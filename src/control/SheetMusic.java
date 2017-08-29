package control;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class SheetMusic extends JFrame {
	int index;
	public static File f;
	
	public SheetMusic() {
	}

	public File showSheet(int index) throws IOException {
		this.index = index;
		File f = new File("");

		if (index == 0) {
			f = new File("img/곰세마리.png");
		} else if (index == 1) {
			f = new File("img/나비야.jpg");
		} else if (index == 2) {
			f = new File("img/학교종.png");
		} else if (index == 3) {
			f = new File("img/버즈-겁쟁이.jpg");
		} else if (index == 4) {
			f = new File("img/메모.jpg");
		}

		return f;
	}

}