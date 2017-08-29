package control;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.*;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;

class Ex1 extends JFrame implements ActionListener {
	JPanel Hyeok;
	JLabel la;
	JTextField tf;
	String id;
	JButton loginButton;
	String str;
	LetsGetItClient lc;
	
	public Ex1() throws UnknownHostException, IOException {
		Hyeok = new JPanel();
		Hyeok.setBackground(new Color(62, 60, 63));
		
		la = new JLabel(" 채팅닉네임 : ");
		la.setFont(new Font("바탕", Font.BOLD, 15));
		la.setForeground(Color.WHITE);
		
		tf = new JTextField(15);
		loginButton = new JButton("Login");
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(25, 25, 25));
		
		Hyeok.add(la);
		Hyeok.add(tf);
		Hyeok.add(loginButton);
		setBounds(800, 300, 260, 250);
		setVisible(true);
		add(Hyeok);
		
		loginButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == loginButton) {
			str = tf.getText();
			setStr(str);
			
			LetsGetItClient letsGetItC;
			try {
				letsGetItC = new LetsGetItClient("172.30.1.7", 5000, getStr());
				letsGetItC.init();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Ex1();
	}
}