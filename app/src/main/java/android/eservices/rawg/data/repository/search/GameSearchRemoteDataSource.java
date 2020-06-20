package android.eservices.rawg.data.repository.search;

import android.eservices.rawg.data.api.GameService;
import android.eservices.rawg.data.api.model.GameSearchResponse;

import io.reactivex.Single;

public class GameSearchRemoteDataSource {

    private GameService gameService;

    public GameSearchRemoteDataSource(GameService gameService) {
        this.gameService = gameService;
    }

    Single<GameSearchResponse> getGames(String keywords) {
        return this.gameService.getGamesSearchResponses(keywords);
    }
}
