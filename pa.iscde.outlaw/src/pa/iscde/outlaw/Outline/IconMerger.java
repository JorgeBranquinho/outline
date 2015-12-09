package pa.iscde.outlaw.Outline;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class IconMerger {

	static String path = "C:\\Users\\Mr.Ivo\\git\\outline\\pa.iscde.outlaw\\images\\";


	public Image merge(String[] icons, int offset_x, int offset_y, boolean flag) {
		Image image;
		if (icons.length > 1 && !flag) {
			try {
				BufferedImage imagebckg = ImageIO.read(new File(path + icons[0]));
				BufferedImage newoverlay = ImageIO.read(new File(path + icons[1]));
				int ws = Math.max(imagebckg.getWidth(), newoverlay.getWidth());
				int hs = Math.max(imagebckg.getHeight(), newoverlay.getHeight());
				BufferedImage combined = new BufferedImage(ws, hs, BufferedImage.TYPE_INT_ARGB);
				Graphics gt = combined.getGraphics();
				gt.drawImage(imagebckg, 0, 0, null);
				gt.drawImage(newoverlay, offset_x, offset_y, null);
				ImageIO.write(combined, "PNG", new File(path + "Outter.png"));
				image = new Image(Display.getCurrent(), path + "Outter.png");
				return image;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			image = new Image(Display.getCurrent(), path + icons[0]);
			return image;
		}
		return null;
	}
}
