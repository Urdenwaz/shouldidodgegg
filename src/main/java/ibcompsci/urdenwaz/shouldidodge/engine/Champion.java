package ibcompsci.urdenwaz.shouldidodge.engine;

public class Champion {
	private boolean dodge; 
	private float winrate; 
	private boolean loseStreak; 
	private int games; 
	private String ID; 
	private String acountID; 
	private String puuID;
	
	public boolean shouldIdodge() {
		return loseStreak || (winrate <= 45.0);
	}
	public void getWinRate() {
		
	}
	
	
}
