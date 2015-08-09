package javaant.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;


public class PostQuestionRequest {
	
	private String user_id;
	private String question;
	
	
	private String questionType;
	

	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
		
	
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	

}
