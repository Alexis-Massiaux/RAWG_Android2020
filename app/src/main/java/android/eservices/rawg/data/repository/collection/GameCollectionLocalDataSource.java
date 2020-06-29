package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.db.GameDatabase;
import android.eservices.rawg.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class GameCollectionLocalDataSource {

    GameDatabase gameDatabase;

    public GameCollectionLocalDataSource(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    public Flowable<List<GameEntity>> getCollection() {
        return this.gameDatabase.gameDao().getCollection();
    }

    public Single<List<String>> getCollectionId() {
        return this.gameDatabase.gameDao().getCollectionId();
    }

    public GameEntity findById(String id) {
        return this.gameDatabase.gameDao().findById(id);
    }

    public Completable addGameToCollection(GameEntity gameEntity) {
        return gameDatabase.gameDao().addGame(gameEntity);
    }
}
