package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.resources.ImageModifier;

public class InteractivePanel extends JPanel {
	
	private Rectangle[] subBounds;
	
	private ChampionPanel cp;
	
	private final int bHeight = 50;
	private JLabel run;
	private JLabel refresh;
	
	private JLabel backgroundImage;
	
	private TextListener tl;
	
	private ApiClient client;
	
	public InteractivePanel(ApiClient client) {
		setLayout(null);
		this.client = client;
		
	}
	
	private void swapPanels() {
		if (getComponentCount() == 1) {
			remove(tl);
			add(cp);
			add(run);
			add(refresh);
		} else {
			remove(cp);
			remove(run);
			remove(refresh);
			add(tl);
		}
		update(getGraphics());
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		subBounds = new Rectangle[] {
				new Rectangle(0, 0, r.width, r.height - bHeight),
				new Rectangle(bHeight, r.height - bHeight-1, r.width, bHeight),
				new Rectangle(0, r.height - bHeight-1, bHeight, bHeight)
		};
		
		// background image
		try {
			BufferedImage source = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/background2.png"));
			source = ImageModifier.verticalResize(source, r.width, r.height);
//			source = ImageModifier.centerCrop(source, r.width, r.height);
			backgroundImage = new JLabel();
			backgroundImage.setBounds(0, 0, r.width, r.height);
			backgroundImage.setIcon(new ImageIcon(source));
			add(backgroundImage);
		} catch (IOException e1) {}
		
				
		// first page which accepts the user input
		tl = new TextListener(r) {
			@Override
			public void triggerSwap(String s) {
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
		run = new JLabel("Lorem Ipsum");
		run.setHorizontalAlignment(JLabel.CENTER);
		run.setBounds(subBounds[1]);

		run.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				cp.shouldidodge();
				System.out.println("Button clicked");
			}
		});
		
		// reload button for manual changes
		refresh = new JLabel();
		refresh.setBounds(subBounds[2]);
		refresh.setBorder(new javax.swing.border.LineBorder(java.awt.Color.BLUE));
		
		refresh.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				cp.refreshSummoners();
			}
		});
		
		try {
			Image refImage = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/refresh.png"));
			refresh.setIcon(
					new ImageIcon(ImageModifier.resizeImage(refImage, refresh.getWidth(), refresh.getHeight())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}
