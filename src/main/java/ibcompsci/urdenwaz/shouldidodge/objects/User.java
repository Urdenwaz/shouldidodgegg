package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.DdragonLookup;

public class User extends JPanel {
	
	private Image summonerIcon;
	private JLabel iconBox;
	
	private String summonerName = "Urdenwaz";
	private JLabel nameLabel;
	
	private JComboBox champion;
	private JLabel champLabel;
	private JComboBox role;
	private JLabel roleLabel;
	
	private JLabel verdictBox;
	
	
	private static String[] championList;
	private static String[] roleList = {
			"", "top", "jungler", "mid", "bot", "support"
	};
	
	
	public static void __init__(String PATCH) throws ApiException {
		DdragonLookup lookup = new DdragonLookup(PATCH);
		
		String rawList = lookup.getChampionListRaw();
		
		Scanner sc = new Scanner(rawList);
		sc.useDelimiter("}");
		sc.next();
		int x = 1;
		StringTokenizer st;
		
		ArrayList<String> champArray = new ArrayList<>();
		
		while (sc.hasNext()) {
			String s = sc.next();
			if (x++ % 4 == 0) {
				st = new StringTokenizer(s, ":\"");
				st.nextToken();
				if (st.hasMoreElements()) {
					champArray.add(st.nextToken());
				}
			}
		}
		champArray.add(0, "Aatrox");
		
		championList = new String[champArray.size()+1];
		for (int i = 1; i < championList.length; i++) {
			championList[i] = champArray.get(i-1);
		}
		
		sc.close();
		
	}
	
	public User() {
		
		setLayout(null);
		
		champion = new JComboBox(championList);
		role = new JComboBox(roleList);
		
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		
		int margin = 10;
		
		// area for the summoner icon to go into
		iconBox = new JLabel();
		iconBox.setBounds(margin, margin, r.height-margin*2, r.height-margin*2);
		iconBox.setBorder(new javax.swing.border.LineBorder(java.awt.Color.BLACK));
		add(iconBox);
		
		// summoner name
		nameLabel = new JLabel();
		nameLabel.setText(summonerName);
		Font sansBig = FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 20, nameLabel.getFont());
		FontMetrics fm = nameLabel.getFontMetrics(sansBig);
		nameLabel.setFont(sansBig);
		nameLabel.setBounds(iconBox.getX() + iconBox.getWidth() + 5, (r.height-fm.getHeight())/2, fm.stringWidth(summonerName), fm.getHeight());
		add(nameLabel);
		
		
		Rectangle b = getBounds();
		
		// dropdown boxes
		
		roleLabel = new JLabel("Role");
		Font sans = FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 12, nameLabel.getFont());
		roleLabel.setFont(sans);
		fm = roleLabel.getFontMetrics(sans);
		roleLabel.setBounds(b.width/2, b.height/4 - fm.getHeight(), fm.stringWidth(roleLabel.getText()), fm.getHeight());
		
		add(roleLabel);
		
		role.setBounds(b.width/2, b.height/4, b.width/2 - r.height - margin*2, 20);
		role.setFont(sans);
		add(role);
		
		champLabel = new JLabel("Champion");
		champLabel.setFont(sans);
		champLabel.setBounds(b.width/2, b.height*3/4 - fm.getHeight(), fm.stringWidth(champLabel.getText()), fm.getHeight());
		add(champLabel);
		
		champion.setBounds(b.width/2, b.height*3/4, b.width/2 - r.height - margin*2, 20);
		champion.setFont(sans);
		add(champion);
		
		
		// verdict box
		verdictBox = new JLabel();
		verdictBox.setBounds(r.width - r.height + margin, margin, r.height - 2*margin, r.height - 2*margin);
		verdictBox.setBorder(new javax.swing.border.LineBorder(java.awt.Color.BLACK));
		add(verdictBox);
		
	}
	
}
