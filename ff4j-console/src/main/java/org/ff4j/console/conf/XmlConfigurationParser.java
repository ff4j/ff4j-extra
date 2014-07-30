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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.ff4j.console.conf.xml.Ff4JConsole;
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

    private Ff4JConsole xmlConfiguration;

    /** Default Constructor */
    public XmlConfigurationParser() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Parsing XML
        File xmlProperties = new File(FILE_CONF);
        if (!xmlProperties.exists()) {}
        InputStream xsdProperties = getClass().getClassLoader().getResourceAsStream(SCHEMA);
        if (null == xsdProperties) {
            throw new IllegalArgumentException("Cannot parse XML schema (not found)");
        }
        try {
            Unmarshaller u = getUnmarshaller(Ff4JConsole.class.getPackage().getName(), xsdProperties);
            xmlConfiguration = (Ff4JConsole) u.unmarshal(xmlProperties);
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

        // PostTreatment XML

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


}
