package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.resources.ImageModifier;

public class InteractivePanel extends JPanel {
	
	private Rectangle[] subBounds;
	
	private ChampionPanel cp;
	
	private final int bHeight = 50;
	private JLabel run;
	private JLabel refresh;
	
	private BufferedImage backgroundSource;
	private JLabel backgroundImage;
	
	private TextListener tl;
	
	private ApiClient client;
	
	public InteractivePanel(ApiClient client) {
		setLayout(null);
		this.client = client;
	}
	
	public void swapPanels() {
		cp.add(backgroundImage);
		cp.remove(backgroundImage);
		if (getComponentCount() == 1) {
			remove(tl);
			add(cp, 0);
			add(run, 0);
			add(refresh, 0);
			getBack();
		} else {
			cp.clearSummoners();
			remove(cp);
			remove(run);
			remove(refresh);
			add(tl);
			tl.reset();
		}
		add(backgroundImage, -1);
		update(getGraphics());
	}
	
	public void getBack() {}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		subBounds = new Rectangle[] {
				new Rectangle(0, 0, r.width, r.height - bHeight),
				new Rectangle(0, r.height - bHeight-1, r.width, bHeight),
				new Rectangle(2, r.height - bHeight+1, bHeight-4, bHeight-4)
		};
		
		// background image
		try {
			backgroundSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/background3.png"));
			backgroundSource = ImageModifier.resizeImage(backgroundSource , r.width, r.height);
			backgroundImage = new JLabel();
			backgroundImage.setBounds(0, 0, r.width, r.height);
			RescaleOp dim = new RescaleOp(0.8f, 0, null);
			dim.filter(backgroundSource, backgroundSource);
			backgroundImage.setIcon(new ImageIcon(backgroundSource));
		} catch (IOException e1) {}
		
		// first page which accepts the user input
		tl = new TextListener(r) {
			@Override
			public void triggerSwap(String s) {
				tl.add(backgroundImage);
				update(getGraphics());
				tl.remove(backgroundImage);
				cp.addUsers(s);
				swapPanels();
			}
		};
		add(tl);


		// panel with all the summoner details and role inputs
		cp = new ChampionPanel(client);
		cp.setBounds(subBounds[0]);
		
		
		// buttons at the bottom.
		// button that returns the mandate.
		run = new JLabel("Click for results! Hit refresh after any edits.");
		run.setHorizontalAlignment(JLabel.CENTER);
		run.setBounds(subBounds[1]);
		
		
		run.setBorder(new CompoundBorder(
				BorderFactory.createEmptyBorder(0,-1,-1,-1),
				new javax.swing.border.LineBorder(java.awt.Color.WHITE)));
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 15);
		run.setFont(f);
		run.setForeground(java.awt.Color.WHITE);
		
		run.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		
		run.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				cp.shouldidodge();
			}
		});
		
		// reload button for manual changes
		refresh = new JLabel();
		refresh.setBounds(subBounds[2]);
		
		refresh.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				cp.refreshSummoners();
			}
		});
		
		refresh.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		
		try {
			Image refImage = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/refresh.png"));
			refresh.setIcon(
					new ImageIcon(ImageModifier.resizeImage(refImage, refresh.getWidth()-2, refresh.getHeight()-2)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		add(backgroundImage, -1);
		
		
	}
	
	
}
