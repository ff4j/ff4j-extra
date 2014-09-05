package org.ff4j.console.test;

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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ColorCalculator {
    
    /**
     * This code build the color gradient between 2 colors with defined step.
     * @param codeFrom
     *      color source
     * @param codeTo
     *      color destination
     * @param nbDivision
     *      number of steps
     * @return
     *      the list of colors
     */
    public static List < String > getColor(String codeFrom, String codeTo, int nbDivision) {
        List < String > colors = new ArrayList<String>();
        int rStart = Integer.parseInt(codeFrom.substring(0, 2), 16);
        int rDelta = (Integer.parseInt(codeTo.substring(0, 2), 16) - rStart) / nbDivision;
        int gStart = Integer.parseInt(codeFrom.substring(2, 4), 16);
        int gDelta = (Integer.parseInt(codeTo.substring(2, 4), 16) - gStart) / nbDivision;
        int bStart = Integer.parseInt(codeFrom.substring(4, 6), 16);
        int bDelta = (Integer.parseInt(codeTo.substring(4, 6), 16) - bStart) / nbDivision;
        for (int idx = 0;idx < nbDivision;idx++) {
            String red = Integer.toHexString(rStart + rDelta * idx);
            String green = Integer.toHexString(gStart + gDelta * idx);
            String blue = Integer.toHexString(bStart + bDelta * idx);
            colors.add(red + green + blue);
        }
        return colors;
    }
    
    @Test
    public void testFF4J() {
        String color1 = "00AB8B";
        String color2 = "EEFFEE";
        List < String > colors = getColor(color1, color2, 9);
        for (String string : colors) {
            System.out.println("#" + string);
        }
    }

}
