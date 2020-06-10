package ibcompsci.urdenwaz.shouldidodge.resources;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageModifier {
	
	public static Image resizeImage(Image i, int width, int height) {
		return (resizeImage((BufferedImage) i, width, height));
	}
	
	public static BufferedImage resizeImage(BufferedImage i, int width, int height) {
		
	    BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = ret.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(i, 0, 0, width, height, null);
	    g2.dispose();
	    
	    return ret;
	    
	}
	
	public static BufferedImage verticalResize(BufferedImage i, int width, int height) {
		
		double h = i.getHeight(), w = i.getWidth();
		double ratio = height / h;
		
		BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = ret.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(i, (int) (width - w*ratio)/2, 0, (int) (w * ratio), (int) (h * ratio), null);
	    g2.dispose();
	    
	    return ret;
		
	}
	
	public static BufferedImage centerCrop(BufferedImage i, int width, int height) {
		
		BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = ret.createGraphics();
		
		int w = i.getWidth();
		
		g2.drawImage(i, (w-width)/2, 0, width, height, null);
		g2.dispose();
		
		return ret;
		
	}

}
