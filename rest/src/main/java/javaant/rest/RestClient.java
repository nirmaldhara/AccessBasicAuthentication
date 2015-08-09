package javaant.rest;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RestClient {
	//////////change the rest user and password
	static String rest_password="yourpassword";
	static String rest_user="user id";
	public static <T> T post(String url,T data,T t){
		try {
		Client client = Client.create();
		
		 client.addFilter(new HTTPBasicAuthFilter(rest_user, rest_password));
		WebResource webResource = client.resource(url);
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, JsonUtils.javaToJson(data));
 
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
		String output = response.getEntity(String.class);
		System.out.println("Response===post="+output);
		
			t=(T)JsonUtils.jsonToJavaObject(output, t.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	public static <T> T get(String url,T t)
	{
		 try {
		Client client = Client.create();
		 
		WebResource webResource = client.resource(url);
		 client.addFilter(new HTTPBasicAuthFilter(rest_user, rest_password));
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
 
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
 
		String output = response.getEntity(String.class);
		System.out.println("Response===get="+output);
			t=(T)JsonUtils.jsonToJavaObject(output, t.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
