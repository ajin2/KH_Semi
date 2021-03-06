package control;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class InstrumentElectric extends Thread {
	
	Clip clip = null;
	String directory = "testelectric/";
	
	public InstrumentElectric(int songNumber) {
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