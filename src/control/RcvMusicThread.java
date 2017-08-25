package control;

import java.io.IOException;

public class RcvMusicThread extends Thread {
	private MusicPlayClient musicClient;
	private String musicData;
	
	public void getMusicData(String musicData) {
		this.musicData = musicData;
	}
	
	public String setMusicData() {
		return musicData;
	}
	
	public RcvMusicThread(MusicPlayClient musicClient) {
		this.musicClient = musicClient;
	}

	public void run() {
		String message = null;
		String[] receiveMsg = null;
		boolean isStop = false;
		
		while(!isStop) {
			try {
				message = (String)musicClient.getOis().readObject();
				receiveMsg = message.split("#");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				isStop = true;
			} catch (IOException e) {
				e.printStackTrace();
				isStop = true;
			} 
			System.out.println(receiveMsg[0] + "#" + receiveMsg[1]);
			
			// 어떤 악기에서 보냈는지 판별하는 if문
			if(receiveMsg[0].equals("0")) {		// piano
				getMusicData(receiveMsg[1]);
			} else if(receiveMsg[0].equals("1")) {	// electric
				
			} else if(receiveMsg[0].equals("2")) {	// bass
				
			} else if(receiveMsg[0].equals("3")) {	// drum
				
			}
		}
	}
}
