package ibcompsci.urdenwaz.shouldidodge.engine;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiValue {

    private Gson gson;
    private JsonObject object;

    public ApiValue(Gson gson, JsonObject object) {
        this.gson = gson;
        this.object = object;
    }

    public String get(String name) {
        return object.get(name).getAsString();
    }

    public String raw() {
        return gson.toJson(object);
    }

    public JsonObject getJsonObject(String name) {
        return object.getAsJsonObject(name);
    }

    public JsonArray getJsonArray(String name) {
        return object.getAsJsonArray(name);
    }

}