package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class ChampionPanel extends JPanel {
	
	private Rectangle[] subBounds = new Rectangle[5];
	private User[] users = new User[5];
	
	private final int n = 5;
	
	public ChampionPanel() {
		this.setLayout(null);
	}
	
	public void updateBounds() {
		
		Rectangle b = getBounds();
		
		for (int i = 0; i < n; i++) {
			User u = (users[i] == null) ? new User() : users[i];
			subBounds[i] = new Rectangle(0, (b.height/n)*i, b.width, b.height/n);
			u.setBounds(subBounds[i]);
			u.setBorder(
					(users[i] == null) ? new javax.swing.border.LineBorder(java.awt.Color.BLACK) : u.getBorder()
							);
			users[i] = u;
			this.add(u);
			
		}
		
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		updateBounds();
	}
	

}
