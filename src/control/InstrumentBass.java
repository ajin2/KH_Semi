package control;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class InstrumentBass extends Thread {
	
	Sequencer sequencer = null; //음악 파일이고
	Sequence sequence = null; //플레이어라고 생각하면 쉽다.
	
	String directory = "testpiano/";
	
	public InstrumentBass(int songNumber) {
		try {
		//파일 객체로부터 시퀀스를 얻어 온다.
		//아래 스트링과 같은 이름의 파일이 존재해야 한다.
			sequence = MidiSystem.getSequence
					(new File(directory + songNumber + ".mid"));
			sequencer = MidiSystem.getSequencer(); //기본 시퀀서를 얻어 온다.
			sequencer.open(); // 시퀀서를 연다.
			sequencer.setSequence(sequence); //시퀀서에 시퀀스를 설정한다.
		} catch (Exception e) {
			System.out.println(e);
		} 
		
	}
	
	public void run() {
			
		try {
			//sequencer.stop(); //이미 곡을 연주하고 있을 때 새로 듣기 위해 먼저 멈춘다.
			sequencer.start(); //곡을 연주한다.
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
}