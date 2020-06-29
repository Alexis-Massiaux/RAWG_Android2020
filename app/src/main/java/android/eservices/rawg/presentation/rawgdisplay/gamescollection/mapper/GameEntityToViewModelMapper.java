package android.eservices.rawg.presentation.rawgdisplay.gamescollection.mapper;

import android.eservices.rawg.db.entity.GameEntity;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *  Converts objects to a GameCollectionViewModel version
 */
public class GameEntityToViewModelMapper {

    /**
     * Allows you to convert an entity into its view model version
     * @param game - a Game entity
     * @return - a GameCollectionViewModel
     */
    public GameCollectionViewModel map(GameEntity game) {
        GameCollectionViewModel gameCollectionViewModel = new GameCollectionViewModel();

        gameCollectionViewModel.setGameId(game.getId());
        gameCollectionViewModel.setGameTitle(game.getTitle());
        gameCollectionViewModel.setGameRating(game.getRating());
        gameCollectionViewModel.setIconUrl(game.getImageLink());

        return gameCollectionViewModel;
    }

    /**
     * Allows you to convert a list of entities into their view model version
     * @param games - the list of Game
     * @return - a list of GameCollectionViewModel
     */
    public List<GameCollectionViewModel> map(List<GameEntity> games) {
        List<GameCollectionViewModel> gameCollectionViewModels = new ArrayList<>();

        for(GameEntity game : games) {
            gameCollectionViewModels.add(map(game));
        }

        return gameCollectionViewModels;
    }
}
