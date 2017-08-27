package control;

import java.io.*;
import java.net.Socket;

public class SndMusicThread implements Runnable {
	private Socket socket;
	private LetsGetItServer server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private SndChatThread sndChatThread;
	
	public SndMusicThread(LetsGetItServer server) {
		this.server = server;
	}
	
	public synchronized void run() {
		boolean isStop = false;
		
		try {
			socket = server.getSocket();
	//		ois = new ObjectInputStream(socket.getInputStream());
		//	oos = new ObjectOutputStream(socket.getOutputStream());
			String message = null;
			
			while(!isStop) {
				message = (String)ois.readObject();
				String[] str = message.split("#");
				
				
				broadCasting(message);
		
				// 어떤 악기에서 보냈는지 판별하는 if문
				if(str[0].equals("0")) {		// piano
					InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(str[1]));					
					try {
						piano.start();
						synchronized(piano) {
							piano.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(str[0].equals("1")) {	// electric
					InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(str[1]));					
					try {
						electric.start();
						synchronized(electric) {
							electric.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(str[0].equals("2")) {	// bass
					InstrumentBass bass = new InstrumentBass(Integer.parseInt(str[1]));					
					try {
						bass.start();
						synchronized(bass) {
							bass.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(str[0].equals("3")) {	// drum
					InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(str[1]));					
					try {
						drum.start();
						synchronized(drum) {
							drum.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				}
			

				if(sndChatThread.getUserFinish() == true) {
					isStop = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void broadCasting(String message) throws IOException {
		for(SndMusicThread mt : server.getMusickList()) {
			mt.send(message);
		}
	}
	
	public void send(String message) throws IOException {
		oos.writeObject(message);
	}

}
