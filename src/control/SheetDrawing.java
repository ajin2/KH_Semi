package control;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SheetDrawing implements ActionListener {

	private static final String FILE_PATH = "img/곰세마리.jpg";

	BufferedImage bImage;
	ImageIcon image;
	JLabel imageLabel;
	JFrame frame;
	JPanel tool;
	JButton ba, r, g, b, c, ac, bold, thin, plain;
	int xClicked = 0;
	int yClicked = 0;
	int xDragged = 0;
	int yDragged = 0;
	Color ch = Color.BLACK;
	int l, x, y, ox, oy, width = 2;
	Graphics g1;
	Graphics2D g2;

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

	public void Allc() {
		Graphics2D g2 = bImage.createGraphics();
		g2.fillRect(0, 0, 600, 600);
		g2.dispose();
		try {
			bImage = ImageIO.read(new File(FILE_PATH));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		image = new ImageIcon(bImage);
		imageLabel.setIcon(new ImageIcon(bImage));
	}

	private MouseAdapter mouseListener = new MouseAdapter() {
		private boolean paint = false;

		@Override
		public void mousePressed(MouseEvent me) {
			xClicked = me.getX();
			yClicked = me.getY();
			xDragged = xClicked;
			yDragged = yClicked;

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

				xDragged = me.getX();
				yDragged = me.getY();

				xDragged = me.getX();
				yDragged = me.getY();

				Graphics2D g2 = bImage.createGraphics();
				g2.setColor(getC());
				width = getW();
				g2.setStroke(new BasicStroke(width));
				g2.drawLine(xClicked, yClicked, xDragged, yDragged);

				g2.dispose();
				imageLabel.setIcon(new ImageIcon(bImage));

				me.getComponent().invalidate();
				me.getComponent().repaint();
			}
		}
	};

	public SheetDrawing() {
		try {
			bImage = ImageIO.read(new File(FILE_PATH));
			image = new ImageIcon(bImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayGUI() {
		JFrame frame = new JFrame("Painting on Sheet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		frame.setLocationByPlatform(true);
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

	public static void main(String... args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SheetDrawing().displayGUI();
			}
		});
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
			this.Allc();
		} else if (obj == bold) {
			width = 4;
			this.setW(width);
		} else if (obj == thin) {
			width = 1;
			this.setW(width);
		} else if (obj == plain) {
			width = 2;
			this.setW(width);
		}
	}
}

