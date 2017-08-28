package control;

import java.io.IOException;

public class RcvMusicThread extends Thread {
	private LetsGetItClient letsGetItClient;
	
	public RcvMusicThread(LetsGetItClient letsGetItClient) {
		this.letsGetItClient = letsGetItClient;
	}

	public void run() {
		String message = null;
		String[] receiveMsg = null;
		boolean isStop = false;
		
		while(!isStop) {
//			try {
//				message = (String)letsGetItClient.getOis().readObject();
//				receiveMsg = message.split("#");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//				isStop = true;
//			} catch (IOException e) {
//				e.printStackTrace();
//				isStop = true;
//			} 
	
			// 어떤 악기에서 보냈는지 판별하는 if문
			if(receiveMsg[0].equals("0") && (letsGetItClient.getThisUserSelected() != 0)) {		// piano
				InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(receiveMsg[1]));					
				try {
					piano.start();
					synchronized(piano) {
						piano.wait();
					}
				} catch(InterruptedException interrupt) {
					interrupt.printStackTrace();
				}
			} else if(receiveMsg[0].equals("1") && (letsGetItClient.getThisUserSelected() != 1)) {	// electric
				InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(receiveMsg[1]));					
				try {
					electric.start();
					synchronized(electric) {
						electric.wait();
					}
				} catch(InterruptedException interrupt) {
					interrupt.printStackTrace();
				}
			} else if(receiveMsg[0].equals("2") && (letsGetItClient.getThisUserSelected() != 2)) {	// bass
				InstrumentBass bass = new InstrumentBass(Integer.parseInt(receiveMsg[1]));					
				try {
					bass.start();
					synchronized(bass) {
						bass.wait();
					}
				} catch(InterruptedException interrupt) {
					interrupt.printStackTrace();
				}
			} else if(receiveMsg[0].equals("3") && (letsGetItClient.getThisUserSelected() != 3)) {	// drum
				InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(receiveMsg[1]));					
				try {
					drum.start();
					synchronized(drum) {
						drum.wait();
					}
				} catch(InterruptedException interrupt) {
					interrupt.printStackTrace();
				}
			}
		}
	}
}
