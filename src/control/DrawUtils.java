package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawUtils {
	public static void drawLineNRefresh(BufferedImage image, Color color, int width, int xC, int yC, int xD, int yD, Component component) {
		Graphics2D g2 = image.createGraphics();
		g2.setColor(color);
		g2.setStroke(new BasicStroke(width));
		g2.drawLine(xC, yC, xD, yD); 

		g2.dispose();

		component.invalidate();
		component.repaint();
	}
}
