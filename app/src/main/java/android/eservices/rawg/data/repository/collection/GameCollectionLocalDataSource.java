package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.db.GameDatabase;
import android.eservices.rawg.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Implementation of actions to retrieve basic data
 */
public class GameCollectionLocalDataSource {

    GameDatabase gameDatabase;

    public GameCollectionLocalDataSource(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    /**
     * Retrieves all game save in the database
     * @return - a list of GameEntity
     */
    public Flowable<List<GameEntity>> getCollection() {
        return this.gameDatabase.gameDao().getCollection();
    }

    /**
     * Retrieves all game id savec in the database
     * @return - a list of game id
     */
    public Single<List<String>> getCollectionId() {
        return this.gameDatabase.gameDao().getCollectionId();
    }

    /**
     * Finds a game in the database according to a game id
     * @param id - a game id
     * @return - a game
     */
    public GameEntity findById(String id) {
        return this.gameDatabase.gameDao().findById(id);
    }

    /**
     * Addition of a game in the database
     * @param gameEntity - a game
     * @return - the result of the action
     */
    public Completable addGameToCollection(GameEntity gameEntity) {
        return gameDatabase.gameDao().addGame(gameEntity);
    }

    /**
     * Deletion of a game in the database
     * @param id - game id
     * @return - the result of the action
     */
    public Completable removeGameFromCollection(String id) {
        return this.gameDatabase.gameDao().deleteGameFromCollection(id);
    }
}
