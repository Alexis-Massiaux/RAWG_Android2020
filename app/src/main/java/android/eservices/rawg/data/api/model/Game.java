package android.eservices.rawg.data.api.model;

public class Game {

    private String id;
    private String name;
    private String background_image;
    private String rating;

    public String getTitle() {
        return name;
    }

    public String getImageLinks() {
        return background_image;
    }

    public String getRating() {
        return rating;
    }

    public String getGameId() {
        return id;
    }

    public String toString() {
        return String.format("id : %s - title : %s - link : %s - rating : %s", getGameId(), getTitle(), getImageLinks(), getRating());
    }
}
