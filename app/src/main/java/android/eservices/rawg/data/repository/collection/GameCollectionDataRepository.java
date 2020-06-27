package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class GameCollectionDataRepository {

    private GameCollectionLocalDataSource gameCollectionLocalDataSource;
    private GameCollectionRemoteDataSource gameCollectionRemoteDataSource;

    public GameCollectionDataRepository(GameCollectionLocalDataSource gameCollectionLocalDataSource, GameCollectionRemoteDataSource gameCollectionRemoteDataSource) {
        this.gameCollectionLocalDataSource = gameCollectionLocalDataSource;
        this.gameCollectionRemoteDataSource = gameCollectionRemoteDataSource;
    }

    public Single<Game> getGame(String id) {
        return gameCollectionRemoteDataSource.getGame(id);
    }

    public Flowable<List<GameEntity>> getGamesInDatabase() {
        return gameCollectionLocalDataSource.getCollection();
    }
}
