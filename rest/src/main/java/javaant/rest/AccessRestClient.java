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
