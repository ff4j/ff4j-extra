package org.ff4j.console.conf;

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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.ff4j.console.conf.xml.Connection;
import org.ff4j.console.conf.xml.Connections;
import org.ff4j.console.conf.xml.Ff4JConsole;
import org.ff4j.console.conf.xml.Properties;
import org.ff4j.console.conf.xml.Property;
import org.ff4j.console.conf.xml.User;
import org.ff4j.console.conf.xml.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

/**
 * Parser for this configuration.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Component("configurationParser")
public class XmlConfigurationParser implements InitializingBean {

    private static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";

    private static final String FILE_CONF = "ff4j-console-conf.xml";

    private static final String SCHEMA = "ff4j-console-conf.xsd";

    private static final String PROPERTY_LANGUAGE = "language";

    /** Logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    private Ff4JConsole rawConfiguration;

    private ConsoleConfiguration conf;

    /** Default Constructor */
    public XmlConfigurationParser() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Parsing XML
        log.info("  __  __ _  _   _ ");
        log.info(" / _|/ _| || | (_)");
        log.info("| |_| |_| || |_| |");
        log.info("|  _|  _|__   _| |");
        log.info("|_| |_|    |_|_/ |");
        log.info("             |__/   Console Full Stack v." + getClass().getPackage().getImplementationVersion());
        log.info(" ");
        
        File xmlProperties = new File(FILE_CONF);
        if (!xmlProperties.exists()) {}
        InputStream xsdProperties = getClass().getClassLoader().getResourceAsStream(SCHEMA);
        if (null == xsdProperties) {
            throw new IllegalArgumentException("Cannot parse XML schema (not found)");
        }
        try {
            Unmarshaller u = getUnmarshaller(Ff4JConsole.class.getPackage().getName(), xsdProperties);
            rawConfiguration = (Ff4JConsole) u.unmarshal(xmlProperties);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse the file", e);
        } finally {
            if (xsdProperties != null) {
                try {
                    xsdProperties.close();
                } catch (IOException e) {
                }
            }
        }
        log.info("Configuration file '" + FILE_CONF + "' has been parsed");

        // PostTreatment XML
        this.conf = new ConsoleConfiguration();
        List<Object> rawElements = rawConfiguration.getPropertiesOrUsersOrConnections();
        for (Object object : rawElements) {
            if (object instanceof Properties) {
                Properties props = (Properties) object;
                List<Property> listOfProp = props.getProperty();
                if (listOfProp != null && !listOfProp.isEmpty()) {
                    for (Property p : listOfProp) {
                        if (PROPERTY_LANGUAGE.equalsIgnoreCase(p.getName())) {
                            conf.setLanguage(new Locale(p.getValue()).getISO3Language());
                        }
                    }
                }
            } else if (object instanceof Users) {
                Users users = (Users) object;
                for (User user : users.getUser()) {
                    conf.getMapOfUser().put(user.getName(), user);
                }
            } else if (object instanceof Connections) {
                Connections conns = (Connections) object;
                List<Connection> listofConn = conns.getConnection();
                if (listofConn != null && !listofConn.isEmpty()) {
                    for (Connection connection : listofConn) {
                        conf.getMapOfConnections().put(connection.getId(), connection);
                    }
                }
            }
        }
        log.info("Configuration has been initialized with " + conf.getMapOfUser().size() + " user(s)");
    }

    /**
     * Méthode permettant de créer le Unmarshaller pour le parsing du XML.
     * @param modelPackage
     *            nom du package qui contient les beans utilisées pour le parsing du fichier.
     * @param schemafile
     *            le fichier XSD pour la validation du XML.
     * @return Unmarshaller
     * @throws JAXBException
     * @throws SAXException
     */
    public Unmarshaller getUnmarshaller(String modelPackage, File schemafile) throws JAXBException, SAXException {
        JAXBContext jc = JAXBContext.newInstance(modelPackage);
        SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(schemafile);
        Unmarshaller u = jc.createUnmarshaller();
        u.setSchema(schema);
        u.setEventHandler(new XmlConfigurationErrorHandler());
        return u;
    }

    /**
     * Méthode permettant de créer le Unmarshaller pour le parsing du XML.
     * @param modelPackage
     *            nom du package qui contient les beans utilisées pour le parsing du fichier.
     * @param schemafile
     *            le fichier XSD pour la validation du XML.
     * @return Unmarshaller
     * @throws JAXBException
     * @throws SAXException
     */
    public Unmarshaller getUnmarshaller(String modelPackage, InputStream schemaStream) throws JAXBException,
            SAXException {
        JAXBContext jc = JAXBContext.newInstance(modelPackage);
        SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new StreamSource(schemaStream));
        Unmarshaller u = jc.createUnmarshaller();
        u.setSchema(schema);
        u.setEventHandler(new XmlConfigurationErrorHandler());
        return u;
    }

    /**
     * Getter accessor for attribute 'rawConfiguration'.
     *
     * @return current value of 'rawConfiguration'
     */
    public Ff4JConsole getRawConfiguration() {
        return rawConfiguration;
    }

    /**
     * Setter accessor for attribute 'rawConfiguration'.
     * 
     * @param rawConfiguration
     *            new value for 'rawConfiguration '
     */
    public void setRawConfiguration(Ff4JConsole rawConfiguration) {
        this.rawConfiguration = rawConfiguration;
    }

    /**
     * Getter accessor for attribute 'conf'.
     *
     * @return current value of 'conf'
     */
    public ConsoleConfiguration getConf() {
        return conf;
    }

    /**
     * Setter accessor for attribute 'conf'.
     * 
     * @param conf
     *            new value for 'conf '
     */
    public void setConf(ConsoleConfiguration conf) {
        this.conf = conf;
    }

}
