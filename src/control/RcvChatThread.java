package control;

import java.io.*;

public class RcvChatThread extends Thread {
	private LetsGetItClient client;
	
	public RcvChatThread(LetsGetItClient clinet) {
		this.client = clinet;
	}
	
	public void run() {
		String message = null;
		String[] receiveMsg = null;
		boolean isStop = false;
		
		while(!isStop) {
			try {
				message = (String)client.getOis().readObject();
				receiveMsg = message.split(" >> ");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isStop = true;
			} catch (IOException e) {
				e.printStackTrace();
				isStop = true;
			}
			System.out.println(receiveMsg[0] + " >> " + receiveMsg[1]);
			if(receiveMsg[1].equals("exit")) {
				if(receiveMsg[0].equals(client.getId())) {
					client.exit();
				} else {
					client.getChatArea().append(receiveMsg[0] +"님이 종료 하셨습니다.(Client)");
					client.getChatArea().setCaretPosition(
							client.getChatArea().getDocument().getLength());
				}
			} else {
				client.getChatArea().append(receiveMsg[0] + 
						" : " + receiveMsg[1] + System.getProperty("line.separator"));
				client.getChatArea().setCaretPosition(client.getChatArea().getDocument().getLength());				
			}
		}
	}
}
