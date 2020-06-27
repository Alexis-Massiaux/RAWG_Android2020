package android.eservices.rawg.db;

import android.eservices.rawg.db.dao.GameDao;
import android.eservices.rawg.db.entity.GameEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GameEntity.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
}