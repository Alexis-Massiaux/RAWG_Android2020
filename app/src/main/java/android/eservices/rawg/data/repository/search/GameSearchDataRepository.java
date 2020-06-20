package android.eservices.rawg.data.repository.search;

import android.eservices.rawg.data.api.model.GameSearchResponse;

import io.reactivex.Single;

public class GameSearchDataRepository {

    private GameSearchRemoteDataSource gameSearchRemoteDataSource;

    public GameSearchDataRepository(GameSearchRemoteDataSource gameSearchRemoteDataSource) {
        this.gameSearchRemoteDataSource = gameSearchRemoteDataSource;
    }

    public Single<GameSearchResponse> getGames(String keywords) {
        return gameSearchRemoteDataSource.getGames(keywords);
    }
}
