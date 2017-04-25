package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Shared.Game;
import Shared.Question;

public class ServerThread implements Runnable{

	private Socket client;
	public ServerThread(Socket client){
		this.client = client;
	}
	public void run(){
		Player player = null;
		try {
			player = getUser(client);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			play(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Player getUser(Socket client) throws IOException{
		Scanner userIn = new Scanner(System.in);
	    Scanner     in  = new Scanner( client.getInputStream() );
	    PrintWriter out = new PrintWriter( client.getOutputStream(), true );
	    
	    out.println("Hallo Client, bist du bereits Spieler? J/N");
	    out.println("EOF");
	    
	    if(in.nextLine().equals("J")){
	    	out.println("Bitte gib deinen Spielernamen ein");
	    	out.println("EOF");
	    	String spielerName = in.nextLine();
	    	//Prfen ob Spieler in DB und laden der daten UNTEN EINFGEN
	    	return new Player(spielerName, 0, client);
	    }
	    else{
	    	out.println("Waehle einen Spielernamen");
	    	out.println("EOF");
	    	String spielerName = in.nextLine();
	    	out.println("Wilkommen "+spielerName);
	    	return new Player(spielerName, 0, client);
	    }
	}
	
	private static void play(Player player1) throws IOException
	  {
		 
		//Mocking questions
		 Question[] questions = new Question[4];
		 
	    	String q1text = "Welche Farbe hat meine Unterhose?";
			Map<String, Boolean> q1answers = new HashMap<String, Boolean>();
			q1answers.put("blau", false);
			q1answers.put("pink", true);
			Question q1 = new Question(q1text, q1answers);
			
			String q2text = "Welche Farbe hat mein Haar?";
			Map<String, Boolean> q2answers = new HashMap<String, Boolean>();
			q2answers.put("blau", false);
			q2answers.put("pink", true);
			Question q2 = new Question(q2text, q2answers);
			
			String q3text = "Welche Farbe hat mein rechter Strumpf?";
			Map<String, Boolean> q3answers = new HashMap<String, Boolean>();
			q3answers.put("blau", false);
			q3answers.put("pink", true);
			Question q3 = new Question(q3text, q3answers);
			
			String q4text = "Welche Farbe hat mein linker Strumpf?";
			Map<String, Boolean> q4answers = new HashMap<String, Boolean>();
			q4answers.put("blau", false);
			q4answers.put("pink", true);
			Question q4 = new Question(q4text, q4answers);
			
			questions[0] = q1;
			questions[1] = q2;
			questions[2] = q3;
			questions[3] = q4;

		 Scanner userIn = new Scanner(System.in);
		 Game game = new Game(questions);
		 
		 
		Socket client1 = player1.client;
	    Scanner     in1  = new Scanner( client1.getInputStream() );
	    PrintWriter out1 = new PrintWriter( client1.getOutputStream(), true );

	    out1.println( "Let's play!" );
	    
	    
			
			
			for(int i=0;i<game.questions.length;i++){
				out1.println("Frage: "+game.getQuestion(i));
				out1.println("Anwortmoeglichkeiten: "+game.getAnswers(i));
				out1.println("EOF");
				String antwort = in1.nextLine();
				
				if(game.validateAnswer(antwort, i)){
					player1.score++;
					out1.println("Richtig");
					out1.println("Dein aktueller Score: "+player1.score);
					
				}
				else{
					out1.println("Falsch");
					out1.println("Dein aktueller Score: "+player1.score);
					
				}
				if(i==3){
					out1.println("EOF");
				}
			}
	  }
}
