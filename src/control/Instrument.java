package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Instrument {
	int index;
	
	
	public Instrument(){}

	public File instrumentShow (int index) throws IOException {
		this.index = index;
		File f = new File("");

		if (index == 0) {
			
		//	InstrumentPiano piano = new InstrumentPiano(songNumber)
			f = new File("img/piano.png");
			
		} else if (index == 1) {
			f = new File("img/elec.png");
		} else if (index == 2) {
			f = new File("img/bass.png");
		} else if (index == 3) {
			f = new File("img/drum.png");
		}
			return f;		
	}

}