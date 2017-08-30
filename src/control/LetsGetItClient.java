package control;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LetsGetItClient extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	static int index;
	JPanel p, p1, p_;
	JPanel chatPanel, p3, writePanel, p5, readPanel;
	JButton ok, send;
	JButton pianoBtn, electricBtn, bassBtn, drumBtn; // Instrument Button
	JTextArea chatArea;
	JTextField chatField;
	JScrollPane jsp;
	JLabel sheet_la, blank;
	JComboBox<String> sheet;
	String sheet_name[] = { "곰세마리", "나비야", "학교종이 땡땡땡", "올챙이와 개구리", "퐁당퐁당" };
	MusicPlayClient musicPlayClient;
	private int thisUserSelected;

	public static File sendbtn = new File("imgg/send.png");
	public static File Bass = new File("imgg/Bass.jpg");
	public static File Drums = new File("imgg/Drums.jpg");
	public static File Piano = new File("imgg/Piano.jpg");
	public static File Guitar = new File("imgg/Guitar.jpg");

	JMenuBar mb;
	JMenu mhelp, mselect;
	JTextArea ta;
	JMenuItem mhelpview;
	Dimension dm;

	HelpSub hs;

	/*
	 * TODO Client가 악기 선택했을 때마다 cnt++ (cnt = client의 사람인원수)가 되었을때
	 * MusicPlayClient Window가 활동되도록 수정해야함.
	 */

	// Network Module
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String id, ip;
	private int port;
	private RcvThreadControl rtc;
	private boolean choosenSheet;

	// TODO 서버가 지정한 악보 데이터로 바꿔야함.
	public static int getIndex() {
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}

	public String getID() {
		return id;
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

	public LetsGetItClient() {
	}

	public LetsGetItClient(String ip, int port, String id) throws IOException, UnknownHostException {
		// Network Module
		this.id = id;
		this.ip = ip;
		this.port = port;

		// GridBagLayout
		// SheetName, Button
		p_ = new JPanel();
		p_.setBackground(new Color(62, 60, 63));
		blank = new JLabel();
		p_.add(blank);

		p = new JPanel();
		p.setBackground(new Color(246, 246, 246));
		p.setLayout(new GridLayout(1, 4, 5, 5));
		p1 = new JPanel();
		p1.setBackground(new Color(62, 60, 63));

		sheet_la = new JLabel("악보 : ");
		sheet_la.setForeground(Color.WHITE);
		sheet_la.setFont(new Font("맑은고딕", Font.BOLD, 13));
		sheet = new JComboBox<String>(sheet_name);
		ok = new JButton("선택");
		ok.setEnabled(false);

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
		p5.setBackground(new Color(62, 60, 63));
		readPanel.setLayout(new BorderLayout());

		Font font = new Font("Courier", Font.PLAIN, 23);
		chatArea = new JTextArea("", 30, 20);
		chatArea.setBackground(new Color(62, 60, 63));
		chatArea.setForeground(Color.WHITE);
		chatArea.setFont(font);
		chatField = new JTextField(15);
		chatField.setBackground(new Color(62, 60, 63));
		chatField.setForeground(Color.WHITE);
		chatField.setFont(font);
		writePanel.add("West", chatField);

		send = new JButton(new ImageIcon(ImageIO.read(sendbtn)));
		send.setMargin(new Insets(0, 0, 0, 0));

		writePanel.add("East", send);
		chatPanel.add("South", writePanel);

		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		jsp = new JScrollPane(chatArea);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		readPanel.add(jsp);
		chatPanel.add(readPanel);

		pianoBtn = new JButton(new ImageIcon(ImageIO.read(Piano)));
		pianoBtn.setBackground(new Color(0, 0, 0));
		pianoBtn.setMargin(new Insets(-500, -500, -500, -500));
		electricBtn = new JButton(new ImageIcon(ImageIO.read(Guitar)));
		electricBtn.setBackground(new Color(0, 0, 0));
		electricBtn.setMargin(new Insets(-500, -500, -500, -500));
		bassBtn = new JButton(new ImageIcon(ImageIO.read(Bass)));
		bassBtn.setBackground(new Color(0, 0, 0));
		bassBtn.setMargin(new Insets(-500, -500, -500, -500));
		drumBtn = new JButton(new ImageIcon(ImageIO.read(Drums)));
		drumBtn.setBackground(new Color(0, 0, 0));
		drumBtn.setMargin(new Insets(-500, -500, -500, -500));

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
		mb.setBackground(new Color(62, 60, 63));

		mhelp = new JMenu("도움말");
		mb.add(mhelp);
		mhelp.setForeground(Color.WHITE);

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
		send.addActionListener(this);
		pianoBtn.addActionListener(this); // Piano Button
		electricBtn.addActionListener(this); // Electric Button
		bassBtn.addActionListener(this); // Bass Button
		drumBtn.addActionListener(this); // Drum Button
		mhelp.addActionListener(this);
		mhelpview.addActionListener(this);
	}

	// action Event
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = chatField.getText();
		sheet.setSelectedIndex(getIndex());
		
		if (obj == mhelpview) {
			hs.setBounds(115, 50, 500, 500);
			hs.setVisible(true);
		} else if (obj == hs.close) {
			hs.dispose();
		}

		if (obj == chatField || obj == send) {
			String str = chatField.getText();
			if (!str.equals("")) {
				try {
					oos.writeObject("chat" + "#" + id + "#" + msg);
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				chatField.setText("");
			}
		}

		// 서버가 악보를 선택하고 사용자가 악기를 클릭했을 때
		if(choosenSheet == true) {
			if (obj == pianoBtn) {
				try {
					musicPlayClient = new MusicPlayClient(getIndex(), 0, this);
					if(rtc != null)
						rtc.addMusicPlayClient(musicPlayClient);
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				setThisUserSelected(0);
			}
			if (obj == electricBtn) {
				try {
					musicPlayClient = new MusicPlayClient(getIndex(), 1, this); // 踰꾪듉
					if(rtc != null)
						rtc.addMusicPlayClient(musicPlayClient);
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				setThisUserSelected(1);
			}
			if (obj == bassBtn) {
				try {
					musicPlayClient = new MusicPlayClient(getIndex(), 2, this); 
					if(rtc != null)
						rtc.addMusicPlayClient(musicPlayClient);
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				setThisUserSelected(2);
			}
			if (obj == drumBtn) {
				try {
					musicPlayClient = new MusicPlayClient(getIndex(), 3, this); 
					if(rtc != null)
						rtc.addMusicPlayClient(musicPlayClient);
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				setThisUserSelected(3);
			}
		}
	}

	public void init() throws IOException {
		socket = new Socket(ip, port);
		System.out.println("connected...");
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());

		rtc = new RcvThreadControl(this);
		Thread threadControl = new Thread(rtc);
		threadControl.start();
	}

	public void exit() {
		System.exit(0);
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
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
	
	public boolean getChoosenSheet() {
		return choosenSheet;
	}
	
	public void setChoosenSheet(boolean choosenSheet) {
		this.choosenSheet = choosenSheet;
	}

}