package control;

import java.awt.*; 
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SheetDrawing extends JFrame implements ActionListener { 
	Button ba,r,gr,b,c,ac,bold,thin,ori;
	Panel p;
	DrawCanvas dc;
	int width=2;
	
    public SheetDrawing() {
    	
    	p = new Panel();
    	p.setBackground(Color.WHITE);
    	
    	ba = new Button("    ");
    	r = new Button("    ");
    	gr = new Button("    ");
    	b = new Button("    ");
    	c = new Button("Clear");
    	ac = new Button("All Clear");
    	bold = new Button("Bold");
    	thin = new Button("Thin");
    	ori = new Button("Plain");
    	
    	ba.setBackground(Color.BLACK);
    	r.setBackground(Color.RED);
    	gr.setBackground(Color.GREEN);
    	b.setBackground(Color.BLUE);
    	
    	p.add(ba);
    	p.add(r);
    	p.add(gr);
    	p.add(b);
    	p.add(c);
    	p.add(ac);
    	p.add(bold);
    	p.add(thin);
    	p.add(ori);
    	
    	dc = new DrawCanvas();
    	dc.setC(Color.BLACK);
    	dc.setW(width);
    	
    	add("North", p);
        add("Center",dc);
        
        ba.addActionListener(this);
        r.addActionListener(this);
        gr.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);
        ac.addActionListener(this);
        bold.addActionListener(this);
        thin.addActionListener(this);
        ori.addActionListener(this);
    }//----------------------------------------------------생성자
    
    public void Draw(){
    	Frame f = new SheetDrawing(); 
        f.setBounds(100,100,600,600);
        f.setBackground(Color.WHITE);
        f.show();
    }//----------------------------------------------------캔버스 보이기
    
    public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == ba){
			dc.setC(Color.BLACK);
		}else if(obj == r){
			dc.setC(Color.RED);
		}else if(obj == gr){
			dc.setC(Color.GREEN);
		}else if(obj == b){
			dc.setC(Color.BLUE);
		}else if(obj == c){
			dc.setC(Color.WHITE);
		}else if(obj == ac){
			dc.Allc();
		}else if(obj == bold){
			width = 4;
			dc.setW(width);
		}else if(obj == thin){
			width = 1;
			dc.setW(width);
		}else if(obj == ori){
			width = 2;
			dc.setW(width);
		}
	}//------------------------------------------------------ 버튼이벤트
    
	public static void main(String[] args) { 
		DrawCanvas dc = new DrawCanvas();
		SheetDrawing f = new SheetDrawing();
         f.Draw();
        
    } //end of main 
} //end of CanvasFrame class 