package pa.iscde.outlaw.Outline;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import pa.iscde.outlaw.PidescoView1;

public class IconMerger {

	public Image merge(String[] icons, int offset_x, int offset_y, boolean flag) {
		String path=PidescoView1.path;
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
			} catch (IOException | NullPointerException e) {
				image = new Image(Display.getCurrent(), path + icons[0]);
				return image;
			}
		} else {
			image = new Image(Display.getCurrent(), path + icons[0]);
			return image;
		}
	}
}
