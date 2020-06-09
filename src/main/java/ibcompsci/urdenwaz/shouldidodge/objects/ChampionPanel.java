package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.LobbyInput;
import ibcompsci.urdenwaz.shouldidodge.engine.Summoner;

public class ChampionPanel extends JPanel {
	
	private Rectangle[] subBounds = new Rectangle[5];
	private User[] users = new User[5];
	private String[] usernames;
	private Summoner[] summoners;
	
	private LobbyInput li;
	private ApiClient client;
	
	private final int n = 5;
	
	public ChampionPanel(ApiClient client) {
		this.setLayout(null);
		
		this.client = client;
		usernames = new String[5];
		summoners = new Summoner[5];
		
		try {
			li = new LobbyInput(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateBounds() {
		
		Rectangle b = getBounds();
		
		User.initUser(new Rectangle(0, b.height/n, b.width, b.height/n));
		
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
	
	public void addUsers(String s) {
		
		List<String> userList =  li.parser(s);
		
		int n = 0;
		for (String u : userList) {
			
			summoners[n] = li.getChampion(u);
			usernames[n] = (summoners[n] == null) ? "MoreThan16Characters" : u; 
			users[n].inputData(usernames[n], summoners[n]);
			
			n++;
		}
	}
	
	public void refreshSummoners() {
		for (int i = 0; i < 5; i++) {
			String name = users[i].getSummonerName();
			users[i].reset();
			if (!name.equals(usernames[i])) {
				summoners[i] = li.getChampion(name);
				usernames[i] = (summoners[i] == null) ? "MoreThan16Characters" : name;
			}
			users[i].inputData(usernames[i],  summoners[i]);
		}
		
		update(getGraphics());
		
	}
	
	public void shouldidodge() {
		
		for (int i = 0; i < 5; i++) {
			if (summoners[i] == null) continue;
			System.out.print(summoners[i].getName());
			users[i].dodge(
					summoners[i].shouldIdodge()
					);
			System.out.println();
		}
		
	}
	
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		updateBounds();
	}
	

}
