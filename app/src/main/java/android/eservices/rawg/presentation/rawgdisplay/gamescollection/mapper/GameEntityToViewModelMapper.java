package android.eservices.rawg.presentation.rawgdisplay.gamescollection.mapper;

import android.eservices.rawg.db.entity.GameEntity;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameEntityToViewModelMapper {

    public GameCollectionViewModel map(GameEntity game) {
        GameCollectionViewModel gameCollectionViewModel = new GameCollectionViewModel();

        gameCollectionViewModel.setGameId(game.getId());
        gameCollectionViewModel.setGameTitle(game.getTitle());
        gameCollectionViewModel.setGameRating(game.getRating());
        gameCollectionViewModel.setIconUrl(game.getImageLink());

        return gameCollectionViewModel;
    }

    public List<GameCollectionViewModel> map(List<GameEntity> games) {
        List<GameCollectionViewModel> gameCollectionViewModels = new ArrayList<>();

        for(GameEntity game : games) {
            gameCollectionViewModels.add(map(game));
        }

        return gameCollectionViewModels;
    }
}
