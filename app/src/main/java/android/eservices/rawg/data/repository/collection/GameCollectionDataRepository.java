package android.eservices.rawg.data.repository.collection;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.data.api.model.YoutubeVideoSearchResponse;
import android.eservices.rawg.data.repository.collection.mapper.GameToEntityModelMapper;
import android.eservices.rawg.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Implementation of actions to retrieve basic or query data
 */
public class GameCollectionDataRepository {

    private GameToEntityModelMapper gameToEntityModelMapper;
    private GameCollectionLocalDataSource gameCollectionLocalDataSource;
    private GameCollectionRemoteDataSource gameCollectionRemoteDataSource;

    public GameCollectionDataRepository(GameCollectionLocalDataSource gameCollectionLocalDataSource, GameCollectionRemoteDataSource gameCollectionRemoteDataSource) {
        this.gameCollectionLocalDataSource = gameCollectionLocalDataSource;
        this.gameCollectionRemoteDataSource = gameCollectionRemoteDataSource;
        this.gameToEntityModelMapper = new GameToEntityModelMapper();
    }

    /**
     * Finds the detail of a game within the api according to its id
     * @param id - game id
     * @return - a game
     */
    public Single<Game> getGame(String id) {
        return gameCollectionRemoteDataSource.getGame(id);
    }

    /**
     * Finds all games within the database
     * @return - a list of games
     */
    public Flowable<List<GameEntity>> getGamesInDatabase() {
        return gameCollectionLocalDataSource.getCollection();
    }

    /**
     * Addition of a game in the database
     * @param id - game id
     * @return - the result of the action
     */
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

    /**
     * Deletion of a game in the database
     * @param id - game id
     * @return - the result of the action
     */
    public Completable removeGameFromCollection(String id) {
        return gameCollectionLocalDataSource.removeGameFromCollection(id);
    }

    /**
     * Finds the Youtube video within the api and related to the games into the database
     * @return - a list of Youtube videos
     */
    public Single<List<YoutubeVideoSearchResponse>> getYoutubeVideosFromCollection() {
        return gameCollectionLocalDataSource.getCollectionId()
                .flatMap(new Function<List<String>, SingleSource<List<YoutubeVideoSearchResponse>>>() {

                    @Override
                    public SingleSource<List<YoutubeVideoSearchResponse>> apply(List<String> ids) throws Exception {
                        return Flowable.fromIterable(ids)
                                .flatMapSingle(new Function<String, SingleSource<YoutubeVideoSearchResponse>>() {

                                    @Override
                                    public SingleSource<YoutubeVideoSearchResponse> apply(String id) throws Exception {
                                        return gameCollectionRemoteDataSource.getYoutubeVideos(id);
                                    }
                                }).toList();
                    }
                });
    }
}
