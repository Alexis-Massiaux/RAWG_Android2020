package android.eservices.rawg.presentation.rawgdisplay.gamesearch.mapper;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameToViewModelMapper {

    private GameItemViewModel map(Game game) {
        GameItemViewModel gameItemViewModel = new GameItemViewModel();

        gameItemViewModel.setGameId(game.getGameId());
        gameItemViewModel.setGameRating(game.getRating());
        gameItemViewModel.setGameTitle(game.getTitle());
        gameItemViewModel.setIconUrl(game.getImageLinks());

        return gameItemViewModel;
    }

    public List<GameItemViewModel> map(List<Game> games) {
        List<GameItemViewModel> gameItemViewModels = new ArrayList<>();

        for(Game game : games) {
            gameItemViewModels.add(map(game));
        }
        return gameItemViewModels;
    }

}
