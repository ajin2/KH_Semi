package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LetsGetIt extends JFrame {
	LetsGetItServer letsGetItS;
	LetsGetItClient letsGetItC;
	boolean checkClient;
	boolean checkServer;
	
	// Client의 값을 확인 시켜주는 메소드
	public boolean getcheckClient(boolean checkClient) {
		return checkClient;
	}
	
	// Client의 값을 바꾸는 메소드
	public void setCheckClient(boolean CheckClient) {
		this.checkClient = CheckClient;
	}

	// Server의 값을 확인 시켜주는 메소드
	public boolean getcheckServer(boolean checkServer) {
		return checkServer;
	}
	
	// Server의 값을 바꾸는 메소드
	public void setcheckServer(boolean checkServer) {
		this.checkServer = checkServer;
	}
	
	private void Server() throws IOException {
		letsGetItS = new LetsGetItServer(4500);
	}
	
	/*private void Client() throws IOException {
		letsGetItC = new LetsGetItClient("localhost", 4500, "Test User");
	}
*/
	public static void main(String[] args) throws IOException { 
		
		LetsGetIt letsGetIt = new LetsGetIt();		

		letsGetIt.Server();			
		//letsGetIt.Client();
		
	}
}
