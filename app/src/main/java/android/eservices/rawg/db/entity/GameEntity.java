package android.eservices.rawg.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="games")
public class GameEntity {

    @NonNull
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "image_link")
    public String imageLink;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "rating")
    public String rating;
}
