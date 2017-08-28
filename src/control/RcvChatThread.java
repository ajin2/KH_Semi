package control;

public class RcvChatThread {
	LetsGetItClient client;
	String[] receiveMsg;
	
	public RcvChatThread(String message) {
		receiveMsg = message.split("#");
		
		System.out.println("RcvChatThread : " + message);
		
		if(receiveMsg[2].equals("exit")) {
			if(receiveMsg[1].equals(client.getId())) {
				client.exit();
			} else {
				client.getChatArea().append(receiveMsg[1] +"님이 종료 하셨습니다.(Client)");
				client.getChatArea().setCaretPosition(
						client.getChatArea().getDocument().getLength());
			}
		} else {
			System.out.println(receiveMsg[0] + "\n"+ receiveMsg[1]+ "\n" + receiveMsg[2]);
			client.getChatArea().append("11111111111111111111111");
			client.getChatArea().append(receiveMsg[1] + " : " + receiveMsg[2] + System.getProperty("line.separator"));
			client.getChatArea().setCaretPosition(client.getChatArea().getDocument().getLength());				
		}
	}
	
}
