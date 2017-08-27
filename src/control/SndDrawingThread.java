package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SndDrawingThread implements Runnable {
	private Socket socket;
	private LetsGetItServer server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean userFinish;		// 유저가 나갔는지 안나갔는지 확인하는 변수
	
	public void setUserFinish(boolean userFinish) {
		this.userFinish = userFinish;
	}
	
	public boolean getUserFinish() {
		return userFinish;
	}
	
	public SndDrawingThread(LetsGetItServer server) {
		this.server = server;
	}
	
	public synchronized void run() {
		boolean isStop = false;
		try {
			setUserFinish(false);		// 유저 나가기 전엔 false
			socket = server.getSocket();
//			ois = new ObjectInputStream(socket.getInputStream());
//			oos = new ObjectOutputStream(socket.getOutputStream());
//			String message = null;
//			
//			while(!isStop) {
//				message = String.valueOf(server.getIndex());
//				System.out.println(server.getIndex());
////				broadCasting(message);
//				if(message != null){
//					isStop = true;
//				}
//			}
//			setUserFinish(true);	// 유저 나가면 true
//			server.getDrawingList().remove(this);
//			System.out.println(socket.getInetAddress() + "정상적으로 종료하셨습니다.(Server)");
		} catch(Exception exception) {
			server.getDrawingList().remove(this);
			System.out.println(socket.getInetAddress() + "님이 비정상적으로 종료되셨습니다.(Server)");
		} finally {
			try {
				if(ois != null)			ois.close();
				if(oos != null)			oos.close();
				if(socket != null)		socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void broadCasting(String message) throws IOException {
		for(SndDrawingThread bt : server.getDrawingList()) {
			bt.send(message);
		}
	}
	
	public void send(String message) throws IOException {
		oos.writeObject(message);
	}
}
