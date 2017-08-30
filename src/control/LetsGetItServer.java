package control;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LetsGetItServer extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	static int index;
	JPanel p, p1, p_;
	JPanel chatPanel, p3, writePanel, p5, readPanel; // Chatting Panel
	JButton ok, send;
	JButton pianoBtn, electricBtn, bassBtn, drumBtn; // Instrument Button
	JTextArea chatArea;
	JTextField chatField;
	JScrollPane jsp;
	JLabel sheet_la, blank;
	static JComboBox<String> sheet;
	String sheet_name[] = { "곰세마리", "나비야", "학교종이 땡땡땡", "올챙이와 개구리", "퐁당퐁당" };

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

	// Network Module
	private ArrayList<SndThreadControl> threadList;
	private Socket socket;
	private String id = "관리자";
	ServerSocket serverSocket;
	
	SndThreadControl sndThreadControl = null;
	private SheetDrawing sheetDrawing;
	
	public static int getIndex() {
		return index = sheet.getSelectedIndex();
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
		threadList = new ArrayList<SndThreadControl>();
		serverSocket = new ServerSocket(port);
		boolean isStop = false;

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
		ok.setEnabled(true);

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

		send = new JButton(new ImageIcon(ImageIO.read(sendbtn)));
		send.setMargin(new Insets(0, 0, 0, 0));

		writePanel.add("West", chatField);
		writePanel.add("East", send);
		chatPanel.add("South", writePanel);

		
		writePanel.add("East", send);
		chatPanel.add("South", writePanel);

		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		jsp = new JScrollPane(chatArea);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		readPanel.add(jsp);
		chatPanel.add(readPanel);

		// p3 > West > p5 > North > b1,b2
		// p4 > South > tf,send
		// p6 > Center > chatArea
		// ----------------------------------------------------------

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
		send.addActionListener(this);
		mhelp.addActionListener(this);
		mhelpview.addActionListener(this);

		// Newtwork Module
		while (!isStop) {
			System.out.println("Server read...");
			socket = serverSocket.accept();

			sndThreadControl = new SndThreadControl(this);
			threadList.add(sndThreadControl);
			Thread t3 = new Thread(sndThreadControl);
			t3.start();
		}
	}

	// action Event
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		 String msg = chatField.getText();

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
					sndThreadControl.broadCasting("chat" + "#" + id + "#" + msg); 
				} catch(IOException exception) {
					exception.printStackTrace();
				}
				chatField.setText("");
				chatArea.append(id + " >> " + msg + "\n");
			}
		}
		
		if (obj == ok) {
			try {
				mp = new MusicPlay(getIndex(), this); 
				if(sndThreadControl != null){
					sndThreadControl.broadCasting("sheet" + "#" + getIndex());
				}
				chatArea.append("Selected a Music Sheet" + "\n");
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}
	}

	public ArrayList<SndThreadControl> getThreadList() {
		return threadList;
	}

	public void exit() {
		System.exit(0);
	}

	public Socket getSocket() {
		return socket;
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}

	public String getId() {
		return id;
	}

	public SndThreadControl getSndThreadControl() {
		return sndThreadControl;
	}

	public SheetDrawing getSheetDrawing() {
		return sheetDrawing;
	}

	public void setSheetDrawing(SheetDrawing sheetDrawing) {
		this.sheetDrawing = sheetDrawing;
	}
}