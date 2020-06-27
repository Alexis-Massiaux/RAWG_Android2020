package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.data.api.GameService;
import android.eservices.rawg.data.api.model.Game;

import io.reactivex.Single;

public class GameCollectionRemoteDataSource {

    private GameService gameService;

    public GameCollectionRemoteDataSource(GameService gameService) {
        this.gameService = gameService;
    }

    Single<Game> getGame(String id) {
        return this.gameService.getGame(id);
    }
}
