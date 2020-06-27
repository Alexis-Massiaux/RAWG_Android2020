package android.eservices.rawg.db.dao;

import android.eservices.rawg.db.entity.GameEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface GameDao {

    @Query("select * from games")
    Flowable<List<GameEntity>> getCollection();

    @Query("select * from games where id = :id")
    GameEntity findById(int id);

    @Query("select * from games where title = :title")
    GameEntity findByTitle(String title);

    @Insert
    Completable addGame(GameEntity game);

    @Delete
    void deleteGame(GameEntity gameEntity);
}
