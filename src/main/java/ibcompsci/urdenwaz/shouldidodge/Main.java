package ibcompsci.urdenwaz.shouldidodge;

import ibcompsci.urdenwaz.shouldidodge.engine.*;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiValue;
import ibcompsci.urdenwaz.shouldidodge.engine.ChampionLookup;
import ibcompsci.urdenwaz.shouldidodge.engine.LobbyInput;

import ibcompsci.urdenwaz.shouldidodge.ui.MainUI;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Main {

    private static final String ENDPOINT = "na1.api.riotgames.com";
    private static final String patch = "10.11.1";
    //todo UI
    //todo algorithm
    public static void main(String[] args) throws Exception {
//        JFrame frame = new MainUI("ShouldIDodge.gg");
//        frameg.setVisible(true);
        ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"), patch);
    	LobbyInput Lobby = new LobbyInput(client);
//    	summonerExample();
    	Champion user1 = Lobby.getChampion("a j o s n 8");
    	
    	System.out.println(user1.getWinrate());
    	System.out.println(user1.getLoseStreak());
    	System.out.println(user1.shouldIdodge());
    	System.out.println(user1.firstTime(50));
    	System.out.println(user1.getMainRole());
    	System.out.println("is she a egirl:" + user1.isEGirl());
    	user1.getProfileIcon();
    }
   
    public static void championLookupExample() throws ApiException {
        ChampionLookup lookup = new ChampionLookup();
        System.out.println(lookup.getChampionID("Azir"));
    }

    public static void summonerExample() throws ApiException, IOException {
        ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"), patch);
        String summonerID = client.getSummoner("Urdenwaz").get("id");
        System.out.println(client.getSummoner("Urdenwaz").raw().toString());
//        List<ApiValue> champions = client.getChampions(summonerID);
//        for (ApiValue val : champions) {
//            System.out.println(val.raw());
//        }
    }

    public static void matchHistoryExample() throws ApiException, IOException {
        ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"), patch);
        String accountID = client.getSummoner("Urdenwaz").get("accountId");
        List<ApiValue> matchHistory = client.getMatchHistory(accountID, 10);
        
    }

    public static void getMatchExample() throws ApiException, IOException{
    	ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"), patch);
    	ApiValue match = client.getMatch("3428811840"); 
    	JsonArray participants = match.getJsonArray("participants");
    	JsonObject participant = participants.get(0).getAsJsonObject();
    	int id = participant.get("participantId").getAsInt();
    	JsonObject stats = participant.getAsJsonObject("stats");
    	
    	//System.out.println(stats.get("win").getAsBoolean());
    }

}
