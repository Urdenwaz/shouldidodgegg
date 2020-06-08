package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;

public class InteractivePanel extends JPanel {
	
	private Rectangle[] subBounds;
	
	private ChampionPanel cp;
	private JLabel jl;
	
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
			add(jl);
		} else {
			remove(cp);
			remove(jl);
			add(tl);
		}
		update(getGraphics());
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		subBounds = new Rectangle[] {
				new Rectangle(0, 0, r.width, r.height - 50),
				new Rectangle(0, r.height - 50, r.width, 50)
		};
		
		tl = new TextListener(r);
		
		tl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) { 
				swapPanels();
				System.out.println("hi");
			}

		});
		
		
		cp = new ChampionPanel();
		cp.setBounds(subBounds[0]);
		
		jl = new JLabel("Lorem Ipsum");
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setBounds(subBounds[1]);
		
		add(tl);
		
	}
	
	
}
