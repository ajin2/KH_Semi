package control;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.sound.midi.*;

public class MusicPlayClient extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu mdrawing;
	JMenuItem msheet, mmemo, msave;
	Instrument instrument;
	
	int index;
	Image img = null, instrumentImg = null, chimg, instruimg;
	JPanel psheet, instruPanel;
	SheetMusic sm;
	
	int instrumentNum;
	long keyPressedMillis;
	JPanel buttonPanel;
	JButton DolemiBtn[];
	
	public void init() {
		psheet = new JPanel();
		psheet.setLayout(new GridLayout(1, 1));
		chimg = img.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel sheet = new JLabel(new ImageIcon(chimg));
		psheet.add(sheet);
	}
	
	public void instrumentInit() {
		instruPanel = new JPanel();
		instruimg = instrumentImg.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel instru = new JLabel(new ImageIcon(instruimg));
		instruPanel.add(instru);
	}
		
		public void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c, int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	public MusicPlayClient(int index, int instrumentNum) throws IOException {
		// MenuBar
		mb = new JMenuBar();
		setJMenuBar(mb);

		mdrawing = new JMenu("Drawing");
		mb.add(mdrawing);

		msheet = new JMenuItem("On Sheet");
		mmemo = new JMenuItem("New Memo");
		msave = new JMenuItem("Save");

		mdrawing.add(msheet);
		mdrawing.add(mmemo);
		mdrawing.add(msave);

		// Sheet Print
		sm = new SheetMusic();
		img = ImageIO.read(sm.showSheet(index));
		init();
		// Instrument Print 
		instrument = new Instrument();
		instrumentImg = ImageIO.read(instrument.instrumentShow(index));
		instrumentInit();

		this.instrumentNum = instrumentNum;
		
		// ----------------------------------------------- �Ǻ����

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		addGrid(gbl, gbc, psheet, 0, 0, 1, 1, 1, 1);
		addGrid(gbl, gbc, instruPanel, 0, 1, 1, 1, 1, 4);

		pack();

		setVisible(true);
		setBounds(118, 50, 1237, 893);
		setAlwaysOnTop(true);
		setResizable(false);

	}	// 생성자
	
	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();
		
		
		
		// TODO 여기까지 하다가 말았음. 데이터 넘겨받는거 해야함.
		// pressed instruments
		for(int i = 0; i < 8; i++) {
			if(instrumentNum == 0) {
				
			} else if(instrumentNum == 1) {
				
			} else if(instrumentNum == 2) {
				
			} else if(instrumentNum == 3) {
				
			}
		}
	}
	
	
	
}
