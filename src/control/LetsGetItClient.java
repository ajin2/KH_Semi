package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LetsGetItClient extends JFrame /*implements ActionListener */{
	private JPanel p,p1,p2,p3,pall;
	private JButton ok, chat,btn1,btn2,btn3,btn4;
	private JLabel sheet_la,blank;
	private JComboBox sheet;
	private String sheet_name[] = {"곰세마리","나비야","학교종이 땡땡땡","버즈-겁쟁이","메모"};
	
	public LetsGetItClient() {
		// 악보
		p = new JPanel();
		p.setLayout(new GridLayout(1,4,5,5));
		p1 = new JPanel();

		blank = new JLabel();
		sheet_la = new JLabel("악보 : ");
		sheet = new JComboBox(sheet_name);
		ok = new JButton("선택");
				
		p1.add(sheet_la);
		p1.add(sheet);
		p1.add(ok);

		p.add(p1);
		p.add(blank);
		p.add(blank);
		p.add(blank);
		add(p,"North");
				
		// 악기
		p2 = new JPanel();
		p2.setLayout(new GridLayout(2,2,10,10));
			
		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
				
		p2.add(btn1);
		p2.add(btn2);
		p2.add(btn3);
		p2.add(btn4);
		add(p2);

		// 채팅
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,1));

		chat = new JButton("채팅");
		p3.add(chat);

		// 악기 + 채팅
		pall = new JPanel();
		pall.setLayout(new GridLayout(1,2,10,10));
				
		pall.add(p2);
		pall.add(p3);
		add(pall);
				
				
		setVisible(true);
		setBounds(100,50,1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
public static void main(String[] args) {
		
		MusicPlay musicPlay = new MusicPlay();			// 源����샇, �쑀�삙誘� 援ы쁽
		Chatting chatting = new Chatting();				// �븳�긽泥� 援ы쁽
		Help help = new Help();							// �쑄�옱�쁺 援ы쁽
		LetsGetIt letsGetIt = new LetsGetIt();			// 怨듯넻 援ы쁽
		
		new LetsGetItClient();
	}
}
