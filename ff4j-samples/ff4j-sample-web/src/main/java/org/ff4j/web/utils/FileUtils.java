package org.ff4j.web.utils;

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


import static org.ff4j.web.embedded.ConsoleConstants.NEW_LINE;
import static org.ff4j.web.embedded.ConsoleConstants.UTF8_ENCODING;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read file for HDD.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class FileUtils {
	
	/** Taille du buffer. **/
    private static final int BUFFER_SIZE = 4096;
    
    /** Logger for this class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * Hide default constructor.
	 */
	private FileUtils() {
	}
	
	 /**
     * Utils method to load a file as String.
     *
     * @param fileName
     *            target file Name.
     * @return target file content as String
     */
    public static String loadFileAsString(String fileName) {
        InputStream in = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (in == null) return null;
        Scanner currentScan = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            currentScan = new Scanner(in, UTF8_ENCODING);
            while (currentScan.hasNextLine()) {
                strBuilder.append(currentScan.nextLine());
                strBuilder.append(NEW_LINE);
            }
        } finally {
            if (currentScan != null) {
                currentScan.close();
            }
        }
        return strBuilder.toString();
    }
    
    public final String loadFileAsStringWithBuffer(String fileName) throws IOException {
    	InputStream in = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[BUFFER_SIZE];
        int n = 0;
        while (n != -1) {
            out.append(new String(b, 0, n));
            n = in.read(b);
        }
        return out.toString();
    }

	/**
	 * Transform inputStream into base64.
	 *
	 * @param image The image to encode
	 * @param type jpeg, bmp, ...
	 * @return encoded string
	 */
	public static String loadFileAsBase64(String fileName) {
		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
		if (is == null) return null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// Reading
			BufferedImage image = ImageIO.read(is);
			// Resizing
			BufferedImage resizedImage = new BufferedImage(130, 180, image.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(image, 0, 0, 130, 180, null);
			g.dispose();
			// Write into outpustream
			ImageIO.write(resizedImage, getFileExtension(fileName), bos);
			// Convert to base64
			return new String(Base64.getEncoder().encode(bos.toByteArray()));
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot convert image to base64", e);
		} finally {
			try {
				bos.close();
			} catch (IOException e) {}
		}
	}
	
	/**
	 * Extract extension of file if exist.
	 *
	 * @param filepath
	 * 		current file path
	 * @return
	 * 		current extension
	 */
	public static String getFileExtension(String filepath) {
		int lastIndex = filepath.lastIndexOf(".");
		return (lastIndex == -1) ? null :  filepath.substring(lastIndex + 1 , filepath.length());
	}

}
