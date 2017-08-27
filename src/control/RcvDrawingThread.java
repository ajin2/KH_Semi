package control;

import java.io.IOException;

public class RcvDrawingThread extends Thread {
	private LetsGetItClient client;
	
	public RcvDrawingThread(LetsGetItClient clinet) {
		this.client = clinet;
	}
	
	public void run() {
		String message = null;
		boolean isStop = false;
		
		while(!isStop) {
			try {
				message = (String)client.getOis().readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isStop = true;
			} catch (IOException e) {
				e.printStackTrace();
				isStop = true;
			}
			System.out.println(message);
		}
	}
}
