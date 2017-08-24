package control;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class BroadCastThread implements Runnable {
	private Socket socket;
	private LetsGetItServer server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private String name;
	
	public BroadCastThread(LetsGetItServer server) {
		this.server = server;
	}
	
	public synchronized void run() {
		boolean isStop = false;
		try {
			socket = server.getSocket();
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			String message = null;
			
			while(!isStop) {
				message = (String)ois.readObject();
				String[] str = message.split(" >> ");
				
				if(str[1].equals("exit")){
                    broadCasting(message);
                    isStop = true;
                }else{
                    broadCasting(message);
                    server.getChatArea().append(str[0] + " >> " + str[1]);
                }
			}
			server.getList().remove(this);
			System.out.println(socket.getInetAddress() + "정상적으로 종료하셨습니다.(Server)");
		} catch(Exception exception) {
			server.getList().remove(this);
			System.out.println(socket.getInetAddress() + "님이 비정상적으로 종료되셨습니다.(Server)");
		} finally {
			try {
				//broadCasting(socket.getInetAddress());
				if(ois != null)			ois.close();
				if(oos != null)			oos.close();
				if(socket != null)		socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void broadCasting(String message) throws IOException {
		for(BroadCastThread bt : server.getList()) {
			bt.send(message);
		}
	}
	
	public void send(String message) throws IOException {
		oos.writeObject(message);;
	}

}
