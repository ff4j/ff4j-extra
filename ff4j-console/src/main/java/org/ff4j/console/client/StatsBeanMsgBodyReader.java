package org.ff4j.console.client;

/*
 * #%L
 * ff4j-console
 * %%
 * Copyright (C) 2013 - 2015 Ff4J
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.ff4j.console.domain.StatisticsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reader dedicated to invoke monitoring API.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class StatsBeanMsgBodyReader implements MessageBodyReader<StatisticsBean> {

    /** logger for the class. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /** {@inheritDoc} */
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return (type == StatisticsBean.class);
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public StatisticsBean readFrom(Class<StatisticsBean> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> evtMap = new HashMap<String, Object>();
        evtMap = objectMapper.readValue(entityStream, HashMap.class);
        
        StatisticsBean sBean = new StatisticsBean();
        sBean.setType((String) evtMap.get("type"));
        sBean.setTotalHit((Integer) evtMap.get("hitCount"));
        
        // Pie
        Map<String, Object> pieMap = (Map<String, Object>) evtMap.get("eventsPie");
        sBean.setPieChartTitle((String) pieMap.get("title"));
        
        StringBuilder sbData = new StringBuilder("[");
        StringBuilder sbColor = new StringBuilder("[");
        List < LinkedHashMap< String , Object > > listOfSectory = (ArrayList<LinkedHashMap< String , Object >>) pieMap.get("sectors");
        boolean first = true;
        for (LinkedHashMap< String , Object > sector : listOfSectory) {
            if (!first) {
                sbData.append(",");
                sbColor.append(",");
            }
            sbData.append("['" + (String) sector.get("label") + "', " + String.valueOf(sector.get("value")) + "]");
            sbColor.append("\"#" + (String) sector.get("color") + "\"");
            first = false;
        }
        sbData.append("]");
        sBean.setPieChartData(sbData.toString());
        sbColor.append("]");
        sBean.setPieChartColors(sbColor.toString());
        
        // BarChart
        Map<String, Object> barMap = (Map<String, Object>) evtMap.get("barChart");
        sBean.setBarChartTitle((String) barMap.get("title"));
        List < String> labels = (ArrayList<String>) barMap.get("labels");
        StringBuilder barLabels = new StringBuilder("[");
        first = true;
        for (String label : labels) {
            if (!first) {
                barLabels.append(",");
            }
            barLabels.append("'" + label + "'");
            first = false;
        }
        barLabels.append("]");
        sBean.setBarChartLabels(barLabels.toString());
        
        List < LinkedHashMap< String , Object > > series = (ArrayList<LinkedHashMap< String , Object >>) barMap.get("series");
        int idxSerie = 1;
        StringBuilder seriesNames = new StringBuilder("[");
        StringBuilder seriesColors = new StringBuilder("[");
        StringBuilder seriesData = new StringBuilder("");
        boolean firstSerie = true;
        for (LinkedHashMap<String, Object> serie : series) {
            if (!firstSerie) {
                seriesNames.append(",");
                seriesColors.append(",");
            }
            String serieName = "s" + idxSerie;
            idxSerie++;
            seriesNames.append(serieName);
            StringBuilder serieValues = new StringBuilder("var " + serieName + " = [");
            List < Double > values = (List<Double>) serie.get("values");
            seriesColors.append("\"#" + serie.get("color") + "\"");
            first = true;
            for (Double value : values) {
                if (!first) {
                    serieValues.append(",");
                }
                serieValues.append(value);
                first = false;
            }
            serieValues.append("];\n");
            seriesData.append(serieValues.toString());
            firstSerie = false;
        }
        seriesNames.append("]");
        seriesColors.append("]");
        
        sBean.setBarChartSeriesColors(seriesColors.toString());
        sBean.setBarChartSeriesData(seriesData.toString());
        sBean.setBarChartSeriesNames(seriesNames.toString());
        return sBean;
    }


}
