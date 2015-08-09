# Consuming RESTful web service with basic authentication.
We can consume Restful webservices many ways. But in this post I will show how to consume RESTful webservices using jersey rest client, which has basic authentication.
##What we need?

1. RESTful url.
2. Jersey Client jar.
3. Jackson data Mapper jar.

##1. RESTful url

Build a RESTful webservices using jersey or spring. If you do not know how to build a webservices please refer my previous [post](http://javaant.com/restful-web-services-using-jersey/#.VcYjI_mqqko)

##2. Jersey Core Client jar

[Download](http://mvnrepository.com/artifact/com.sun.jersey/jersey-client/1.19) the jar file from maven repository or use the below maven dependency 

```xml
<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-client</artifactId>
	<version>1.19</version>
</dependency>
```

##3. Jackson data Mapper jar

[Download](http://mvnrepository.com/artifact/org.codehaus.jackson/jackson-jaxrs/1.9.13) the jar file from maven repository or use the below maven dependency 

```xml
<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-jaxrs</artifactId>
			<version>1.9.13</version>
</dependency>
```

##Client code to access RESTful webservices.

###RestClient.java

Rest client is the common class which has get and post method.

```java
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
```

###AccessRestClient.java

This class access the rest url using the RestClient class.

```java
package javaant.rest;
import java.util.ArrayList;
import java.util.List;
public class AccessRestClient {
	String baseUrl="http://javamad.com/javamad-webservices-1.0";
	public void getQuestions(){
	
		MyResponse gqResponse=new MyResponse();
		gqResponse =RestClient.get(baseUrl+"/v1/questionAnswerService/questions?questionType=2",gqResponse);
		List qList=new ArrayList<QuestionDetails>();
		qList=(List) gqResponse.getData();
		
	}
	
	
	public void postQuestions(){
		MyResponse pqResponse=new MyResponse();
		PostQuestionRequest pqRequest=new PostQuestionRequest();
		pqRequest.setQuestion("Post questions form rest client");
		pqRequest.setQuestionType("2");
		pqRequest.setUser_id("2");
		//Map m= new HashMap();
		pqResponse =(MyResponse) RestClient.post(baseUrl+"/v1/questionAnswerService/questions",pqRequest,pqResponse);
		
	}
	public static void main(String ar[]){
	AccessRestClient ac= new AccessRestClient();
		
		/////////////////post
		ac.postQuestions();
		//////////////////get
		ac.getQuestions();
		
	}

}
```


