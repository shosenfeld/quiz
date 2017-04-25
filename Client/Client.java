package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static void main( String[] args ) throws InterruptedException
	  {
	    Socket server = null;

	    try
	    {
	    	Scanner userIn = new Scanner(System.in);
	      server = new Socket( "localhost", 8081 );
	      Scanner     in  = new Scanner( server.getInputStream() );
	      PrintWriter out = new PrintWriter( server.getOutputStream(), true );

	      while(true){
	    	  Thread.sleep(100);
	    	  while(in.hasNextLine()){
	    		  String line = in.nextLine();
	    		  if(line.contains("EOF")){
	    			  break;
	    		  }
	    		  else{
	    			  System.out.println( line );
	    		  }
	    	  }
	    	  out.println(userIn.nextLine());
	      }

	    }
	    catch ( UnknownHostException e ) {
	      e.printStackTrace();
	    }
	    catch ( IOException e ) {
	      e.printStackTrace();
	    }
	    finally {
	      if ( server != null )
	        try { server.close(); } catch ( IOException e ) { }
	    }
	  }

}
