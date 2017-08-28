package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;

public class MusicPlay extends JFrame implements ActionListener{
	SheetMusic sm;
	File f;
	Image img = null, chimg;
	JMenuBar mb;
	JMenu mdrawing;
	JMenuItem msheet, mmemo, msave;
	JPanel psheet, pmusic;
	JButton btn;
	int index;

	public void init() {
		psheet = new JPanel();
		psheet.setLayout(new GridLayout(1, 1));
		chimg = img.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel sheet = new JLabel(new ImageIcon(chimg));
		psheet.add(sheet);
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

	public MusicPlay(int index) throws IOException {

		this.index = LetsGetItServer.getIndex();
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

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		addGrid(gbl, gbc, psheet, 0, 0, 1, 1, 1, 1);

		pack();

		setVisible(true);
		setBounds(118, 50, 1237, 893);
		setAlwaysOnTop(true);
		setResizable(false);
		
		msheet.addActionListener(this);
		mmemo.addActionListener(this);
		msave.addActionListener(this);
	}	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == msheet) {
			try {
				f = sm.showSheet(index);
				new SheetDrawing(f).displayGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (obj == mmemo){
			
		}else if (obj == msave){
			
		}
	}
}
