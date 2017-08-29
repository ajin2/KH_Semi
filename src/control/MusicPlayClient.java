package control;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class MusicPlayClient extends JFrame implements ActionListener {
	JMenuBar mb;
	JMenu mdrawing;
	JMenuItem msheet, msave;
	SheetSave sheetsave;
	Instrument instrument;
	InstrumentPiano piano;
	
	// 악보
	int index;
	Image img = null, chimg;
	JPanel psheet;
	SheetMusic sm;
	
	// 악기
	int instrumentNum;
	long keyPressedMillis;
	Image instrumentImg = null, instruimg;
	JPanel instruPanel, buttonPanel;
	JButton DolemiBtn[];
	
	// Network Instrument 
	LetsGetItClient letsGetItClient;
	SndThreadControl sndThreadControl;
	private File f;
	private SheetDrawing sheetDrawing;
		
	public void init() {
		psheet = new JPanel();
		psheet.setBackground(new Color(90, 84, 92));

		psheet.setLayout(new GridLayout(1, 1));
		chimg = img.getScaledInstance(650, 420, java.awt.Image.SCALE_SMOOTH);
		JLabel sheet = new JLabel(new ImageIcon(chimg));
		psheet.add(sheet);
	}
	
	public void instrumentInit() {
		instruPanel = new JPanel();
		instruPanel.setBackground(new Color(90, 84, 92));
		instruimg = instrumentImg.getScaledInstance(800, 420, java.awt.Image.SCALE_SMOOTH);
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
	
	public MusicPlayClient(int index, int instrumentNum, LetsGetItClient letsGetItClient) throws IOException {		
		this.letsGetItClient = letsGetItClient;
		this.index = LetsGetItClient.getIndex();
		
		// MenuBar
		mb = new JMenuBar();
		setJMenuBar(mb);

		mdrawing = new JMenu("Drawing");
		mb.add(mdrawing);
		mb.setBackground(new Color(90, 84, 92));
		mdrawing.setForeground(Color.WHITE);

		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(90, 84, 92));
		
		msheet = new JMenuItem("On Sheet");
		msave = new JMenuItem("Save");

		mdrawing.add(msheet);
		mdrawing.add(msave);

		// Sheet Print
		sm = new SheetMusic();
		img = ImageIO.read(sm.showSheet(index));
		init();
		// Instrument Print 
		instrument = new Instrument();
		instrumentImg = ImageIO.read(instrument.instrumentShow(instrumentNum));
		instrumentInit();

		// Instruments Button
		DolemiBtn = new JButton[8];
		DolemiBtn[0] = new JButton("도");
		DolemiBtn[1] = new JButton("레");
		DolemiBtn[2] = new JButton("미");
		DolemiBtn[3] = new JButton("파");
		DolemiBtn[4] = new JButton("솔");
		DolemiBtn[5] = new JButton("라");
		DolemiBtn[6] = new JButton("시");
		DolemiBtn[7] = new JButton("도");
		for(int i = 0; i < 8; i++) {
			buttonPanel.add(DolemiBtn[i]);
		}
		this.instrumentNum = instrumentNum;	// 악기 종류 저장
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		addGrid(gbl, gbc, psheet, 0, 0, 1, 1, 1, 1);
		addGrid(gbl, gbc, instruPanel, 0, 1, 1, 1, 1, 4);
		addGrid(gbl, gbc, buttonPanel, 0, 2, 1, 1, 0, 0);	
		pack();

		setVisible(true);

		setBounds(438, 50, 1000, 893);
		setAlwaysOnTop(true);
		setResizable(false);
		
		msheet.addActionListener(this);
		msave.addActionListener(this);
		
		// Key Event
		buttonPanel.requestFocus();
		MusicKeyAdapter adapter = new MusicKeyAdapter();
		buttonPanel.addKeyListener(adapter);
		
		// Action Event
		for(int i = 0; i < 8; i++) {
			DolemiBtn[i].addActionListener(this);
			DolemiBtn[i].addKeyListener(adapter);
		}
	}	// 생성자
	
	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();
		
		// MenuItem
		if (obj == msheet) {
			try {
				f = sm.showSheet(index);
				SheetDrawing sheetDrawing = new SheetDrawing(f, letsGetItClient);
				setSheetDrawing(sheetDrawing);
				sheetDrawing.displayGUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (obj == msave) {
			if(sheetDrawing != null){
				SheetSave sheetsave = new SheetSave(this, sheetDrawing.getbImage());
				sheetsave.save();
			}
		}

		// play instrument
		for(int i = 0; i < 8; i++) {
			if(obj == DolemiBtn[i]) {
				if(instrumentNum == 0) {
					InstrumentPiano piano = new InstrumentPiano(i);	
					try {
						letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + i);
						piano.start();
						synchronized(piano) {
							piano.wait();
						}
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					} catch(IOException exception) {
						exception.printStackTrace();
					}
					
				} else if(instrumentNum == 1) {
					InstrumentElectric electric = new InstrumentElectric(i);
					try {
						electric.start();
						synchronized(electric) {
							electric.wait();
						}
						letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + i);
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if(instrumentNum == 2) {
					InstrumentBass bass = new InstrumentBass(i);
					try {
						bass.start();
						synchronized(bass) {
							bass.wait();
						}
						letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + i);
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if(instrumentNum == 3) {
					InstrumentDrum drum = new InstrumentDrum(i);
					try {
						drum.start();
						synchronized(drum) {
							drum.wait();
						}
						letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + i);
					} catch(InterruptedException interrupt) {
						interrupt.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void setSheetDrawing(SheetDrawing sheetDrawing) {
		this.sheetDrawing = sheetDrawing;
	}
	
	public SheetDrawing getSheetDrawing() {
		return sheetDrawing;
	}
	
	private final class MusicKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(0);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(0);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(0);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(0);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} 
					
			if (e.getKeyCode() == KeyEvent.VK_S) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(1);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(1);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(1);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(1);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(2);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(2);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(2);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(2);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_F) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(3);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(3);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(3);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(3);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_G) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(4);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(4);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(4);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(4);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_H) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(5);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(5);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(5);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(5);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_J) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(6);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(6);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(6);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(6);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_K) {
				try {
					letsGetItClient.getOos().writeObject("music" + "#" + instrumentNum + "#" + 0);

					if(instrumentNum == 0) {	
						InstrumentPiano piano = new InstrumentPiano(7);
						piano.start();
						synchronized (piano) {
							piano.wait();
						}
					} else if(instrumentNum == 1) {
						InstrumentElectric electric = new InstrumentElectric(7);
						electric.start();
						synchronized (electric) {
							electric.wait();
						}
					} else if(instrumentNum == 2) {
						InstrumentBass bass = new InstrumentBass(7);
						bass.start();
						synchronized (bass) {
							bass.wait();
						}
					} else if (instrumentNum == 3) {
						InstrumentDrum drum = new InstrumentDrum(7);
						drum.start();
						synchronized (drum) {
							drum.wait();
						}
					}
				} catch (InterruptedException interrupt) {
					interrupt.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}
}
