package control;

public class SndChatThread {
	LetsGetItServer server;
	
	public SndChatThread(String str1, String str2) {
		if(str2.equals("exit")) {
			server.getThreadList().remove(this);
			System.out.println(str1 + "님이 정상적으로 종료하셨습니다.(Server)");
		} else {
			// 지금은 채팅 데이터에 사용자가 #을 입력할 경우, 뒤에 다 잘림.
			server.getChatArea().append(str1 + " >> " + str2 + "\n");
		}
	}
}