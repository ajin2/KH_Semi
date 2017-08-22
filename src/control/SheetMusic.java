package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class SheetMusic extends JFrame {
   int index;
   Image img = null;
   
   public SheetMusic(){}
   
   public void init(){
      JLabel sheet = new JLabel(new ImageIcon(img));
      add(sheet);
   }
   
   public void showSheet(int index) throws IOException{
      this.index = index;
      File f = new File("");
      
      if(index==0){               // ComboBox index�� 1�϶� ( �������� )
         f = new File("img/��������.jpg");
         img = ImageIO.read(f);
      }else if(index==1){            // ComboBox index�� 2�϶� ( ����� )
         f = new File("img/�����.jpg");
         img = ImageIO.read(f);
      }else if(index==2){            // ComboBox index�� 3�϶� ( �б����� ������ )
         f = new File("img/�б����� ������.jpg");
         img = ImageIO.read(f);   
      }else if(index==3){            // ComboBox index�� 4�϶� ( ����-������ )
         f = new File("img/����-������.jpg");
         img = ImageIO.read(f);
      }else if(index==4){            // ComboBox index�� 5�϶� ( �޸� )
         f = new File("img/�޸�.jpg");
         img = ImageIO.read(f);   
      }
      
      init();
      
      setVisible(true);
      setBounds(100,50,980,900);
      setAlwaysOnTop(true);
   }
   
/*   public void showSheet2(int index) throws IOException{
	      this.index = index;
	      File f = new File("");
	      
	      String filePath = "";
	      
	      if(index==0){               // ComboBox index�� 1�϶� ( �������� )
	    	 filePath = "img/��������.jpg";
	      }else if(index==1){            // ComboBox index�� 2�϶� ( ����� )
	    	  filePath = "img/�����.jpg";
	      }else if(index==2){            // ComboBox index�� 3�϶� ( �б����� ������ )
	         f = new File("img/�б����� ������.jpg");
	         img = ImageIO.read(f);   
	      }else if(index==3){            // ComboBox index�� 4�϶� ( ����-������ )
	         f = new File("img/����-������.jpg");
	         img = ImageIO.read(f);
	      }else if(index==4){            // ComboBox index�� 5�϶� ( �޸� )
	         f = new File("img/�޸�.jpg");
	         img = ImageIO.read(f);   
	      }
	      
	      f = new File(filePath);
	         img = ImageIO.read(f);
	      
	      init();
	      
	      setVisible(true);
	      setSize(700,500);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }*/
}