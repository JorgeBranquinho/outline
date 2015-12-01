package pa.iscde.outlaw.Outline;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
public class IconMerger {

	static String path="C:\\Users\\Mr.Ivo\\git\\outline\\pa.iscde.outlaw\\images\\";
	
//	public java.awt.Image merge(String[] icons){
//		System.out.println("x"+icons.length);
//		if(icons.length>1){
//			try {
//				BufferedImage imagebckg = ImageIO.read(new File(path + icons[0]));
//				//for(int i=1;i<icons.length-1;i++){
//					BufferedImage newoverlay = ImageIO.read(new File(path + icons[1]));
//					
//					Graphics2D g = imagebckg.createGraphics();
//					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
//		            int x = (imagebckg.getWidth() - newoverlay.getWidth()) / 2;
//		            int y = (imagebckg.getHeight() - newoverlay.getHeight()) / 2;
//		            g.drawImage(newoverlay, x, y, null);
//		            g.dispose();
//		            ImageIO.write(imagebckg, "PNG", new File(path+"Outter.png"));
//		            
//					/*int w = Math.max(imagebckg.getWidth(), newoverlay.getWidth());
//					int h = Math.max(imagebckg.getHeight(), newoverlay.getHeight());
//					imagebckg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//					BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//					Graphics g = combined.getGraphics();
//					g.drawImage(imagebckg, 0, 0, null);
//					g.drawImage(newoverlay, 0, 0, null);
//					boolean x = ImageIO.write(combined, "PNG", new File("combined.png"));*/
//				//}
//				return imagebckg;//bufferedImagetoImage(imagebckg);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
	
	public Image merge(String[] icons){
		System.out.println("x"+icons.length);
		Image image;
		if(icons.length>1){
			try {
				BufferedImage imagebckg = ImageIO.read(new File(path + icons[0]));
				//for(int i=1;i<icons.length-1;i++){
				System.out.println("Path: "+path + icons[1]);
					BufferedImage newoverlay = ImageIO.read(new File(path + icons[1]));
					
					//Graphics2D g = imagebckg.createGraphics();
					//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
		            //int x = (imagebckg.getWidth() - newoverlay.getWidth());
		          //  int y = (imagebckg.getHeight() - newoverlay.getHeight())/2 ;
		            
		         // create the new image, canvas size is the max. of both image sizes
		            int w = Math.max(imagebckg.getWidth(), newoverlay.getWidth());
		            int h = Math.max(imagebckg.getHeight(), newoverlay.getHeight());
		            
		            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		            
		            Graphics gt= combined.getGraphics();
		            
		           // g.drawImage(newoverlay, x, y, null);
		            //g.dispose();
		           // ImageIO.write(imagebckg, "PNG", new File(path+"Outter.png"));
		            
		            gt.drawImage(newoverlay, 0, 0, null);
		            gt.drawImage(imagebckg, 0, 0, null);
		            
		            ImageIO.write(combined, "PNG", new File(path+"Outter.png"));
		            
		            
		            //SWT 
		            image = new Image(Display.getCurrent(), path+"Outter.png");
					
		            
		            /*int w = Math.max(imagebckg.getWidth(), newoverlay.getWidth());
					int h = Math.max(imagebckg.getHeight(), newoverlay.getHeight());
					imagebckg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
					BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
					Graphics g = combined.getGraphics();
					g.drawImage(imagebckg, 0, 0, null);
					g.drawImage(newoverlay, 0, 0, null);
					boolean x = ImageIO.write(combined, "PNG", new File("combined.png"));*/
				//}
		        return image;    
				//return path+"Outter.png";//bufferedImagetoImage(imagebckg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			image = new Image(Display.getCurrent(), path + icons[0]);
			return image;
		}
		return null;
	}
	
	
	
	public java.awt.Image bufferedImagetoImage(BufferedImage bi) {
	    return Toolkit.getDefaultToolkit().createImage(bi.getSource());
	}
	
	
	
}
