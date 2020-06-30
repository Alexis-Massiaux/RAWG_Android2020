package android.eservices.rawg.data.api;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.data.api.model.GameSearchResponse;
import android.eservices.rawg.data.api.model.YoutubeVideoSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Queries set up to call the API
 */
public interface GameService {

    /**
     * Retrieves all games based on a part of a game title
     * @param keywords - user input
     * @return - a list of games found
     */
    @GET("games")
    Single<GameSearchResponse> getGamesSearchResponses(@Query("search") String keywords);

    /**
     * Finds the detail of a game from its id
     * @param id - game id
     * @return - the game found
     */
    @GET("games/{id}")
    Single<Game> getGame(@Path("id") String id);

    /**
     * Retrieves all Youtube videos based on a game id
     * @param id - game id
     * @return - a list of videos found
     */
    @GET("games/{id}/youtube")
    Single<YoutubeVideoSearchResponse> getYoutubeVideos(@Path("id") String id);
}
