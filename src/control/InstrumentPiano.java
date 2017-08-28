package control;

import java.io.*;
import javax.sound.sampled.*;

public class InstrumentPiano extends Thread {

	Clip clip = null;
	String directory = "instrumentpiano/";

	public InstrumentPiano(int songNumber) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(directory + songNumber + ".wav")));
			Thread.sleep(clip.getMicrosecondLength() / 100000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		try {
			clip.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}