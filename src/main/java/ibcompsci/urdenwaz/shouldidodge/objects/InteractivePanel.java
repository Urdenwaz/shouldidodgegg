package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
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
	
	private BufferedImage backgroundSource;
	private JLabel backgroundImage;
	
	private TextListener tl;
	
	private ApiClient client;
	
	public InteractivePanel(ApiClient client) {
		setLayout(null);
		this.client = client;
	}
	
	private void swapPanels() {
		System.out.println(getComponentCount());
		if (getComponentCount() == 1) {
			remove(tl);
			add(cp, 0);
			add(run, 0);
			add(refresh, 0);
		} else {
			remove(cp);
			remove(run);
			remove(refresh);
			add(tl);
		}
		add(backgroundImage, -1);
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
			backgroundSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/background3.png"));
			backgroundSource = ImageModifier.resizeImage(backgroundSource , r.width, r.height);
			backgroundImage = new JLabel();
			backgroundImage.setBounds(0, 0, r.width, r.height);
			RescaleOp dim = new RescaleOp(0.8f, 0, null);
			dim.filter(backgroundSource, backgroundSource);
			backgroundImage.setIcon(new ImageIcon(backgroundSource ));
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
			@Override
			public void superUpdate() {
				update(getGraphics());
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
		
		add(backgroundImage, -1);
		
		
	}
	
	
}
