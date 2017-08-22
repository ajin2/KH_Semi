package control;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LetsGetItServer extends JFrame implements ActionListener {
	Insets insets = new Insets(0, 0, 0, 0);
	MusicPlay mp;
	SheetMusic sm;
	int index;
	JPanel p,p1,p2,p3,pall;
	JButton ok, chat,btn1,btn2,btn3,btn4;
	JLabel sheet_la,blank;
	JComboBox <String> sheet;
	String sheet_name[] = {"곰세마리","나비야","학교종이 땡땡땡","버즈-겁쟁이","메모"};
	
	public int getIndex(){
		index = sheet.getSelectedIndex();
		return index;
	}
	
	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c,  
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      gbl.setConstraints(c, gbc);
      add(c);
	}
	
	public LetsGetItServer() {
		
		// GridBagLayout
		
		// 악보
			p = new JPanel();
			p.setLayout(new GridLayout(1,4,5,5));
			p1 = new JPanel();

			blank = new JLabel();
			sheet_la = new JLabel("악보 : ");
			sheet = new JComboBox <String> (sheet_name);
			ok = new JButton("선택");
					
			p1.add(sheet_la);
			p1.add(sheet);
			p1.add(ok);

			p.add(p1);
			p.add(blank);
			p.add(blank);
			p.add(blank);
         
         btn1 = new JButton("Piano");
         btn2 = new JButton("Electric");
         btn3 = new JButton("Bass");
         btn4 = new JButton("Drum");
         chat = new JButton("chatting");
         
         GridBagLayout gbl = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.fill = GridBagConstraints.BOTH;
         setLayout(gbl);
         
         addGrid(gbl, gbc, p, 	 0, 0, 3, 1, 1, 0);
         addGrid(gbl, gbc, btn1, 0, 1, 1, 2, 1, 2);
         addGrid(gbl, gbc, btn2, 1, 1, 1, 2, 1, 2);
         addGrid(gbl, gbc, btn3, 0, 3, 1, 2, 1, 2);
         addGrid(gbl, gbc, btn4, 1, 3, 1, 2, 1, 2);
         addGrid(gbl, gbc, chat, 2, 1, 1, 4, 1, 4);
         
         pack();
				
		setVisible(true);
		setBounds(100,50,1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ok.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == ok){
			try{
				mp = new MusicPlay(getIndex());		// 버튼 클릭 시 MusicPlay 실행
			}catch(IOException ee){
				ee.printStackTrace();
			}
		}
	}
}
