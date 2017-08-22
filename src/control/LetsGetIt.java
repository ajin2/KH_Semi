package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LetsGetIt extends JFrame /*implements ActionListener */{
	LetsGetItServer letsGetItS;
	
	private void open() {
		//악보 선택 시 윈도우 창에 악보 띄워줌
		letsGetItS = new LetsGetItServer();
	}
	
	public static void main(String[] args) throws IOException {
		
		LetsGetIt letsGetIt = new LetsGetIt();			
		letsGetIt.open();							
	}

}
