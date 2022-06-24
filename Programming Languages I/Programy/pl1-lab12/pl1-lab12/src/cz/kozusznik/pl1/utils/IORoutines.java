/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.utils;

import java.io.IOException;
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
	
	private IORoutines() {
		throw new UnsupportedOperationException();
	}
}
