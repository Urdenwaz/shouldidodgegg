package ibcompsci.urdenwaz.shouldidodge.engine;

import java.io.IOException;
import java.util.*;

public class Champion {
    
	private boolean dodge; 
	private float winrate; 
	private boolean loseStreak; 
	private int games; 
	private String ID; 
	private String accountID;
	private String puuID;
	private String name; 
    private ApiClient client;

	public Champion(String ID, String accountID, String puuID, String name, ApiClient client) throws IOException, ApiException {
		this.accountID = accountID;
		this.ID = ID; 
		this.puuID = puuID;
		this.name = name;
		this.client = client;
		getWinRate();
	}

	public boolean shouldIdodge() {
		return loseStreak || (winrate <= 45.0 && games > 40);
	}

	public void getWinRate() throws ApiException {
		ArrayList<ApiValue> ranked = (ArrayList<ApiValue>) client.getLeagues(ID);
		float win = Float.parseFloat(ranked.get(0).get("wins"));
		float loss = Float.parseFloat(ranked.get(0).get("losses"));
		games = (int) (win+loss); 
		winrate = win/(win+loss);
	}

	public void getloseStreak() {
		
	}
	
}
