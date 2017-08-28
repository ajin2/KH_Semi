package control;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class LetsGetItClient extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	static int index;
	JPanel p, p1, p_;
	JPanel chatPanel, p3, writePanel, p5, readPanel;
	JButton ok, send, b1, b2;
	JButton pianoBtn, electricBtn, bassBtn, drumBtn; // Instrument Button
	JTextArea chatArea;
	JTextField chatField;
	JScrollPane jsp;
	JLabel sheet_la, blank;
	JComboBox<String> sheet;
	String sheet_name[] = { "곰세마리", "나비야", "학교종이 땡땡땡", "버즈-겁쟁이", "메모" };
	MusicPlayClient musicPlayClient;
	private int thisUserSelected;
	
	JMenuBar mb;
	JMenu mhelp, mselect;
	JTextArea ta;
	JMenuItem mhelpview;
	Dimension dm;
	
	HelpSub hs;

	/* TODO Client가 악기 선택했을 때마다 cnt++
	 *  (cnt = client의 사람인원수)가 되었을때
	 *  MusicPlayClient Window가 활동되도록 수정해야함.
	 */
	
	// Network Module
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String id, ip;
	private int port;
	
	// TODO 서버가 지정한 악보 데이터로 바꿔야함.
	public static int getIndex() {
//		index = sheet.getSelectedIndex();
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public String getID(){ return id; }

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
	
	public LetsGetItClient() {	}
	public LetsGetItClient(String ip, int port, String id) throws IOException, UnknownHostException {
		// Network Module
		this.id = id;
		this.ip = ip;
		this.port = port;
		
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
		ok.setEnabled(false);

		p1.add(sheet_la);
		p1.add(sheet);
		p1.add(ok);

		//p.add(p1);

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
		writePanel.add("West", chatField);
		
		send = new JButton("입력");
		b1 = new JButton("파일선택");
		b2 = new JButton("파일전송");
		p5.add("West", b1);
		p5.add("East", b2);
		p3.add("West", p5);
		chatPanel.add("North", p3);
		writePanel.add("East", send);
		chatPanel.add("South", writePanel);
		
		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		jsp = new JScrollPane(chatArea);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		readPanel.add(jsp);
		chatPanel.add(readPanel);

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
		hs.close.addActionListener(this);

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
		
		ok.addActionListener(this);
		chatField.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		send.addActionListener(this);
		pianoBtn.addActionListener(this);			// Piano Button
		electricBtn.addActionListener(this);		// Electric Button
		bassBtn.addActionListener(this);			// Bass Button
		drumBtn.addActionListener(this);			// Drum Button
		mhelp.addActionListener(this);
		mhelpview.addActionListener(this);

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
		
		if (obj == chatField || obj == send) {
			String str = chatField.getText();
			if (!str.equals("")) {
				try {
					oos.writeObject(id + " >> "  + msg);
				} catch(IOException exception) {
					exception.printStackTrace();
				}
				chatField.setText("");
			}
		}
		
		// Server 없이 Client Test용. 서버 통신 되면 onlyTestBooleanValue 다 지우면 됌
		boolean onlyTestBooleanValue = true;
		if (obj == ok) {
			onlyTestBooleanValue = true;
		}
		
		// 서버가 악보를 선택하고 사용자가 악기를 클릭했을 때
		if (obj == pianoBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 0);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
			setThisUserSelected(0);
		}
		if (obj == electricBtn && onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 1);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
			setThisUserSelected(1);
		} 
		if (obj == bassBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 2);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
			setThisUserSelected(2);
		}
		if (obj == drumBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 3);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
			setThisUserSelected(3);
		}
	}
	
	public void init() throws IOException {
		socket = new Socket(ip, port);
        System.out.println( "connected..." );
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
		RcvChatThread rct = new RcvChatThread(this);
//		RcvMusicThread rmt = new RcvMusicThread(this);
		
		Thread chatThread = new Thread(rct);
//		Thread musicThread = new Thread(rmt);
		
//		musicThread.start();
		chatThread.start();
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public ObjectInputStream getOis() {
		return ois;
	}
	
	public ObjectOutputStream getOos() {
		return oos;
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}
	
	public String getId() {
		return id;
	}
	
	public void setThisUserSelected(int thisUserSelected) {
		this.thisUserSelected = thisUserSelected;
	}
	
	public int getThisUserSelected() {
		return thisUserSelected;
	}
	

	
}