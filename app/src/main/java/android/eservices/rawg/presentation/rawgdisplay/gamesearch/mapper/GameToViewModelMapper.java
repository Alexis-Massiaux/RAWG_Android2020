package android.eservices.rawg.presentation.rawgdisplay.gamesearch.mapper;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *  Converts objects to a GameItemViewModel version
 */
public class GameToViewModelMapper {

    /**
     * Allows you to convert a model into its view model version
     * @param game - a Game model
     * @return - a GameItemViewModel
     */
    public GameItemViewModel map(Game game) {
        GameItemViewModel gameItemViewModel = new GameItemViewModel();

        gameItemViewModel.setGameId(game.getGameId());
        gameItemViewModel.setGameRating(game.getRating());
        gameItemViewModel.setGameTitle(game.getTitle());
        gameItemViewModel.setIconUrl(game.getImageLinks());

        return gameItemViewModel;
    }

    /**
     * Allows you to convert a list of models into their view model version
     * @param games - the list of Game
     * @return - a list of GameItemViewModel
     */
    public List<GameItemViewModel> map(List<Game> games) {
        List<GameItemViewModel> gameItemViewModels = new ArrayList<>();

        for(Game game : games) {
            gameItemViewModels.add(map(game));
        }
        return gameItemViewModels;
    }

}
