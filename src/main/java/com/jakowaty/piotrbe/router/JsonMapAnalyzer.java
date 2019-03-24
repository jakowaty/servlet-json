package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;



public class JsonMapAnalyzer extends GenericMapAnalyzer
{
	public JsonParser parser;
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
		
		this.parser = Json.createParserFactory(this.map)
				.createParser(new StringReader(this.asString));
		
		if (!(this.parser instanceof JsonParser)) {
			throw new Exception("Invalid parser class " + parser.getClass().getName());
		}
		
		List<String> list = Collections.emptyList();
	
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch (event) {
			case KEY_NAME:
				list.add(event.toString());
				break;
	
			}
		}
		
		return list;
	}
}
