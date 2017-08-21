package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LetsGetItClient extends JFrame /*implements ActionListener */{
	private JPanel p,p1,p2,p3,pall;
	private JButton ok, chat,btn1,btn2,btn3,btn4;
	private JLabel sheet_la,blank;
	private JComboBox sheet;
	private String sheet_name[] = {"곰세마리","�굹鍮꾩빞","�븰援먯쥌�씠 �븸�븸�븸","踰꾩쫰-寃곸웳�씠","硫붾え"};
	
	public LetsGetItClient() {
		// �븙蹂�
		p = new JPanel();
		p.setLayout(new GridLayout(1,4,5,5));
		p1 = new JPanel();

		blank = new JLabel();
		sheet_la = new JLabel("�븙蹂� : ");
		sheet = new JComboBox(sheet_name);
		ok = new JButton("�꽑�깮");
				
		p1.add(sheet_la);
		p1.add(sheet);
		p1.add(ok);

		p.add(p1);
		p.add(blank);
		p.add(blank);
		p.add(blank);
		add(p,"North");
				
		// �븙湲�
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

		// 梨꾪똿
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,1));

		chat = new JButton("梨꾪똿");
		p3.add(chat);

		// �븙湲� + 梨꾪똿
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
		
		MusicPlay musicPlay = new MusicPlay();			// 繹먲옙占쏙옙占쎌깈, 占쎌�占쎌굺沃섓옙 �뤃�뗭겱
		Chatting chatting = new Chatting();				// 占쎈립占쎄맒筌ｏ옙 �뤃�뗭겱
		Help help = new Help();							// 占쎌몖占쎌삺占쎌겫 �뤃�뗭겱
		LetsGetIt letsGetIt = new LetsGetIt();			// �⑤벏�꽰 �뤃�뗭겱
		
		new LetsGetItClient();
	}
}
