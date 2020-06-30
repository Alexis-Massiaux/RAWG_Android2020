package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameSearchResponse {

    @SerializedName("results")
    private List<Game> games;
    int totalItems;

    public List<Game> getGames() {
        return games;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
