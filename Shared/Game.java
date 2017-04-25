package Shared;

import java.util.Dictionary;
import java.util.List;

import Client.Client;
import Server.Player;

public class Game {

	public Question[] questions;
	private Player player1;
	private Player player2;
	
	
	public Game(Question[] questions){
		this.questions = questions;
	}
	
	public Boolean validateAnswer(String answer,int i){
		boolean result;
		try{
			result = questions[i].answers.get(answer);
			}catch(Exception e){
				result =  false;
		
		}
		return result;
	}
	
	public String getQuestion(int i){
		return questions[i].actualQuestion;
	}
	
	public String getAnswers(int i){
		String answers = "\n";
		for(String key : questions[i].answers.keySet()){
			answers+=key+"\n";
		}
		return answers;
	}
}
