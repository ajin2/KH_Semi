package control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

import javax.swing.JLabel;

public class SndThreadControl implements Runnable {
	private Socket socket;
	private LetsGetItServer server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	SheetDrawing sd;
	private SheetMusic sm;
	
	public SndThreadControl(LetsGetItServer server) {
		this.server = server;
	}
	
	public void run() {
		boolean isStop = false;
		try {
			socket = server.getSocket();
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			String message = null;
			
			while(!isStop) {
				message = (String)ois.readObject();
				String[] str = message.split("#");

				if(str[0].equals("chat")) {
					// 대화 내용에 #이 존재할 수 있기 때문에 그대로 다시 문자열로 보내고 클래스에서 처리
					if(str[2].equals("exit")) {
						server.getThreadList().remove(this);
						System.out.println(str[1] + "님이 정상적으로 종료하셨습니다.(Server)");
						isStop = true;
					} else {
						// 지금은 채팅 데이터에 사용자가 #을 입력할 경우, 뒤에 다 잘림.
						System.out.println(str[1] + str[2]);
						server.getChatArea().append(str[1] + " >> " + str[2] + "\n");
					}
				} else if(str[0].equals("music")) {
					if(str[1].equals("0")) {		// piano
						InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(str[2]));					
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					} else if(str[1].equals("1")) {	// electric
						InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(str[2]));					
						try {
							electric.start();
							synchronized(electric) {
								electric.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					} else if(str[1].equals("2")) {	// bass
						InstrumentBass bass = new InstrumentBass(Integer.parseInt(str[2]));					
						try {
							bass.start();
							synchronized(bass) {
								bass.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					} else if(str[1].equals("3")) {	// drum
						InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(str[2]));					
						try {
							drum.start();
							synchronized(drum) {
								drum.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
				} else if(str[0].equals("draw")) {
					/* 자기 자신의 그림판에도 그리기 */
					// 서버가 보낸 draw 정보
					int XC = Integer.parseInt(str[1]);
					int YC = Integer.parseInt(str[2]);
					int XD = Integer.parseInt(str[3]);
					int YD = Integer.parseInt(str[4]);
					String rgb = str[5];
					int width = Integer.parseInt(str[6]);
					System.err.println(XC+", "+YC+", "+XD+", "+YD);
				
					// 그림판 정보 얻어오기
					SheetDrawing sheetDrawing = server.getSheetDrawing();
					BufferedImage image = sheetDrawing.getbImage();
					Color color = new Color(Integer.parseInt(rgb));
					JLabel component = sheetDrawing.getImageLabel();
					System.err.println(sheetDrawing);
					
					// 그리기 및 화면갱신
					DrawUtils.drawLineNRefresh(image, color, width, XC, YC, XD, YD, component);
				} else if(str[0].equals("ready")) {
					
				} else if(str[0].equals("Allc")){
					server.getSheetDrawing().Allc(SheetMusic.f);
				}
				broadCasting(message);
			}
			server.getThreadList().remove(this);
			System.out.println(server.getId() + "님이 정상적으로 종료하셨습니다.2");
		} catch (IOException e) {
			isStop = true;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			isStop = true;
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				broadCasting("관리자가 퇴장했습니다");
				
				if(ois != null) 		ois.close();
				if(oos != null)			oos.close();
				if(socket != null)		socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void broadCasting(String message) throws IOException {
		for(SndThreadControl tc : server.getThreadList()) {
			tc.send(message);
		}
	}
	
	public void send(String message) throws IOException {
		oos.writeObject(message);
	}

}
