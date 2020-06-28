package android.eservices.rawg.data.repository.collection.mapper;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.db.entity.GameEntity;

public class GameToEntityModelMapper {

    public GameEntity map(Game game) {
        GameEntity gameEntity = new GameEntity();

        gameEntity.setId(game.getGameId());
        gameEntity.setTitle(game.getTitle());
        gameEntity.setRating(game.getRating());
        gameEntity.setImageLink(game.getImageLinks());

        return gameEntity;
    }
}
