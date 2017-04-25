package Server;

import java.net.Socket;

public class Player {

	public String name;
	public int score;
	public Socket client;
	
	public Player (String name, int score ,Socket client){
		this.name = name;
		this.client = client;
		this.score = score;
	}
	
}
