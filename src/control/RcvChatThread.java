package control;

import java.io.IOException;
import java.util.ArrayList;

public class RcvChatThread extends Thread {
	private LetsGetItClient client;
	SheetDrawing sd;
	
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
			}
			else if(receiveMsg[0].equals("index")){
				System.err.println("index !!! ");
				int index = Integer.parseInt(receiveMsg[1]);
				client.setIndex(index);
			}
			/*else if(receiveMsg[0].equals("xypoint")){
				System.err.println("xypoint");
				int XC = Integer.parseInt(receiveMsg[1]);
				int YC = Integer.parseInt(receiveMsg[2]);
				int XD = Integer.parseInt(receiveMsg[3]);
				int YD = Integer.parseInt(receiveMsg[4]);
				if(sd.getPaint() == true){
					sd.g2.drawLine(XC, YC, XD, YD);
				}
			}*/
			else {
				client.getChatArea().append(receiveMsg[0] + 
						" : " + receiveMsg[1] + System.getProperty("line.separator"));
				client.getChatArea().setCaretPosition(client.getChatArea().getDocument().getLength());				
			}
		}
	}
}
