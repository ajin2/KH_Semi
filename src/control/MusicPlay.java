package control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MusicPlay extends JFrame{
	private JPanel p,p�Ǻ�,pä��,p�Ǳ�,p��ü;
	private JButton ����, ä��,�Ǳ�1,�Ǳ�2,�Ǳ�3,�Ǳ�4;
	private JLabel �Ǻ�,blank;
	private JComboBox sheet;
	private String sheet_name[] = {"��������","�����","�б����� ������","����-������","�޸�dsds"};
	
	public MusicPlay(){
		
		// �Ǻ�
		p = new JPanel();
		p.setLayout(new GridLayout(1,4,5,5));
		p�Ǻ� = new JPanel();

		blank = new JLabel();
		�Ǻ� = new JLabel("�Ǻ� : ");
		sheet = new JComboBox(sheet_name);
		���� = new JButton("����");
		
		p�Ǻ�.add(�Ǻ�);
		p�Ǻ�.add(sheet);
		p�Ǻ�.add(����);

		p.add(p�Ǻ�);
		p.add(blank);
		p.add(blank);
		p.add(blank);
		add(p,"North");
		
		// �Ǳ�
		p�Ǳ� = new JPanel();
		p�Ǳ�.setLayout(new GridLayout(2,2,10,10));
		
		�Ǳ�1 = new JButton();
		�Ǳ�2 = new JButton();
		�Ǳ�3 = new JButton();
		�Ǳ�4 = new JButton();
		
		p�Ǳ�.add(�Ǳ�1);
		p�Ǳ�.add(�Ǳ�2);
		p�Ǳ�.add(�Ǳ�3);
		p�Ǳ�.add(�Ǳ�4);
		add(p�Ǳ�);

		// ä��
		pä�� = new JPanel();
		pä��.setLayout(new GridLayout(1,1));

		ä�� = new JButton("ä��");

		pä��.add(ä��);

		// �Ǳ� + ä��
		p��ü = new JPanel();
		p��ü.setLayout(new GridLayout(1,2,10,10));
		
		p��ü.add(p�Ǳ�);
		p��ü.add(pä��);
		add(p��ü);
		
		
		setVisible(true);
		setBounds(100,50,1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MusicPlay();
	}
}
