package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

//import javax.json.Json;
//import javax.json.JsonException;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.stream.JsonParser;


public class JsonMapAnalyzer extends GenericMapAnalyzer
{
	public final String version = "0.0.1";
	public Map<String, ?> map;

	@Override
	public boolean loadStream(InputStream mapStream) throws Exception {		
		this.asString = new String(mapStream.readAllBytes());
		this.map = Collections.emptyMap();
		this.streamLoaded = true;
		
		mapStream.close();
		
		return true;
	}
	
	
	@Override
	public List<String> getKeys() throws Exception {
		
		if (!this.streamLoaded) {
			throw new Exception("Stream not loaded");
		}
		
		List<String> list = new ArrayList<String>();
		
		JSONObject jsonObject = new JSONObject(this.asString);
		
		return list;
	}
	
	protected void validate(JSONObject jsonObject) throws InvalidMapFormatException{
		if (!jsonObject.has("version") || !jsonObject.has("routes")) {
			throw new InvalidMapFormatException("There need to be :: 'version' and 'routes'");
		}
	}
}
