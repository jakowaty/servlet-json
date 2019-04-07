package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.List;

abstract public class GenericMapAnalyzer
{
	public String asString = null;
	public Boolean streamLoaded = false;

	/**
	 * Creates route map analyzer - depending on file format.
	 * It determines file format expanding file name.
	 * File named "file.json" will expand to Json analyzer, file named "file.xml"
	 * will be expanded to XML analyzer.
	 * It will try to instantiate classes with apropriate name seeking for them in own package
	 * com.jakowaty.piotrbe.router.
	 * 
	 * @param routeMap
	 * @return
	 * @throws Exception
	 */
	public static final GenericMapAnalyzer getAnalyzerByFileType(String routeMap) throws Exception
	{
		String[] nameSplit = routeMap.split("\\.");
		String mapFileExtension = nameSplit[nameSplit.length - 1];
		String analyzerClassName = mapFileExtension.substring(0, 1).toUpperCase() + mapFileExtension.substring(1);
		analyzerClassName = GenericMapAnalyzer.class.getPackageName() + "." + analyzerClassName + "MapAnalyzer";
		
		try {
			Constructor<?> analyzerClassConstructor = Class.forName(analyzerClassName).getConstructor();
			return (GenericMapAnalyzer)analyzerClassConstructor.newInstance();
		} catch (ClassNotFoundException e) {
			throw new Exception(e.getMessage() + " Not found class: " + analyzerClassName);
		}
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	abstract public List<?> getKeys() throws Exception;
	
	/**
	 * 
	 * @param mapStream
	 * @return boolean
	 */
	abstract public boolean loadStream(InputStream mapStream) throws Exception;
}
