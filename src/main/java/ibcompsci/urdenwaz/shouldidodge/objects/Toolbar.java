package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.ui.MainUI;


public class Toolbar extends JPanel {
	
	private final MainUI home;
	
	public Toolbar(MainUI home) {
		this.home = home;
		
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
	
	
	
}
