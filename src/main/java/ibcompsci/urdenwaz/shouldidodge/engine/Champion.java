package ibcompsci.urdenwaz.shouldidodge.engine;

public class Champion {
	private boolean dodge; 
	private float winrate; 
	private boolean loseStreak; 
	private int games; 
	private String ID; 
	private String acountID; 
	private String puuID;
	private String name; 
	public Champion(String ID, String acountID, String puuID, String name) {
		this.acountID = acountID; 
		this.ID = ID; 
		this.puuID = puuID;
		this.name = name;
	}
	public boolean shouldIdodge() {
		return loseStreak || (winrate <= 45.0);
	}
	public void getWinRate() {
		
	}
	
	
}
