package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.resources.ImageModifier;
import ibcompsci.urdenwaz.shouldidodge.ui.MainUI;


public class Toolbar extends JPanel {
	
	private final MainUI home;
	
	private JLabel design;
	private Image designSource;
	
	private JLabel close;
	private JLabel min;
	
	private JLabel back;
	
	private Image backSource;
	private Image closeSource;
	private Image minSource;
	
	public Toolbar(MainUI home) {
		this.home = home;
		
		setLayout(null);
		
		close = new JLabel();
		min = new JLabel();
		back = new JLabel();
		
		design = new JLabel();
		
		try {
			designSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/bar.png"));
			minSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/min.png"));
			closeSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/close.png"));
			backSource = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/back.png"));
		} catch (IOException e) {}
		
		min.setIcon(new ImageIcon(minSource));
		min.setOpaque(false);
		min.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		close.setIcon(new ImageIcon(closeSource));
		close.setOpaque(false);
		close.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		
		back.setIcon(new ImageIcon(backSource));
		back.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		back.setOpaque(false);
		back.setVisible(false);
		
		design.setIcon(new ImageIcon(designSource));
		
		add(min);
		add(close);
		add(back);
		add(design, -1);

		
		min.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				min();
			}
		});
		close.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				exit();
			}
		});
		
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				home.framePress(evt);
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				home.frameDrag(evt);
			}
		});
		
		
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void min() {
		home.setState(home.ICONIFIED);
	}
	
	public void back() {
		back.setVisible(!back.isVisible());
		update(getGraphics());
	}
	
	public void addBack(MouseAdapter ma) {
		back.addMouseListener(ma);
	}
	
	public JLabel getBack() {
		return back;
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		
		int margin = 1;
		
		design.setBounds(0, 0, r.width, r.height - margin);
		ImageModifier.resizeImage(designSource, design.getWidth(), design.getHeight());
		
		close.setBounds(r.width-r.height-margin, margin, r.height-2*margin, r.height-2*margin);
		ImageModifier.resizeImage(closeSource, close.getWidth(), close.getHeight());
		
		min.setBounds(r.width-2*(r.height+margin), margin, r.height-2*margin, r.height-2*margin);
		ImageModifier.resizeImage(closeSource, close.getWidth(), close.getHeight());
		
		back.setBounds(margin, margin, r.height*3/2, r.height-2*margin);
		ImageModifier.resizeImage(backSource, back.getWidth(), back.getHeight());
		
	}
	
	
	
}
