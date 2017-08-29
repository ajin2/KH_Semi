package control;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SheetSave {
	private JFrame jframe;
	private BufferedImage bufferedImage;

	public SheetSave(JFrame jframe, BufferedImage bufferedImage) {
		this.jframe = jframe;
		this.bufferedImage = bufferedImage;
	}

	public void save() {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(jframe);
		String path = fc.getSelectedFile().getPath();
		System.out.println(path);

		try {
			File file = new File(path); // 파일의 이름을 설정한다
			ImageIO.write(bufferedImage, "jpg", file); // write메소드를 이용해 파일을 만든다
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
