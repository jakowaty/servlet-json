package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;



public class JsonMapAnalyzer extends GenericMapAnalyzer
{
	public JsonReader parser;
	public Map<String, ?> map;
	
	@Override
	public boolean loadStream(InputStream mapStream) throws Exception {		
//		JsonReader reader = Json.createReader(new StringReader(this.asString));
//		JsonValue jsonRoutes = reader.readObject().get("routes");
		
		this.asString = new String(mapStream.readAllBytes());
		this.map = Collections.emptyMap();
		this.streamLoaded = true;
		
		mapStream.close();
		
//		if (jsonRoutes == null) {
//			throw new InvalidMapFormatException();
//		}
		
		return true;
	}
	
	
	@Override
	public List<String> getKeys() throws Exception {
		
		if (!this.streamLoaded) {
			throw new Exception("Stream not loaded " + parser.getClass().getName());
		}
		
		List<String> list = new ArrayList<String>();
		this.parser = Json.createReader(new StringReader(this.asString));
		
		if (!(this.parser instanceof JsonReader)) {
			throw new Exception("Invalid parser class " + parser.getClass().getName());
		}
		
		JsonObject objectJson = parser.readObject();
		if (!objectJson.isEmpty()) {
			list.add("has key");
		}
//		parser.hasNext();
//		while () {
//			try {
//				JsonParser.Event event = parser.next();
//					switch (event) {
//						case KEY_NAME:
//							event.
////							list.add();
//							break;
//						}
//				} catch (NoSuchElementException e) {
//					break;
//				}
////		}
//		
		return list;
	}
}
