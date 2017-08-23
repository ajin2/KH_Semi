package control;

import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class Help extends JFrame implements ActionListener {
	
	private JMenuBar mb;
	private JMenu mhelp, mselect;
	private JTextArea ta;
	private JMenuItem mhelpview;
	Dimension dm;

	//private JPanel p1;
	HelpSub sf;

	public Help() {
		super("Main");
		mb = new JMenuBar();
		setJMenuBar( mb );

		mhelp = new JMenu( "도움말" );
		mb.add( mhelp );
		
		mhelpview = new JMenuItem( "도움말 보기");
		mhelp.add( mhelpview );
		
		mhelp.addActionListener(this);
		mhelpview.addActionListener(this);
		
		sf = new HelpSub();

		sf.close.addActionListener(this);
		
		
		
		
	addWindowListener(
		new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}
		);
	}// 생성자
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if(obj == mhelpview) {
			sf.setSize(500,500);
			sf.setLocation(dm.width,0);
			sf.setVisible(true);
			} 
			else if(obj == sf.close) { 
			sf.dispose();
		} 
		
	}
	
	public static void main(String[] args) {
		Help h = new Help();
		h.setSize(500,500);
		h.setVisible(true);

		h.dm = h.getSize();
	}
}	////////////////////////////

///////// 메인 프레임에서 띄어줄 서브 프레임 클래스 //////////////