package control;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SheetDrawing implements ActionListener {
	SndThreadControl sndThreadControl = null;
	BufferedImage bImage, AcImage;

	ImageIcon image;
	JLabel imageLabel;
	JFrame frame;
	JPanel tool;
	JButton ba, r, g, b, c, ac, bold, thin, plain;
	int xClicked = 0;
	int yClicked = 0;
	int xDragged = 0;
	int yDragged = 0;
	int xc, yc, xd, yd;
  
	Color ch = Color.BLACK;
	int l, x, y, ox, oy, width = 2;
	Graphics g1;
	Graphics2D g2;

	public boolean paint = false;
	private JFrame letsGetItUser;

	public boolean getPaint() {
		return paint;
	}

	public int getXC() {
		return xc;
	}

	public int getYC() {
		return yc;
	}

	public int getXD() {
		return xd;
	}

	public int getYD() {
		return yd;
	}

	public void setC(Color ch) {
		this.ch = ch;
	}

	public Color getC() {
		return ch;
	} // 색받아옴

	public void setW(int width) {
		this.width = width;
	}

	public int getW() {
		return width;
	} // 선두께 받아옴

	public Graphics2D getG() {
		return g2;
	}

	public void Allc(File f) {
		g2 = bImage.createGraphics();
		g2.fillRect(0, 0, 600, 600);
		g2.dispose();
		try {
			bImage = ImageIO.read(f);
			image = new ImageIcon(bImage);
		} catch (Exception e) {
			e.printStackTrace();
	}
		image = new ImageIcon(bImage);
		imageLabel.setIcon(new ImageIcon(bImage));
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public MouseAdapter mouseListener = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent me) {
			xClicked = me.getX();
			yClicked = me.getY();
			xDragged = xClicked;
			yDragged = yClicked;

			xc = me.getX();
			yc = me.getY();
			paint = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			xClicked = -1;
			xClicked = -1;
			xDragged = -1;
			yDragged = -1;

			paint = false;
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (paint) {

				xClicked = xDragged;
				yClicked = yDragged;

				xd = xDragged;
				yd = yDragged;

				xDragged = me.getX();
				yDragged = me.getY();

				xDragged = me.getX();
				yDragged = me.getY();

				DrawUtils.drawLineNRefresh(bImage, getC(), getW(), xClicked, yClicked, xDragged, yDragged,
						me.getComponent());
				imageLabel.setIcon(new ImageIcon(bImage));

				try {
					// letsGetItClient.getOos().writeObject("draw" + "#" + "3" +
					// "#" + "2" + "#" + "1" + "#" + "0"); // test
					StringBuilder builder = new StringBuilder();
					builder.append("draw");
					builder.append("#").append(xClicked);
					builder.append("#").append(yClicked);
					builder.append("#").append(xDragged);
					builder.append("#").append(yDragged);
					builder.append("#").append(getC().getRGB());
					builder.append("#").append(width);

					if (letsGetItUser instanceof LetsGetItClient) {
						((LetsGetItClient) letsGetItUser).getOos().writeObject(builder.toString());
					} else if (letsGetItUser instanceof LetsGetItServer) {
						if (((LetsGetItServer) letsGetItUser).getSndThreadControl() != null)
							((LetsGetItServer) letsGetItUser).getSndThreadControl().broadCasting(builder.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public BufferedImage getbImage() {
		return bImage;
	}

	public SheetDrawing(File f) {
		try {
			bImage = ImageIO.read(f);
			image = new ImageIcon(bImage);

			g2 = bImage.createGraphics();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SheetDrawing(File f, JFrame letsGetItUser) {
		this(f);
		try {
			this.letsGetItUser = letsGetItUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayGUI() {
		JFrame frame = new JFrame("Painting on Sheet");
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		tool = new JPanel();
		ba = new JButton(" ");
		r = new JButton(" ");
		g = new JButton(" ");
		b = new JButton(" ");
		c = new JButton(" ");
		ac = new JButton("All Clr");
		bold = new JButton("Bold");
		thin = new JButton("Thin");
		plain = new JButton("Plain");

		ba.setBackground(Color.BLACK);
		r.setBackground(Color.RED);
		g.setBackground(Color.GREEN);
		b.setBackground(Color.BLUE);
		c.setBackground(Color.WHITE);

		tool.add(ba);
		tool.add(r);
		tool.add(g);
		tool.add(b);
		tool.add(c);
		tool.add(ac);
		tool.add(bold);
		tool.add(thin);
		tool.add(plain);

		contentPane.add("North", tool);

		imageLabel = new JLabel(image);
		imageLabel.addMouseListener(mouseListener);
		imageLabel.addMouseMotionListener(mouseListener);

		contentPane.add("Center", imageLabel);

		frame.add(contentPane);
		frame.pack();

    frame.setBounds(600, 200, 600, 500);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

		ba.addActionListener(this);
		r.addActionListener(this);
		g.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		ac.addActionListener(this);
		bold.addActionListener(this);
		thin.addActionListener(this);
		plain.addActionListener(this);
	}
  
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == ba) {
			this.setC(Color.BLACK);
		} else if (obj == r) {
			this.setC(Color.RED);
		} else if (obj == g) {
			this.setC(Color.GREEN);
		} else if (obj == b) {
			this.setC(Color.BLUE);
		} else if (obj == c) {
			this.setC(Color.WHITE);
		} else if (obj == ac) {
      File f = SheetMusic.f;
			this.Allc(f);
		} else if (obj == bold) {
			width = 4;
			this.setW(width);
		} else if (obj == thin) {
			width = 1;
			this.setW(width);
		} else if (obj == plain) {
			width = 2;
			this.setW(width);
			this.setC(Color.BLACK);
		}
	}
}