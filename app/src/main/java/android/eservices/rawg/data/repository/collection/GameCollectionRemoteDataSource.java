package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.data.api.GameService;
import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.data.api.model.YoutubeVideoSearchResponse;

import io.reactivex.Single;

/**
 * Implementation of actions to retrieve query data
 */
public class GameCollectionRemoteDataSource {

    private GameService gameService;

    public GameCollectionRemoteDataSource(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Finds the detail of a game within the api according to its id
     * @param id - a game id
     * @return - a game
     */
    Single<Game> getGame(String id) {
        return this.gameService.getGame(id);
    }

    /**
     * Finds the Youtube video within the api and related to a game id
     * @param id - a game id
     * @return - a list of Youtube videos
     */
    Single<YoutubeVideoSearchResponse> getYoutubeVideos(String id) {
        return this.gameService.getYoutubeVideos(id);
    }

}
