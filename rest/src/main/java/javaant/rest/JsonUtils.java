package javaant.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	
	
	public static <T> T jsonToJavaObject(String jsonRequest, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE,false);		
		T finalJavaRequest = objectMapper.readValue(jsonRequest, valueType);
		return finalJavaRequest;

	}

	public static String javaToJson(Object o) {
		String jsonString = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE,true);	
			jsonString = objectMapper.writeValueAsString(o);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
}
