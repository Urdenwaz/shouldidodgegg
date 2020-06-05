package ibcompsci.urdenwaz.shouldidodge;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiValue;
import ibcompsci.urdenwaz.shouldidodge.engine.ChampionLookup;
import ibcompsci.urdenwaz.shouldidodge.ui.MainUI;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final String ENDPOINT = "na1.api.riotgames.com";

    //todo UI
    //todo algorithm
    public static void main(String[] args) throws Exception {
        JFrame frame = new MainUI("ShouldIDodge.gg");
        frame.setVisible(true);
    }

    public static void championLookupExample() throws ApiException {
        ChampionLookup lookup = new ChampionLookup();
        System.out.println(lookup.getChampionID("Azir"));
    }

    public static void summonerExample() throws ApiException, IOException {
        ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"));
        String summonerID = client.getSummoner("Urdenwaz").get("id");

        List<ApiValue> champions = client.getChampions(summonerID);
        for (ApiValue val : champions) {
            System.out.println(val.raw());
        }
    }

    public static void matchHistoryExample() throws ApiException, IOException {
        ApiClient client = new ApiClient(ENDPOINT, ApiClient.loadKey("key.txt"));
        String accountID = client.getSummoner("Urdenwaz").get("accountId");
        List<ApiValue> matchHistory = client.getMatchHistory(accountID, 10);
    }

}
