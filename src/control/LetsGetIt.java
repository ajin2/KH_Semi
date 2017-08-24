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
	
	private void Server() {
		//악보 선택 시 윈도우 창에 악보 띄워줌
		letsGetItS = new LetsGetItServer();
	}
	
	/*private void Client(){
		letsGetItC = new LetsGetItClient();
	}*/
	
	public static void main(String[] args) throws IOException {
		
		LetsGetIt letsGetIt = new LetsGetIt();			
		//letsGetIt.Server();		
		Ex1 ex1 = new Ex1();
	}

}
