/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public final class IORoutines {

	private static final Logger log = LoggerFactory.getLogger(IORoutines.class); 
	
	public static String readFile(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			log.error("readFile", e);
			return null;
		}
	}

	public static String readHtmlFromURL (String url) {

		StringBuilder htmlFile = new StringBuilder();
		try {
			URL urlLocation = new URL(url);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(urlLocation.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				htmlFile.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			log.error("readHtmlFromURL", e);
			return null;
		}
		return htmlFile.toString();
	}
	
	private IORoutines() {
		throw new UnsupportedOperationException();
	}
}
