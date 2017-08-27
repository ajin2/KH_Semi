package control;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.*;

public class LetsGetItServer extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	int index;
	JPanel p, p1, p_;
	JPanel chatPanel, p3, writePanel, p5, readPanel; 					// Chatting Panel
	JButton ok, send, b1, b2;
	JButton pianoBtn, electricBtn, bassBtn, drumBtn; 	// Instrument Button
	JTextArea chatArea;
	JTextField chatField;
	JScrollPane jsp;
	JLabel sheet_la, blank;
	JComboBox<String> sheet;
	String sheet_name[] = { "곰세마리", "나비야", "학교종이 땡땡땡", "버즈-겁쟁이", "메모" };
	boolean checkOk = false; // 클라이언트에게 악보를 설정했다고 알려주는 변수.

	JMenuBar mb;
	JMenu mhelp, mselect;
	JTextArea ta;
	JMenuItem mhelpview;
	Dimension dm;

	HelpSub hs;

	// Network Module
	private ArrayList <SndChatThread> sendList;
	private ArrayList <SndMusicThread> musicList;
	private Socket socket;
	private String id = "관리자";
	ServerSocket serverSocket;

	public int getIndex() {
		index = sheet.getSelectedIndex();
		return index;
	}

	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c, int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	public LetsGetItServer(int port) throws IOException {
		// Network Module
		sendList = new ArrayList <SndChatThread> ();
		musicList = new ArrayList <SndMusicThread> ();
		serverSocket = new ServerSocket(port);
		SndChatThread sndChatThread = null;
		SndMusicThread sndMusicThread = null;
		boolean isStop = false;
		
		// GridBagLayout
		// SheetName, Button
		p_ = new JPanel();
		p_.setBackground(new Color(246, 246, 246));
		blank = new JLabel();

		p_.add(blank);

		p = new JPanel();
		p.setBackground(new Color(246, 246, 246));
		p.setLayout(new GridLayout(1, 4, 5, 5));
		p1 = new JPanel();
		p1.setBackground(new Color(246, 246, 246));

		sheet_la = new JLabel("악보 : ");
		sheet = new JComboBox<String>(sheet_name);
		ok = new JButton("선택");

		p1.add(sheet_la);
		p1.add(sheet);
		p1.add(ok);
		p.add(p1);

		// Chatting Panel
		chatPanel = new JPanel();
		p3 = new JPanel();
		writePanel = new JPanel();
		p5 = new JPanel();
		readPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p3.setBackground(new Color(246, 246, 246));
		writePanel.setLayout(new BorderLayout());
		p5.setLayout(new BorderLayout());
		p5.setBackground(new Color(246, 246, 246));
		readPanel.setLayout(new BorderLayout());

		Font font = new Font("Courier", Font.PLAIN, 23);
		chatArea = new JTextArea("", 30, 20);
		chatArea.setFont(font);
		chatField = new JTextField(20);
		chatField.setFont(font);

		send = new JButton("입력");
		b1 = new JButton("파일선택");
		b2 = new JButton("파일전송");
		p5.add("West", b1);
		p5.add("East", b2);
		p3.add("West", p5);
		chatPanel.add("North", p3);

		writePanel.add("West", chatField);
		writePanel.add("East", send);
		chatPanel.add("South", writePanel);
		
		chatArea.setLineWrap(true);
		jsp = new JScrollPane(chatArea);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		readPanel.add(jsp);
		chatPanel.add(readPanel);

		// p3 > West > p5 > North > b1,b2
		// p4 > South > tf,send
		// p6 > Center > chatArea
		// ----------------------------------------------------------

		pianoBtn = new JButton("Piano");
		electricBtn = new JButton("Electric");
		bassBtn = new JButton("Bass");
		drumBtn = new JButton("Drum");

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		addGrid(gbl, gbc, p, 0, 0, 4, 1, 0.5, 0);
		addGrid(gbl, gbc, p_, 5, 0, 1, 1, 0.5, 0);
		addGrid(gbl, gbc, pianoBtn, 0, 1, 2, 2, 1, 2);
		addGrid(gbl, gbc, electricBtn, 2, 1, 2, 2, 1, 2);
		addGrid(gbl, gbc, bassBtn, 0, 4, 2, 2, 1, 2);
		addGrid(gbl, gbc, drumBtn, 2, 4, 2, 2, 1, 2);
		addGrid(gbl, gbc, chatPanel, 5, 1, 1, 5, 0.5, 5);

		pack();

		mb = new JMenuBar();
		setJMenuBar(mb);
		mhelp = new JMenu("도움말");
		mb.add(mhelp);
		mhelpview = new JMenuItem("도움말 보기");
		mhelp.add(mhelpview);
		hs = new HelpSub();	      

		setVisible(true);
		setBounds(115, 50, 1670, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// key event
		chatField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					chatField.setText("");
					break;
				}
			}
		});
		
		hs.close.addActionListener(this);
		ok.addActionListener(this);
		chatField.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		send.addActionListener(this);
		mhelp.addActionListener(this);
		mhelpview.addActionListener(this);

		
		// Newtwork Module
		while(!isStop) {
			System.out.println("Server read...");
			socket = serverSocket.accept();
			
			sndChatThread = new SndChatThread(this);
			sndMusicThread = new SndMusicThread(this);
			
			sendList.add(sndChatThread);
			musicList.add(sndMusicThread);
			
			Thread t1 = new Thread(sndChatThread);
			Thread t2 = new Thread(sndMusicThread);
			
			t1.start();
			t2.start();
		}
	}

	// action Event
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = chatField.getText();

		if (obj == mhelpview) {
			hs.setSize(500, 500);
			hs.setVisible(true);
		} else if (obj == hs.close) {
			hs.dispose();
		}

		if (obj == ok) {
			try {
				mp = new MusicPlay(getIndex()); // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
			} catch (IOException ee) {
				ee.printStackTrace();
			}

			// 클라이언트에게 버튼이 눌렸는지 알려주는 boolean값
			checkOk = true;
		}
	}
	
	public ArrayList< SndChatThread > getChatList() {
		return sendList;
	}
	
	public ArrayList< SndMusicThread > getMusickList() {
		return musicList;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}
	
	public String getId() {
		return id;
	}
}