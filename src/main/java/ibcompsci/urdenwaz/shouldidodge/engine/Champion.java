package ibcompsci.urdenwaz.shouldidodge.engine;

import java.io.IOException;
import java.util.*;
public class Champion {
	
    private static final String ENDPOINT = "na1.api.riotgames.com";
    
	private boolean dodge; 
	private float winrate; 
	private boolean loseStreak; 
	private int games; 
	private String ID; 
	private String acountID; 
	private String puuID;
	private String name; 
    private ApiClient client;
	public Champion(String ID, String acountID, String puuID, String name) throws IOException {
		this.acountID = acountID; 
		this.ID = ID; 
		this.puuID = puuID;
		this.name = name;
		this.client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"));
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
