package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class TextListener extends JPanel {
	
	public TextListener(Rectangle r) {
		super.setBounds(0, 0, r.width, r.height);
		setOpaque(true);
		setBackground(new java.awt.Color(0, 10, 80, 255));
	}
	
}
