package org.ff4j.console.controller;

/*
 * #%L
 * ff4j-console
 * %%
 * Copyright (C) 2013 - 2014 Ff4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.NotImplementedException;
import org.ff4j.FF4j;
import org.ff4j.conf.XmlParser;
import org.ff4j.console.ApplicationConstants;
import org.ff4j.console.client.ConsoleHttpClient;
import org.ff4j.console.domain.EnvironmenBean;
import org.ff4j.console.domain.FeaturesBean;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingStrategy;
import org.ff4j.web.api.FF4jWebConstants;
import org.ff4j.web.store.FeatureStoreHttp;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to display features.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Controller
@RequestMapping("/" + ApplicationConstants.VIEW_FEATURES + ".do")
public class FeaturesController extends AbstractConsoleController {

    /**
     * Display screen
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest req, HttpServletResponse res, 
            @RequestParam(value = OPERATION, required = false) String  operation, 
            @RequestParam(value = FEATID, required = false) String  featureId) throws Exception {
       
        // Environment de travail
        EnvironmenBean envBean = (EnvironmenBean) req.getSession().getAttribute(ATTR_ENVBEAN);
        if (null == envBean) {
            return new ModelAndView("redirect:loadConfig.do");
        }
        log.info("Page <FEATURES>, action<GET>, env<" + envBean.getEnvId() + ">");

        // Access features through HTTP store (all parsing done)
        FeatureStore fStore;
        if ("http".equalsIgnoreCase(envBean.getConnectionMode())) {
            // Access features through HTTP store (all parsing done)
            fStore = new FeatureStoreHttp(envBean.getEnvUrl() + "/" + FF4jWebConstants.RESOURCE_FF4J);
        } else if ("bean".equalsIgnoreCase(envBean.getConnectionMode())) {
            ClassPathXmlApplicationContext cpax = new ClassPathXmlApplicationContext(envBean.getEnvUrl());
            fStore = cpax.getBean(FF4j.class).getFeatureStore();
            cpax.close();
        } else if ("jmx".equalsIgnoreCase(envBean.getConnectionMode())) {
            throw new NotImplementedException("JMX Client is not yet implemented");
        } else {
            throw new IllegalArgumentException("Invalid definition of store within configuration file, avaolable modes are 'http', 'jmx' and 'bean'");
        }
        
        // Data in the target screen
        FeaturesBean featBean = new FeaturesBean();
        
        try {
            
            // Check parameter to know what to do
            if (operation != null && !operation.isEmpty()) {
                log.debug("Performing operation <" + operation + "> on <" + featureId + ">");
                if (OP_DISABLE.equalsIgnoreCase(operation)) {
                    opDisableFeature(fStore, featureId);
                    String msg = buildMessage(featureId, "DISABLED");
                    featBean.setMessage(msg);
                    log.info(msg);
                } else if (OP_ENABLE.equalsIgnoreCase(operation)) {
                    opEnableFeature(fStore, featureId);
                    featBean.setMessage(buildMessage(featureId, "ENABLED"));
                } else if (OP_RMV_FEATURE.equalsIgnoreCase(operation)) {
                    opDeleteFeature(fStore, featureId);
                    String msg = buildMessage(featureId, "DELETED");
                    featBean.setMessage(msg);
                    log.info(msg);
                } else if (OP_EXPORT.equalsIgnoreCase(operation)) {
                    opExportFile(fStore, res);
                    featBean.setMessage("Feature have been success fully exported");
                }
            }

        } catch(Exception e) {
            featBean.setMessageType(MSG_ERROR);
            featBean.setMessage(e.getMessage());
            log.error("Error when retrieving features ", e);
        }
        
        // Updating Bean
        featBean.setListOfFeatures(new ArrayList<Feature>(fStore.readAll().values()));
        featBean.setHtmlPermissions(populatePermissionList(envBean));
        featBean.setGroupList(fStore.readAllGroups());
        
        // Create view
        ModelAndView mav = new ModelAndView(VIEW_FEATURES);
        mav.addObject(ATTR_ENVBEAN, envBean);
        mav.addObject(ATTR_FEATUREBEAN, featBean);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView executeThenShowPage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        log.info("Page <FEATURES> on action <POST>");

        // Environment de travail
        EnvironmenBean envBean = (EnvironmenBean) req.getSession().getAttribute(ATTR_ENVBEAN);

        // Access features through HTTP store (all parsing done)
        FeatureStore storeHTTP = new FeatureStoreHttp(envBean.getEnvUrl() + "/" + FF4jWebConstants.RESOURCE_FF4J);
        
        // Data in the target screen
        FeaturesBean featBean = new FeaturesBean(); 
        try {
            // Import Features
            if (ServletFileUpload.isMultipartContent(req)) {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if (OPERATION.equalsIgnoreCase(item.getFieldName())) {
                            log.info("Processing action : " + item.getString());
                        }
                    } else if (FLIPFILE.equalsIgnoreCase(item.getFieldName())) {
                        String filename = FilenameUtils.getName(item.getName());
                        if (filename.toLowerCase().endsWith("xml")) {
                            opImportFile(storeHTTP, item.getInputStream());
                            featBean.setMessage("The file <b>" + filename + "</b> has been successfully imported");
                        } else {
                            featBean.setMessage("Invalid FILE, must be XML files");
                            featBean.setMessageType(MSG_ERROR);
                        }
                    }
                }

            } else {
                // Edit Operations
                String operation = req.getParameter(OPERATION);
                if (operation != null && !operation.isEmpty()) {

                    if (OP_EDIT_FEATURE.equalsIgnoreCase(operation)) {
                        opUpdateFeatureDescription(storeHTTP, req);
                        featBean.setMessage(buildMessage(req.getParameter(FEATID), "UPDATED"));

                    } else if (OP_CREATE_FEATURE.equalsIgnoreCase(operation)) {
                        opCreateFeature(storeHTTP, req);
                        featBean.setMessage(buildMessage(req.getParameter(FEATID), "ADDED"));

                    } else if (OP_TOGGLE_GROUP.equalsIgnoreCase(operation)) {
                        String groupName = req.getParameter(GROUPNAME);
                        if (groupName != null && !groupName.isEmpty()) {
                            String operationGroup = req.getParameter(SUBOPERATION);
                            if (OP_ENABLE.equalsIgnoreCase(operationGroup)) {
                                storeHTTP.enableGroup(groupName);
                                featBean.setMessage(buildMessageGroup(groupName, "ENABLED"));
                                log.info("Group '" + groupName + "' has been ENABLED.");
                            } else if (OP_DISABLE.equalsIgnoreCase(operationGroup)) {
                                storeHTTP.disableGroup(groupName);
                                featBean.setMessage(buildMessageGroup(groupName, "DISABLED"));
                                log.info("Group '" + groupName + "' has been DISABLED.");
                            }
                        }
                    } else {
                        log.error("Invalid POST OPERATION" + operation);
                        featBean.setMessageType(MSG_ERROR);
                        featBean.setMessage("Invalid REQUEST");
                    }
                }
            }
        } catch(Exception e) {
            featBean.setMessageType(MSG_ERROR);
            featBean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        
        // Updating bean
        featBean.setListOfFeatures(new ArrayList<Feature>(storeHTTP.readAll().values()));
        featBean.setHtmlPermissions(populatePermissionList(envBean));
        featBean.setGroupList(storeHTTP.readAllGroups());
        
        // Create view
        ModelAndView mav = new ModelAndView(VIEW_FEATURES);
        mav.addObject(ATTR_ENVBEAN, envBean);
        mav.addObject(ATTR_FEATUREBEAN, featBean);
        return mav;
    }

    /**
     * Access the authorizationManager.
     */
    private String populatePermissionList(EnvironmenBean envBean) {
        ConsoleHttpClient client = new ConsoleHttpClient(getConnection(envBean.getEnvId()));
        StringBuilder sb = new StringBuilder("<br/>");
        for (String permission : client.getAllPermissions()) {
                sb.append("\r\n<br/>&nbsp;&nbsp;&nbsp;<input type=\"checkbox\" ");
                sb.append(" name=\"" + PREFIX_CHECKBOX + permission + "\"");
                sb.append(" id=\"" + PREFIX_CHECKBOX + permission + "\" >&nbsp;");
                sb.append(permission);
        }
        // Begin user provided permission input
        sb.append("\r\n<br/>&nbsp;&nbsp;&nbsp;<input type=\"checkbox\" ");
        sb.append(" name=\"" + PREFIX_CHECKBOX + "other\"");
        sb.append(" id=\"" + PREFIX_CHECKBOX + "other\" >&nbsp;Other:&nbsp");
        sb.append("<input type=\"text\" ");
        sb.append("name=\"" + PREFIX_TEXTBOX + "other-value\"");
        sb.append("id=\"" + PREFIX_TEXTBOX + "other-value\" />");
        // End user provided permission input
        log.info(client.getAllPermissions().size() + " permission(s) retrieved");
        return sb.toString();
    }
    
    /**
     * Build info messages.
     * 
     * @param featureName
     *            target feature name
     * @param operationd
     *            target operationId
     * @return
     */
    private String buildMessage(String featureName, String operationId) {
        return String.format("Feature <b>%s</b> has been successfully %s", featureName, operationId);
    }
    
    /**
     * Build info messages.
     * 
     * @param featureName
     *            target feature name
     * @param operationd
     *            target operationId
     * @return
     */
    private String buildMessageGroup(String groupName, String operationId) {
        return String.format("Group <b>%s</b> has been successfully %s", groupName, operationId);
    }

    /**
     * User action to enable a Feature.
     * 
     * @param req
     *            http request containing operation parameters
     */
    private void opEnableFeature(FeatureStore store, String featureId) {
        if (featureId != null && !featureId.isEmpty()) {
            store.enable(featureId);
            log.info(featureId + " has been successfully enabled");
        }
    }

    /**
     * User action to disable a Feature.
     * 
     * @param req
     *            http request containing operation parameters
     */
    private void opDisableFeature(FeatureStore store, String featureId) {
        if (featureId != null && !featureId.isEmpty()) {
            store.disable(featureId);
            log.info(featureId + " has been disabled");
        }
    }

    /**
     * User action to create a new Feature.
     * 
     * @param req
     *            http request containing operation parameters
     */
    private void opCreateFeature(FeatureStore store, HttpServletRequest req) {
        // uid
        final String featureId = req.getParameter(FEATID);
        if (featureId != null && !featureId.isEmpty()) {
            Feature fp = new Feature(featureId, false);

            // Description
            final String featureDesc = req.getParameter(DESCRIPTION);
            if (null != featureDesc && !featureDesc.isEmpty()) {
                fp.setDescription(featureDesc);
            }

            // GroupName
            final String groupName = req.getParameter(GROUPNAME);
            if (null != groupName && !groupName.isEmpty()) {
                fp.setGroup(groupName);
            }

            // Strategy
            final String strategy = req.getParameter(STRATEGY);
            if (null != strategy && !strategy.isEmpty()) {
                try {
                    Class<?> strategyClass = Class.forName(strategy);
                    FlippingStrategy fstrategy = (FlippingStrategy) strategyClass.newInstance();

                    final String strategyParams = req.getParameter(STRATEGY_INIT);
                    if (null != strategyParams && !strategyParams.isEmpty()) {
                        Map<String, String> initParams = new HashMap<String, String>();
                        String[] params = strategyParams.split(";");
                        for (String currentP : params) {
                            String[] cur = currentP.split("=");
                            if (cur.length < 2) {
                                throw new IllegalArgumentException("Invalid Syntax : param1=val1,val2;param2=val3,val4");
                            }
                            initParams.put(cur[0], cur[1]);
                        }
                        fstrategy.init(featureId, initParams);
                    }
                    fp.setFlippingStrategy(fstrategy);

                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Cannot find strategy class", e);
                } catch (InstantiationException e) {
                    throw new IllegalArgumentException("Cannot instanciate strategy", e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Cannot instanciate : no public constructor", e);
                }
            }

            populateFeatureWithPermissions(fp, req);

            // Creation
            store.create(fp);
            log.info(featureId + " has been created");
        }
    }

    /**
     * User action to update a target feature's description.
     * 
     * @param req
     *            http request containing operation parameters
     */
    @SuppressWarnings("unchecked")
    private void opUpdateFeatureDescription(FeatureStore store, HttpServletRequest req) {
        // uid
        final String featureId = req.getParameter(FEATID);
        if (featureId != null && !featureId.isEmpty()) {
            Feature fp = new Feature(featureId, false);

            // Description
            final String featureDesc = req.getParameter(DESCRIPTION);
            if (null != featureDesc && !featureDesc.isEmpty()) {
                fp.setDescription(featureDesc);
            }

            // GroupName
            final String groupName = req.getParameter(GROUPNAME);
            if (null != groupName && !groupName.isEmpty()) {
                fp.setGroup(groupName);
            }

            // Strategy
            final String strategy = req.getParameter(STRATEGY);
            if (null != strategy && !strategy.isEmpty()) {
                try {
                    Class<?> strategyClass = Class.forName(strategy);
                    FlippingStrategy fstrategy = (FlippingStrategy) strategyClass.newInstance();

                    final String strategyParams = req.getParameter(STRATEGY_INIT);
                    if (null != strategyParams && !strategyParams.isEmpty()) {
                        Map<String, String> initParams = new HashMap<String, String>();
                        String[] params = strategyParams.split(";");
                        for (String currentP : params) {
                            String[] cur = currentP.split("=");
                            if (cur.length < 2) {
                                throw new IllegalArgumentException("Invalid Syntax : param1=val1,val2;param2=val3,val4");
                            }
                            initParams.put(cur[0], cur[1]);
                        }
                        fstrategy.init(featureId, initParams);
                    }
                    fp.setFlippingStrategy(fstrategy);

                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Cannot find strategy class", e);
                } catch (InstantiationException e) {
                    throw new IllegalArgumentException("Cannot instanciate strategy", e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Cannot instanciate : no public constructor", e);
                }
            }

            populateFeatureWithPermissions(fp, req);

            // Creation
            store.update(fp);
            log.info(featureId + " has been updated");
        }
    }

    private void populateFeatureWithPermissions(Feature fp, HttpServletRequest req) {
        // Permissions
        final String permission = req.getParameter(PERMISSION);
        if (null != permission && PERMISSION_RESTRICTED.equals(permission)) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> parameters = req.getParameterMap();
            Set<String> permissions = new HashSet<>();
            for (String key : parameters.keySet()) {
                if (key.startsWith(PREFIX_CHECKBOX)) {
                    if (key.equals(PREFIX_CHECKBOX + "other")) {
                        permissions.addAll(Arrays.asList(parameters.get(PREFIX_TEXTBOX + "other-value")[0].split(",")));
                    } else {
                        permissions.add(key.replace(PREFIX_CHECKBOX, ""));
                    }
                }
            }
            fp.setPermissions(permissions);
        }
    }

    /**
     * User action to delete a new Feature.
     * 
     * @param req
     *            http request containing operation parameters
     */
    private void opDeleteFeature(FeatureStore store, String id) {
        if (StringUtils.hasLength(id)) {
            store.delete(id);
            log.info(id + " has been deleted");
        }
    }

    /**
     * User action to import Features from a properties files.
     * 
     * @param in
     *            inpustream from configuration file
     * @throws IOException
     *             Error raised if the configuration cannot be read
     */
    private void opImportFile(FeatureStore store, InputStream in) throws IOException {
        Map<String, Feature> mapsOfFeat = new XmlParser().parseConfigurationFile(in).getFeatures();
        for (Entry<String, Feature> feature : mapsOfFeat.entrySet()) {
            if (store.exist(feature.getKey())) {
                store.update(feature.getValue());
            } else {
                store.create(feature.getValue());
            }
        }
        log.info(mapsOfFeat.size() + " features have been imported.");
    }

    /**
     * Build Http response when invoking export features.
     * 
     * @param res
     *            http response
     * @throws IOException
     *             error when building response
     */
    private void opExportFile(FeatureStore store, HttpServletResponse res) throws IOException {
        Map<String, Feature> features = store.readAll();
        InputStream in = new XmlParser().exportFeatures(features);
        ServletOutputStream sos = null;
        try {
            sos = res.getOutputStream();
            res.setContentType("text/xml");
            res.setHeader("Content-Disposition", "attachment; filename=\"ff4j.xml\"");
            // res.setContentLength()
            byte[] bbuf = new byte[4096];
            int length = 0;
            while ((in != null) && (length != -1)) {
                length = in.read(bbuf);
                sos.write(bbuf, 0, length);
            }
            log.info(features.size() + " features have been exported.");
        } finally {
            if (in != null) {
                in.close();
            }
            if (sos != null) {
                sos.flush();
                sos.close();
            }
        }
    }

}
