package control;

public class SndMusicThread {
	
	public SndMusicThread(String str1, String str2) {
		if(str1.equals("0")) {		// piano
			InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(str2));					
			try {
				piano.start();
				synchronized(piano) {
					piano.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(str1.equals("1")) {	// electric
			InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(str2));					
			try {
				electric.start();
				synchronized(electric) {
					electric.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(str1.equals("2")) {	// bass
			InstrumentBass bass = new InstrumentBass(Integer.parseInt(str2));					
			try {
				bass.start();
				synchronized(bass) {
					bass.wait();
				}
			} catch(InterruptedException interrupt) {
				interrupt.printStackTrace();
			}
		} else if(str1.equals("3")) {	// drum
			InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(str2));					
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
			