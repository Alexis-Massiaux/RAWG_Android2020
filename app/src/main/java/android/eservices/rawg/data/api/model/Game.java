package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

public class Game {

    private String id;
    private String name;
    private String rating;
    @SerializedName("background_image")
    private String backgroundImage;

    public String getTitle() {
        return name;
    }

    public String getImageLinks() {
        return backgroundImage;
    }

    public String getRating() {
        return rating;
    }

    public String getGameId() {
        return id;
    }
}
