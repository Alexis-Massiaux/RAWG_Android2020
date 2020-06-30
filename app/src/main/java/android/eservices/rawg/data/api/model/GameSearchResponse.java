package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * List of the games retrieved by the API
 */
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
