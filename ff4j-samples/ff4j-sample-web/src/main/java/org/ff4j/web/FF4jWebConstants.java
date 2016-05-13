package org.ff4j.web;

/*
 * #%L
 * ff4j-sample-web
 * %%
 * Copyright (C) 2013 - 2016 FF4J
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
 * All constants
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class FF4jWebConstants {

	/**
	 * Hide defaulf constructor.
	 */
	private FF4jWebConstants() {
	}

	/** constant. */
	public static final String VIEW_404 = "404";

	/** constant. */
	public static final String VIEW_DEFAULT = "home";

	/** constant. */
	public static final String VIEW_STATIC = "static";

	/** constant. */
	public static final String VIEW_API = "api";

	 /** User operation. */
    public static final String OP_IMPORT = "import";

    /** User operation. */
    public static final String OP_EXPORT = "export";

    /** POST - Operation. */
    public static final String OPERATION = "op";

    /** View for dispatch. */
    public static final String VIEW = "view";

    /** POST - Operation. */
    public static final String SUBOPERATION = "ope";

    /** User operation. */
    public static final String OP_CREATE_FEATURE = "create";

    /** User operation. */
    public static final String OP_CREATE_PROPERTY = "createProperty";

    /** User operation. */
    public static final String OP_EDIT_FEATURE = "update";

    /** User operation. */
    public static final String OP_EDIT_PROPERTY = "updateProperty";

    /** User operation: remove feature. */
    public static final String OP_RMV_FEATURE = "delete";

    /** User operation: remove feature. */
    public static final String OP_RMV_PROPERTY = "deleteProperty";

    /** User operation: remove feature. */
    public static final String OP_READ_PROPERTY = "readProperty";

    /** remove a value of a listed. */
    public static final String OP_DELETE_FIXEDVALUE = "deleteFixedValue";

    /** remove a value of a listed. */
    public static final String OP_ADD_FIXEDVALUE = "addFixedValue";

    /** User operation. */
    public static final String OP_TOGGLE_GROUP = "toggleGroup";

    /** User operation: remove feature. */
    public static final String OP_READ_FEATURE = "readFeature";

    /** User operation. */
    public static final String OP_ENABLE = "enable";

    /** User operation. */
    public static final String OP_DISABLE = "disable";

    /** User operation. */
    public static final String OP_MONITORING = "monitoring";

    /** HTTP Parameter. */
    public static final String FEATID = "uid";

    /** HTTP Parameter. */
    public static final String ROLE = "role";

    /** HTTP Parameter. */
    public static final String DESCRIPTION = "desc";

    /** HTTP Parameter. */
    public static final String FLIPFILE = "flipFile";

    /** HTTP Parameter. */
    public static final String GROUPNAME = "groupName";

    /** HTTP Parameter. */
    public static final String STRATEGY = "strategy";

    /** HTTP Parameter. */
    public static final String STRATEGY_INIT = "initParams";

    /** HTTP Parameter. */
    public static final String PERMISSION = "permission";

    /** HTTP Parameter. */
    public static final String NAME = "name";

	/** constant. */
	public static final String ERROR = "error";


}
