package control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MusicPlay extends JFrame{
	private JPanel p,pæ«∫∏,p√§∆√,pæ«±‚,p¿¸√º;
	private JButton º±≈√, √§∆√,æ«±‚1,æ«±‚2,æ«±‚3,æ«±‚4;
	private JLabel æ«∫∏,blank;
	private JComboBox sheet;
	private String sheet_name[] = {"∞ıºº∏∂∏Æ","≥™∫Òæﬂ","«–±≥¡æ¿Ã ∂Ø∂Ø∂Ø","πˆ¡Ó-∞Ã¿Ô¿Ã","∏ﬁ∏dsds"};
	
	public MusicPlay(){
		
		// æ«∫∏
		p = new JPanel();
		p.setLayout(new GridLayout(1,4,5,5));
		pæ«∫∏ = new JPanel();

		blank = new JLabel();
		æ«∫∏ = new JLabel("æ«∫∏ : ");
		sheet = new JComboBox(sheet_name);
		º±≈√ = new JButton("º±≈√");
		
		pæ«∫∏.add(æ«∫∏);
		pæ«∫∏.add(sheet);
		pæ«∫∏.add(º±≈√);

		p.add(pæ«∫∏);
		p.add(blank);
		p.add(blank);
		p.add(blank);
		add(p,"North");
		
		// æ«±‚
		pæ«±‚ = new JPanel();
		pæ«±‚.setLayout(new GridLayout(2,2,10,10));
		
		æ«±‚1 = new JButton();
		æ«±‚2 = new JButton();
		æ«±‚3 = new JButton();
		æ«±‚4 = new JButton();
		
		pæ«±‚.add(æ«±‚1);
		pæ«±‚.add(æ«±‚2);
		pæ«±‚.add(æ«±‚3);
		pæ«±‚.add(æ«±‚4);
		add(pæ«±‚);

		// √§∆√
		p√§∆√ = new JPanel();
		p√§∆√.setLayout(new GridLayout(1,1));

		√§∆√ = new JButton("√§∆√");

		p√§∆√.add(√§∆√);

		// æ«±‚ + √§∆√
		p¿¸√º = new JPanel();
		p¿¸√º.setLayout(new GridLayout(1,2,10,10));
		
		p¿¸√º.add(pæ«±‚);
		p¿¸√º.add(p√§∆√);
		add(p¿¸√º);
		
		
		setVisible(true);
		setBounds(100,50,1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MusicPlay();
	}
}
