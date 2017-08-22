package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class MusicPlay extends JFrame{
	SheetMusic sm;
	int index;
	
	public MusicPlay(int index) throws IOException{
		sm = new SheetMusic();
		sm.showSheet(index);
	}
}
 