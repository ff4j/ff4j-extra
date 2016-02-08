package org.ff4j.console;

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

/**
 * Constants to be used in the application.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public interface ApplicationConstants {

    // Content of Page
    String PAGE_TITLE = "title";

    String PAGE_SUBTITLE = "subtitle";

    // ----- VIEWS ------------

    String VIEW_HOME = "home";

    String VIEW_FEATURES = "features";

    String VIEW_STATS = "stats";

    String VIEW_SETTINGS = "settings";

    // ---- Roles ----

    String ROLE_USER = "ROLE_USER";

    String ROLE_ADMIN = "ROLE_ADMIN";

    // ----- Model Attributes ------

    String ATTR_HOMEBEAN = "homebean";

    String ATTR_ENVBEAN = "envbean";
    
    String ATTR_FEATUREBEAN = "featuresbean";
    
    String ATTR_STATISTICBEAN = "statsbean";

    // ---- Form Params ----

    String FORM_PARAM_ENVIRONMENT = "env";

    String MODE_HTTP = "http";

    String MODE_JMX = "jmx";
    
    // --- Http Parameters --
    
    /** POST - Operation. */
    String OPERATION = "op";
    
    /** POST - Operation. */
    String SUBOPERATION = "ope";
    
    /** User operation. */
    String OP_CREATE_FEATURE = "create";

    /** User operation. */
    String OP_EDIT_FEATURE = "update";

    /** User operation: remove feature. */
    String OP_RMV_FEATURE = "delete";

    /** User operation. */
    String OP_TOGGLE_GROUP = "toggleGroup";

    /** User operation. */
    String OP_ENABLE = "enable";

    /** User operation. */
    String OP_DISABLE = "disable";

    /** User operation. */
    String OP_IMPORT = "import";

    /** User operation. */
    String OP_EXPORT = "export";

    // -------- FORM PARAM ------------------------------

    /** HTTP Parameter. */
    String FEATID = "uid";

    /** HTTP Parameter. */
    String ROLE = "role";

    /** HTTP Parameter. */
    String DESCRIPTION = "desc";

    /** HTTP Parameter. */
    String FLIPFILE = "flipFile";
    
    /** HTTP Parameter. */
    String GROUPNAME = "groupName";

    /** HTTP Parameter. */
    String STRATEGY = "strategy";
    
    /** HTTP Parameter. */
    String STRATEGY_INIT = "initParams";

    /** HTTP Parameter. */
    String PERMISSION = "permission";
    
    // ------- FEATURE ELEMENTS --------------------

    /** attribute name. */
    String PREFIX_CHECKBOX = "perm-check-";

    /** attribute name. */
    String PREFIX_TEXTBOX = "perm-text-";

    /** permission. */
    String PERMISSION_PUBLIC = "Public";

    /** permission. */
    String PERMISSION_RESTRICTED = "Restricted";

    /** modal id. */
    String MODAL_EDIT = "modalEdit";

    /** modal ID. */
    String MODAL_CREATE = "modalCreate";

    /** modal ID. */
    String MODAL_TOGGLE = "modalToggle";
    
    // --- Message type ---
    
    /** message type. */
    String MSG_INFO = "info";
    
    /** message type. */
    String MSG_WARNING = "warning";
    
    /** message type. */
    String MSG_ERROR = "error";
    
    /** message type. */
    String MSG_SUCCESS = "success";

}
