package Shared;

import java.util.Dictionary;
import java.util.Map;

public class Question {

	public Map<String,Boolean> answers;
	public String actualQuestion;
	
	public Question(String question, Map<String, Boolean> answers){
		this.answers = answers;
		this.actualQuestion = question;
	}
}
