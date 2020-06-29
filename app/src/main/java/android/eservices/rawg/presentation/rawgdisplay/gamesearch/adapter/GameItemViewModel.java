package android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter;

/**
 * The version of a model of a Game used to be displayed to the user
 */
public class GameItemViewModel {

    private String gameId;
    private String iconUrl;
    private String gameTitle;
    private String gameRating;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameRating() {
        return gameRating;
    }

    public void setGameRating(String gameRating) {
        this.gameRating = gameRating;
    }
}
