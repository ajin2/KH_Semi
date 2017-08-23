package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Instrument extends Thread {
	int index;
	
	public Instrument(){}

	public File instrumentShow (int index) throws IOException {
		this.index = index;
		File f = new File("");
		

		if (index == 0) {
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