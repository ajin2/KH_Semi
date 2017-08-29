package control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;

public class RcvThreadControl  extends Thread {
	private LetsGetItClient client;
	SheetDrawing sd;
	SheetMusic sm;
	int index;
	private ArrayList <MusicPlayClient> musicPlayClientList = new ArrayList<>();
	
	public RcvThreadControl(LetsGetItClient clinet) {
		this.client = clinet;
	}
	
	public void run() {
		String message = null;
		String[] receiveMsg = null;
		boolean isStop = false;
		
		while(!isStop) {
			try {
				message = (String)client.getOis().readObject();
				receiveMsg = message.split("#");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				isStop = true;
			} catch (IOException e) {
				e.printStackTrace();
				isStop = true;
			}
			
			// Chat
			if(receiveMsg[0].equals("chat")) {
				if(receiveMsg[2].equals("exit")) {
					if(receiveMsg[1].equals(client.getId())) {
						client.exit();
					} else {
						client.getChatArea().append(receiveMsg[1] +"님이 종료 하셨습니다.(Client)");
						client.getChatArea().setCaretPosition(
								client.getChatArea().getDocument().getLength());
					}
				} else {
					client.getChatArea().append(receiveMsg[1] + " : " + receiveMsg[2] + System.getProperty("line.separator"));
					client.getChatArea().setCaretPosition(client.getChatArea().getDocument().getLength());				
				} 
				
			// Music	
			} else if(receiveMsg[0].equals("music")) {
				if(receiveMsg[1].equals("0") /*&& (client.getThisUserSelected() != 0)*/) {		// piano
					InstrumentPiano piano = new InstrumentPiano(Integer.parseInt(receiveMsg[2]));					
					try {
						piano.start();
						synchronized(piano) {
							piano.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(receiveMsg[1].equals("1") /*&& (client.getThisUserSelected() != 1)*/) {	// electric
					InstrumentElectric electric = new InstrumentElectric(Integer.parseInt(receiveMsg[2]));					
					try {
						electric.start();
						synchronized(electric) {
							electric.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(receiveMsg[1].equals("2") /*&& (client.getThisUserSelected() != 2)*/) {	// bass
					InstrumentBass bass = new InstrumentBass(Integer.parseInt(receiveMsg[2]));					
					try {
						bass.start();
						synchronized(bass) {
							bass.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(receiveMsg[1].equals("3") /*&& (client.getThisUserSelected() != 3)*/) {	// drum
					InstrumentDrum drum = new InstrumentDrum(Integer.parseInt(receiveMsg[2]));					
					try {
						drum.start();
						synchronized(drum) {
							drum.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} 
				
			} else if(receiveMsg[0].equals("sheet")) {
				System.err.println("index !!! ");
				index = Integer.parseInt(receiveMsg[1]);
				client.setChoosenSheet(true);
				client.setIndex(index);
				client.getChatArea().append("Selected a Music Sheet");
			} else if(receiveMsg[0].equals("draw")) {
				System.err.println("draw !!! ");
				System.err.println("client is " + client);
				
				// 서버가 보낸 draw 정보
				int XC = Integer.parseInt(receiveMsg[1]);
				int YC = Integer.parseInt(receiveMsg[2]);
				int XD = Integer.parseInt(receiveMsg[3]);
				int YD = Integer.parseInt(receiveMsg[4]);
				String rgb = receiveMsg[5];
				int width = Integer.parseInt(receiveMsg[6]);
				System.err.println(XC+", "+YC+", "+XD+", "+YD);
			
				for(MusicPlayClient musicPlayClient : musicPlayClientList) {
					// 그림판 정보 얻어오기
					SheetDrawing sheetDrawing = musicPlayClient.getSheetDrawing();
					BufferedImage image = sheetDrawing.getbImage();
					Color color = new Color(Integer.parseInt(rgb));
					JLabel component = sheetDrawing.getImageLabel();
					System.err.println(sheetDrawing);
					
					// 그리기 및 화면갱신
					DrawUtils.drawLineNRefresh(image, color, width, XC, YC, XD, YD, component);
				}
			} else if(receiveMsg[0].equals("ready")) {
				
			} else if(receiveMsg[0].equals("Allc")){
				System.err.println("Allc !!! ");

				File f = SheetMusic.f;
				for(MusicPlayClient musicPlayClient : musicPlayClientList) {
					musicPlayClient.getSheetDrawing().Allc(f);
				}
				
			}
		}
	}
	
	/**
	 * 그리기 객체를 얻어오기 위해서 MusicPlayClient를 리스트에 저장
	 * @param musicPlayClient
	 */
	public void addMusicPlayClient(MusicPlayClient musicPlayClient) {
		musicPlayClientList.add(musicPlayClient);
	}
}
