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
      
      if(index==0){               // ComboBox index가 1일때 ( 곰세마리 )
         f = new File("img/곰세마리.jpg");
         img = ImageIO.read(f);
      }else if(index==1){            // ComboBox index가 2일때 ( 나비야 )
         f = new File("img/나비야.jpg");
         img = ImageIO.read(f);
      }else if(index==2){            // ComboBox index가 3일때 ( 학교종이 땡땡땡 )
         f = new File("img/학교종이 땡땡땡.jpg");
         img = ImageIO.read(f);   
      }else if(index==3){            // ComboBox index가 4일때 ( 버즈-겁쟁이 )
         f = new File("img/버즈-겁쟁이.jpg");
         img = ImageIO.read(f);
      }else if(index==4){            // ComboBox index가 5일때 ( 메모 )
         f = new File("img/메모.jpg");
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
	      
	      if(index==0){               // ComboBox index가 1일때 ( 곰세마리 )
	    	 filePath = "img/곰세마리.jpg";
	      }else if(index==1){            // ComboBox index가 2일때 ( 나비야 )
	    	  filePath = "img/나비야.jpg";
	      }else if(index==2){            // ComboBox index가 3일때 ( 학교종이 땡땡땡 )
	         f = new File("img/학교종이 땡땡땡.jpg");
	         img = ImageIO.read(f);   
	      }else if(index==3){            // ComboBox index가 4일때 ( 버즈-겁쟁이 )
	         f = new File("img/버즈-겁쟁이.jpg");
	         img = ImageIO.read(f);
	      }else if(index==4){            // ComboBox index가 5일때 ( 메모 )
	         f = new File("img/메모.jpg");
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