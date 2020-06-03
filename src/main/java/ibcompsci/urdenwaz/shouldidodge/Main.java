package ibcompsci.urdenwaz.shouldidodge;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiValue;
import ibcompsci.urdenwaz.shouldidodge.engine.ChampionLookup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final String ENDPOINT = "na1.api.riotgames.com";

    //todo UI
    //todo algorithm
    public static void main(String[] args) throws Exception {
        matchHistoryExample();
    }

    public static void championLookupExample() throws ApiException {
        ChampionLookup lookup = new ChampionLookup();
        System.out.println(lookup.getChampionID("Azir"));
    }

    public static void summonerExample() throws ApiException, IOException {
        BufferedReader rdr = new BufferedReader(new FileReader("key.txt"));
        String key = rdr.readLine();

        ApiClient client = new ApiClient(ENDPOINT, key);
        String summonerID = client.getSummoner("Urdenwaz").get("id");

        List<ApiValue> champions = client.getChampions(summonerID);
        for (ApiValue val : champions) {
            System.out.println(val.raw());
        }
    }

    public static void matchHistoryExample() throws ApiException, IOException {
        BufferedReader rdr = new BufferedReader(new FileReader("key.txt"));
        String key = rdr.readLine();

        ApiClient client = new ApiClient(ENDPOINT, key);
        String accountID = client.getSummoner("Urdenwaz").get("accountId");
        ApiValue matchHistory = client.getMatchHistory(accountID);
        System.out.println(matchHistory.raw());
    }

}
