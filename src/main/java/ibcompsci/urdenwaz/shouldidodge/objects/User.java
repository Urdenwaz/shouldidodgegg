package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.DdragonLookup;
import ibcompsci.urdenwaz.shouldidodge.engine.Summoner;
import ibcompsci.urdenwaz.shouldidodge.resources.FontGenerator;
import ibcompsci.urdenwaz.shouldidodge.resources.ImageModifier;

public class User extends JPanel {
	
	private Summoner summoner;
	
	private ImageIcon summonerIcon;
	private JLabel iconBox;
	
	private String summonerName = " ";
	private JTextArea nameArea;
	
	private JComboBox champion;
	private JLabel champLabel;
	private JComboBox role;
	private JLabel roleLabel;
	
	private static final int margin = 10;
	private JLabel verdictBox;
	private boolean dodge;
	
	private static Font sansBig;
	private static Font sans;
	
	private static ImageIcon confirm;
	private static ImageIcon deny;
	
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
	
	public void inputData(String s, Summoner summoner) {
		
		if (summoner == this.summoner) return; 
		
		this.summoner = summoner;
		
		summonerName = s;
		
		nameArea.setText(summonerName);
		FontMetrics fm = nameArea.getFontMetrics(sansBig);
		nameArea.setFont(sansBig);
		nameArea.setBounds(iconBox.getX() + iconBox.getWidth() + 5,
				(getBounds().height-fm.getHeight())/2,
				getBounds().width/2 - iconBox.getWidth() - margin*2,
				fm.getHeight());
		
		if (summoner != null) {
			try {
				Image summonerImage = summoner.getProfileIcon();
				summonerIcon = new ImageIcon(
						ImageModifier.resizeImage(summonerImage, iconBox.getWidth(), iconBox.getHeight())
						);
				iconBox.setIcon(summonerIcon);
			} catch (IOException e) {}
		}
		
		
	}
	
	public static void initUser(Rectangle r) {
		Image confirmImage = null;
		Image denyImage = null;
		
		try {
			confirmImage = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/check.png"));
			denyImage = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/cancel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int boxSide = r.height - 2*margin;
		
		confirm = new ImageIcon(
				ImageModifier.resizeImage(confirmImage, boxSide, boxSide));
		deny = new ImageIcon(
				ImageModifier.resizeImage(denyImage, boxSide, boxSide));
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		
		// area for the summoner icon to go into
		iconBox = new JLabel();
		iconBox.setBounds(margin, margin, r.height-margin*2, r.height-margin*2);
		iconBox.setBorder(new javax.swing.border.LineBorder(java.awt.Color.BLACK));
		add(iconBox);
		
		// summoner name
		nameArea = new JTextArea();
		sansBig = FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 20, nameArea.getFont());
		nameArea.setEditable(true);
		nameArea.setOpaque(false);
		
		add(nameArea);
		
		Rectangle b = getBounds();
		
		// dropdown boxes
		
		roleLabel = new JLabel("Role");
		sans = FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 12, nameArea.getFont());
		roleLabel.setFont(sans);
		FontMetrics fm = roleLabel.getFontMetrics(sans);
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
		int boxSize = r.height - 2*margin;
		verdictBox = new JLabel();
		verdictBox.setBounds(r.width - r.height + margin, margin, boxSize, boxSize);
		
		add(verdictBox);
		
	}
	
	public void reset() {
		verdictBox.setIcon(null);
		setBackground(null);
	}
	
	public void dodge(boolean ishoulddodge) {
		
		if (ishoulddodge) {
			verdictBox.setIcon(deny);
			System.out.print(" dodged");
			setBackground(java.awt.Color.PINK);
		} else {
			verdictBox.setIcon(confirm);
			setBackground(java.awt.Color.CYAN);
		}
		dodge = ishoulddodge;
	}

	
	public String getSummonerName() {
		return nameArea.getText();
	}
	
}
