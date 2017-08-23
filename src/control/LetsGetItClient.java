package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class LetsGetItClient extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	int index;
	JPanel p, p1, p2, p3, p4, p5, p6, p_;
	JButton ok, send, b1, b2;
	JButton pianoBtn, electricBtn, bassBtn, drumBtn; // Instrument Button
	JTextArea ta;
	JTextField tf;
	JScrollPane jsp;
	JLabel sheet_la, blank;
	JComboBox<String> sheet;
	String sheet_name[] = { "곰세마리", "나비야", "학교종이 땡땡땡", "버즈-겁쟁이", "메모" };
	MusicPlayClient musicPlayClient;
	
	
	// TODO 서버가 지정한 악보 데이터로 바꿔야함.
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

	public LetsGetItClient() {

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

		p.add(p1);

		// Chatting

		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();

		p2.setLayout(new BorderLayout());
		p3.setLayout(new BorderLayout());
		p3.setBackground(new Color(246, 246, 246));
		p4.setLayout(new BorderLayout());
		p5.setLayout(new BorderLayout());
		p5.setBackground(new Color(246, 246, 246));
		p6.setLayout(new BorderLayout());

		Font font = new Font("Courier", Font.PLAIN, 23);

		ta = new JTextArea("", 30, 20);
		ta.setFont(font);

		tf = new JTextField(20);
		tf.setFont(font);

		send = new JButton("입력");
		b1 = new JButton("파일선택");
		b2 = new JButton("파일전송");

		p4.add("West", tf);
		p4.add("East", send);
		p2.add("South", p4);

		p5.add("West", b1);
		p5.add("East", b2);
		p3.add("West", p5);
		p2.add("North", p3);

		ta.setLineWrap(true);
		jsp = new JScrollPane(ta);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		p6.add(jsp);
		p2.add(p6);

		// p3 > West > p5 > North > b1,b2
		// p4 > South > tf,send
		// p6 > Center > ta
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
		addGrid(gbl, gbc, p2, 5, 1, 1, 5, 0.5, 5);

		pack();

		setVisible(true);
		setBounds(115, 50, 1670, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// key event

		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					tf.setText("");
					break;
				}

			}
		});
		ok.addActionListener(this);
		tf.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		send.addActionListener(this);
		pianoBtn.addActionListener(this);			// Piano Button
		electricBtn.addActionListener(this);		// Electric Button
		bassBtn.addActionListener(this);			// Bass Button
		drumBtn.addActionListener(this);			// Drum Button
	}

	// action Event
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = tf.getText();

		// TODO 서버에서 선택한 악보 값 넘겨 받는 걸로 바꿔야할듯
		//LetsGetItServer lgis = new LetsGetItServer();// 서버에서 악보를 선택했을 때를 알리기 위해 사용	lgis.checkOk
		
		boolean onlyTestBooleanValue = true;
		
		if (obj == ok) {
			onlyTestBooleanValue = true;
		}
		
		if (obj == send || obj == tf) {
			String str = tf.getText();
			if (!str.equals("")) {
				ta.append(" >> " + str + "\n");
				tf.setText("");
			}
		}

		// 서버가 악보를 선택하고 사용자가 악기를 클릭했을 때
		if (obj == pianoBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 0);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
		}
		if (obj == electricBtn && onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 1);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
		} 
		if (obj == bassBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 2);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
		}
		if (obj == drumBtn &&  onlyTestBooleanValue == true) {
			try{
	            musicPlayClient = new MusicPlayClient(getIndex(), 3);      // 踰꾪듉 �겢由� �떆 MusicPlay �떎�뻾
	         }catch(IOException ee){
	            ee.printStackTrace();
	         }
		}

	}
}