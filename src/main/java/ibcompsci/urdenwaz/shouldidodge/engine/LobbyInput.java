package ibcompsci.urdenwaz.shouldidodge.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LobbyInput {
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
	public Champion getChampion(String Username) {
		
	}
}
