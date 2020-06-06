package ibcompsci.urdenwaz.shouldidodge.engine;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.URL;
import java.util.function.Function;

public class ChampionLookup {

    ApiValue championList;

    Gson gson;
    JsonParser parser;
    OkHttpClient http;

    // See http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json
    // Needs raw champion list for Champion Key lookup
    public ChampionLookup() throws ApiException {
        this(new GsonBuilder().setPrettyPrinting().create(), new JsonParser(),
                new OkHttpClient());
    }

    public ChampionLookup(Gson gson, JsonParser parser, OkHttpClient http) throws ApiException {
        this.gson = gson;
        this.parser = parser;
        this.http = http;

        championList = getListFromDD();
    }

    public String getChampionID(String name) {
        return championList.getJsonObject("data").getAsJsonObject(name).get("key").getAsString();
    }

    public String getChampionListRaw() {
        return championList.raw();
    }

    private <T> T getFromDD(Function<JsonElement, T> evaluator) throws ApiException {
        try {
            Request request = new Request.Builder()
                    .url(new URL("https","ddragon.leagueoflegends.com", "/cdn/10.11.1/data/en_US/champion.json"))
                    .get()
                    .build();

            try (Response response = http.newCall(request).execute()) {
                JsonElement element = parser.parse(response.body().charStream());
                return evaluator.apply(element);
            }
        } catch (Exception e) {
            throw new ApiException("Error", e);
        }
    }

    public ApiValue getListFromDD() throws ApiException {
        return getFromDD((element) -> {
            JsonObject object = element.getAsJsonObject();
            return new ApiValue(gson, object);
        });
    }

}
