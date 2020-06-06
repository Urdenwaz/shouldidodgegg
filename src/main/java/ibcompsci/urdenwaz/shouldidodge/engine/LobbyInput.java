package ibcompsci.urdenwaz.shouldidodge.engine;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LobbyInput {
    private static final String ENDPOINT = "na1.api.riotgames.com";
	ApiClient client;
	public LobbyInput(ApiClient client) throws IOException {
		 this.client = client;
	}
	public List<String> parser(String s) {
		HashSet<String> repeats = new HashSet<>(); 
		Scanner sc = new Scanner(s); 
		StringBuilder name = new StringBuilder();
		LinkedList<String> res = new LinkedList<String>(); 
		while(sc.hasNext() && repeats.size() != 5) {
			String current = sc.next(); 
			
			if(current.equals("joined")) {
				if(!repeats.contains(name.toString())) {
					repeats.add(name.toString());
					res.add(name.toString());
				}
				//skips 2 tokens 
				name = new StringBuilder(); 
				for(int i = 0; i<2; i++) {
					if(sc.hasNext()) {
						sc.next(); 
					}
				}
				continue; 
			}
			name.append(name.length() == 0 ? current: " "+current);
		}
		return res;
 	}
	public Champion getChampion(String Username) throws ApiException, IOException {
		ApiValue user = client.getSummoner(Username);
		
		return new Champion(user.get("id"),user.get("accountId"),user.get("puuid"), user.get("name"), client);
	}
	
}
