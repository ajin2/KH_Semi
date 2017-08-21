package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LetsGetIt extends JFrame /*implements ActionListener */{
	LetsGetItServer letsGetItS;
	SheetMusic sheetMusic;
	MusicPlay musicPlay;
	
	private void open() {
		// TODO Auto-generated method stub
		letsGetItS = new LetsGetItServer();
	}
	
	public static void main(String[] args) throws IOException {
		
		LetsGetIt letsGetIt = new LetsGetIt();			// 怨듯넻 援ы쁽
		letsGetIt.open();
	}

}
