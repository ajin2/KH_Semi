package control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

///////// 메인 프레임에서 띄어줄 서브 프레임 클래스 //////////////
class HelpSub extends JFrame implements ActionListener {

	JButton close, ok;
	private JButton a, b, c, d, e;
	private JLabel la;
	private JPanel Hyeok;

	public HelpSub() {
		super("도움말");

		close = new JButton("CLOSE");
		close.setBackground(new Color(25, 25, 25));
		close.setFont(new Font("바탕", Font.BOLD, 15));
		close.setForeground(Color.WHITE);
		add(close, "South");

		Hyeok = new JPanel();
		Hyeok.setBackground(new Color(62, 60, 63));
		
		a = new JButton("통합");
		a.setFont(new Font("바탕", Font.BOLD, 15));
		a.setPreferredSize(new Dimension(80, 50));
		a.setBackground(new Color(25, 25, 25));
		a.setForeground(Color.WHITE);
		
		b = new JButton("피아노");
		b.setFont(new Font("바탕", Font.BOLD, 15));
		b.setPreferredSize(new Dimension(80, 50));
		b.setBackground(new Color(25, 25, 25));
		b.setForeground(Color.WHITE);
		
		c = new JButton("드럼");
		c.setFont(new Font("바탕", Font.BOLD, 15));
		c.setPreferredSize(new Dimension(80, 50));
		c.setBackground(new Color(25, 25, 25));
		c.setForeground(Color.WHITE);
		
		d = new JButton("베이스기타");
		d.setFont(new Font("바탕", Font.BOLD, 15));
		d.setPreferredSize(new Dimension(110, 50));
		d.setBackground(new Color(25, 25, 25));
		d.setForeground(Color.WHITE);
		
		e = new JButton("기타");
		e.setFont(new Font("바탕", Font.BOLD, 15));
		e.setPreferredSize(new Dimension(80, 50));
		e.setBackground(new Color(25, 25, 25));
		e.setForeground(Color.WHITE);
		
		Hyeok.add(a);
		Hyeok.add(b);
		Hyeok.add(c);
		Hyeok.add(d);
		Hyeok.add(e);
		Hyeok.setLayout(new FlowLayout());
		
		add(Hyeok);

		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e.addActionListener(this);

		la = new JLabel();
		la.setForeground(Color.WHITE);
		Hyeok.add(la);

	} // 생성자

	public void actionPerformed(ActionEvent e) {

		Object Obj = e.getSource();

		if (Obj == a) {
			la.setText("<html>" + "<p>                               </p>" + "<p>                               </p>"
					+ "<p>                               </p>" + "<p>                               </p>"
					+ "<p>4가지 악기 피아노, 기타, 베이스기타, 드럼을 사용자들이</p>" + "<p>채팅을 통해 연주할 악기를 선정하면</p>"
					+ "<p>선택한 악기와 악보가 보여집니다.</p>" + "</html>");
			la.setFont(new Font("바탕", Font.BOLD, 16));
		} else if (Obj == b) {
			la.setText("<html>" + "<p>                               </p>" + "<p>                               </p>"
					+ "<p>                               </p>" + "<p>                               </p>"
					+ "<p>피아노를 선택한 사용자는 악보와 피아노 이미지가 주어지고</p>" + "<p>이미지 밑에 버튼이 있습니다.</p>"
					+ "<p>키보드와 마우스를 통해 버튼을 눌러</p>" + "<p>소리를 내어 연주를 할 수 있습니다.</p>"
					+ "<p>버튼 A   S   D   F   G   H   J   K</p>" + "<p>음계 도  레  미  파  솔  라  시  도</p>" + "</html>");
			la.setFont(new Font("바탕", Font.BOLD, 16));
		} else if (Obj == c) {
			la.setText("<html>" + "<p>                               </p>" + "<p>                               </p>"
					+ "<p>                               </p>" + "<p>                               </p>"
					+ "<p>드럼을 선택한 사용자는 악보와 드럼 이미지가 주어지고</p>" + "<p>이미지 밑에 버튼이 있고 키보드와 마우스를 통해 버튼을 눌러</p>"
					+ "<p>소리를 내어 연주를 할 수 있습니다.</p>" + "<p>버튼 A   S   D   F   G   H   J   K</p>"
					+ "<p>음계 도  레  미  파  솔  라  시  도</p>" + "</html>");
		} else if (Obj == d) {
			la.setText("<html>" + "<p>                               </p>" + "<p>                               </p>"
					+ "<p>                               </p>" + "<p>                               </p>"
					+ "<p>베이스기타를 선택한 사용자는 악보와 베이스기타 이미지가</p>" + "<p>주어지고 이미지 밑에 버튼이 있고 키보드와 마우스를 통해</p>"
					+ "<p>버튼을 눌러 소리를 내어 연주를 할 수 있습니다.</p>" + "<p>버튼 A   S   D   F   G   H   J   K</p>"
					+ "<p>음계 도  레  미  파  솔  라  시  도</p>" + "</html>");
		} else {
			la.setText("<html>" + "<p>                               </p>" + "<p>                               </p>"
					+ "<p>                               </p>" + "<p>                               </p>"
					+ "<p>기타를 선택한 사용자는 악보와 기타 이미지가 주어지고</p>" + "<p>이미지 밑에 버튼이 있고 키보드와 마우스를 통해 버튼을 눌러</p>"
					+ "<p>소리를 내어 연주를 할 수 있습니다.</p>" + "<p>버튼 A   S   D   F   G   H   J   K</p>"
					+ "<p>음계 도  레  미  파  솔  라  시  도</p>" + "</html>");
		}

	}
}