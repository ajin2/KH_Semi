package control;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;
import java.net.Socket;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.sound.midi.*;

public class MusicPlayClient extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu mdrawing;
	JMenuItem msheet, mmemo, msave;
	SheetSave sheetsave;
	Instrument instrument;
	InstrumentPiano piano;
	
	// 악보
	int index;
	Image img = null, chimg;
	JPanel psheet;
	SheetMusic sm;
	
	// 악기
	int instrumentNum;
	long keyPressedMillis;
	Image instrumentImg = null, instruimg;
	JPanel instruPanel, buttonPanel;
	JButton DolemiBtn[];
	
	// Network
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	String keyData;
	private int port;
	private String ip;
	
	// Network Instrument 
	LetsGetItClient letsGetItClient = new LetsGetItClient();
		
	public void init() {
		psheet = new JPanel();
		psheet.setLayout(new GridLayout(1, 1));
		chimg = img.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel sheet = new JLabel(new ImageIcon(chimg));
		psheet.add(sheet);
	}
	
	public void instrumentInit() {
		instruPanel = new JPanel();
		instruimg = instrumentImg.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel instru = new JLabel(new ImageIcon(instruimg));
		instruPanel.add(instru);
	}
		
	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c, int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	/*public void musicPlayInit() throws IOException {
		socket = new Socket(ip, port);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
		RcvMusicThread rmt = new RcvMusicThread(this);
		Thread musicThread = new Thread(rmt);
		musicThread.start();
	}*/
	
	public MusicPlayClient(int index, int instrumentNum) throws IOException {
		// MenuBar
		mb = new JMenuBar();
		setJMenuBar(mb);

		mdrawing = new JMenu("Drawing");
		mb.add(mdrawing);

		buttonPanel = new JPanel();
		
		msheet = new JMenuItem("On Sheet");
		mmemo = new JMenuItem("New Memo");
		msave = new JMenuItem("Save");

		mdrawing.add(msheet);
		mdrawing.add(mmemo);
		mdrawing.add(msave);

		// Sheet Print
		sm = new SheetMusic();
		img = ImageIO.read(sm.showSheet(index));
		init();
		// Instrument Print 
		instrument = new Instrument();
		instrumentImg = ImageIO.read(instrument.instrumentShow(instrumentNum));
		instrumentInit();

		// Instruments Button
		DolemiBtn = new JButton[8];
		DolemiBtn[0] = new JButton("도");
		DolemiBtn[1] = new JButton("레");
		DolemiBtn[2] = new JButton("미");
		DolemiBtn[3] = new JButton("파");
		DolemiBtn[4] = new JButton("도");
		DolemiBtn[5] = new JButton("레");
		DolemiBtn[6] = new JButton("미");
		DolemiBtn[7] = new JButton("파");
		for(int i = 0; i < 8; i++) {
			buttonPanel.add(DolemiBtn[i]);
		}
		this.instrumentNum = instrumentNum;	// 악기 종류 저장
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		addGrid(gbl, gbc, psheet, 0, 0, 1, 1, 1, 1);
		addGrid(gbl, gbc, instruPanel, 0, 1, 1, 1, 1, 4);
		add(buttonPanel);
		
		pack();

		setVisible(true);
		setBounds(118, 50, 1237, 893);
		setAlwaysOnTop(true);
		setResizable(false);
		
		msheet.addActionListener(this);
		mmemo.addActionListener(this);
		msave.addActionListener(this);
		
		
		// TODO 코드 piano뿐만 아니라 bass drum elec까지 넣어야해서 코드 정리 필요함.
		// Key Event
		buttonPanel.requestFocus();
		buttonPanel.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == e.VK_A) {
						
						InstrumentPiano piano = new InstrumentPiano(0);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_S) {
						InstrumentPiano piano = new InstrumentPiano(1);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_D) {
						InstrumentPiano piano = new InstrumentPiano(2);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_F) {
						InstrumentPiano piano = new InstrumentPiano(3);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_G) {
						InstrumentPiano piano = new InstrumentPiano(4);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_H) {
						InstrumentPiano piano = new InstrumentPiano(5);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_J) {
						InstrumentPiano piano = new InstrumentPiano(6);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
					if(e.getKeyCode() == e.VK_K) {
						InstrumentPiano piano = new InstrumentPiano(7);
						try {
							piano.start();
							synchronized(piano) {
								piano.wait();
							}
						} catch(InterruptedException interrupt) {
							interrupt.printStackTrace();
						}
					}
				}
			}
		);
		
		// Action Event
		for(int i = 0; i < 8; i++) {
			DolemiBtn[i].addActionListener(this);
		}
	}	// 생성자
	
	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();
		
		// MenuItem
		if(obj == msheet){
			
		}
		if(obj == mmemo){
			
		}
		if(obj == msave){
	//		sheetsave = new sheetsave();
		}
		
		// play instrument
		for(int i = 0; i < 8; i++) {
			if(obj == DolemiBtn[i]) {
				if(instrumentNum == 0) {
					InstrumentPiano piano = new InstrumentPiano(i);
					
					try {
						piano.start();
						synchronized(piano) {
							piano.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(instrumentNum == 1) {
					InstrumentElectric electric = new InstrumentElectric(i);
					try {
						electric.start();
						synchronized(electric) {
							electric.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(instrumentNum == 2) {
					InstrumentBass bass = new InstrumentBass(i);
					try {
						bass.start();
						synchronized(bass) {
							bass.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				} else if(instrumentNum == 3) {
					InstrumentDrum drum = new InstrumentDrum(i);
					try {
						drum.start();
						synchronized(drum) {
							drum.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					}
				}
			}
		}
	}
	
	public ObjectInputStream getOis() {
		return ois;
	}
	
	public void musicBuffer(String keyData) {
		
		this.keyData = keyData;
		String stringKindsInstrument = String.valueOf(instrumentNum);
		
		// 키 데이터가 비어있지 않으면
		if (!keyData.equals("")) {
			try {
				oos.writeObject(stringKindsInstrument + "#" + keyData);
			} catch(IOException exception) {
				exception.printStackTrace();
			}
			// 보낸 데이터 비워주기
			this.keyData = null;
		}	
	}
}
