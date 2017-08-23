package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LetsGetIt extends JFrame /*implements ActionListener */{
	LetsGetItServer letsGetItS;
	LetsGetItClient letsGetItC;
	
	private void Server() {
		//악보 선택 시 윈도우 창에 악보 띄워줌
		letsGetItS = new LetsGetItServer();
	}
	
	private void Client(){
		letsGetItC = new LetsGetItClient();
	}
	
	public static void main(String[] args) throws IOException {
		
		LetsGetIt letsGetIt = new LetsGetIt();			
		letsGetIt.Server();			
		//letsGetIt.Client();
	}

}
