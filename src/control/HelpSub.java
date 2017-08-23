package control;

import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

///////// 메인 프레임에서 띄어줄 서브 프레임 클래스 //////////////
class HelpSub extends JFrame implements ActionListener {

   JButton close, ok;
   private JButton a, b, c, d, e;
   private JTextArea ta;
   private JLabel la, lb;
   private JTextField tf;
   private JPanel Hyeok;

   public HelpSub() {
      super("도움말");

      close = new JButton("CLOSE");
      add(close, "South");

      Hyeok = new JPanel();
      Hyeok.setBackground(new Color(25, 100, 55));
      a = new JButton("통합");
      a.setPreferredSize(new Dimension(80, 50));
      a.setBackground(new Color(200, 250, 180));
      b = new JButton("피아노");
      b.setPreferredSize(new Dimension(80, 50));
      b.setBackground(new Color(200, 250, 180));
      c = new JButton("드럼");
      c.setPreferredSize(new Dimension(80, 50));
      c.setBackground(new Color(200, 250, 180));
      d = new JButton("베이스기타");
      d.setPreferredSize(new Dimension(100, 50));
      d.setBackground(new Color(200, 250, 180));
      e = new JButton("일렉기타");
      e.setPreferredSize(new Dimension(100, 50));
      e.setBackground(new Color(200, 250, 180));
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
         la.setText("<html>" + "<p>통.</p>" + "<p>합.</p>" + "<p>감사합니다.</p>" + "</html>");
      } else if (Obj == b) {
         la.setText("<html>" + "<p>피.</p>" + "<p>아.</p>" + "<p>노.</p>" + "<p>감사합니다.</p>" + "</html>");
      } else if (Obj == c) {
         la.setText("<html>" + "<p>드.</p>" + "<p>럼.</p>" + "<p>감사합니다.</p>" + "</html>");
      } else if (Obj == d) {
         la.setText("<html>" + "<p>베이.</p>" + "<p>스.</p>" + "<p>기타.</p>" + "<p>감사합니다.</p>" + "</html>");
      } else {
         la.setText("<html>" + "<p>일렉.</p>" + "<p>기.</p>" + "<p>타.</p>" + "<p>감사합니다.</p>" + "</html>");
      }

   }
}