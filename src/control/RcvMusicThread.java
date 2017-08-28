package control;

public class RcvMusicThread extends Thread {
	private LetsGetItClient client;
	String[] receiveMsg;
	
	public RcvMusicThread(String message) {
		receiveMsg = message.split("#");
		
		if(receiveMsg[1].equals("0") && (client.getThisUserSelected() != 0)) {		// piano
			InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(receiveMsg[2]));					
			try {
				piano.start();
				synchronized(piano) {
					piano.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(receiveMsg[1].equals("1") && (client.getThisUserSelected() != 1)) {	// electric
			InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(receiveMsg[2]));					
			try {
				electric.start();
				synchronized(electric) {
					electric.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(receiveMsg[1].equals("2") && (client.getThisUserSelected() != 2)) {	// bass
			InstrumentBass bass = new InstrumentBass(Integer.parseInt(receiveMsg[2]));					
			try {
				bass.start();
				synchronized(bass) {
					bass.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(receiveMsg[1].equals("3") && (client.getThisUserSelected() != 3)) {	// drum
			InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(receiveMsg[2]));					
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