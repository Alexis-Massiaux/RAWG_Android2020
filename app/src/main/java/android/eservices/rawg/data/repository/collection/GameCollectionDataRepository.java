package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.data.repository.collection.mapper.GameToEntityModelMapper;
import android.eservices.rawg.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class GameCollectionDataRepository {

    private GameToEntityModelMapper gameToEntityModelMapper;
    private GameCollectionLocalDataSource gameCollectionLocalDataSource;
    private GameCollectionRemoteDataSource gameCollectionRemoteDataSource;

    public GameCollectionDataRepository(GameCollectionLocalDataSource gameCollectionLocalDataSource, GameCollectionRemoteDataSource gameCollectionRemoteDataSource) {
        this.gameCollectionLocalDataSource = gameCollectionLocalDataSource;
        this.gameCollectionRemoteDataSource = gameCollectionRemoteDataSource;
        this.gameToEntityModelMapper = new GameToEntityModelMapper();
    }

    public Single<Game> getGame(String id) {
        return gameCollectionRemoteDataSource.getGame(id);
    }

    public Flowable<List<GameEntity>> getGamesInDatabase() {
        return gameCollectionLocalDataSource.getCollection();
    }

    public Completable addGameToCollection(String id) {
        return gameCollectionRemoteDataSource.getGame(id)
                .map(new Function<Game, GameEntity>() {
                    @Override
                    public GameEntity apply(Game game) throws Exception {
                        return gameToEntityModelMapper.map(game);
                    }
                })
                .flatMapCompletable(new Function<GameEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(GameEntity gameEntity) throws Exception {
                        return gameCollectionLocalDataSource.addGameToCollection(gameEntity);
                    }
                });
    }
}
