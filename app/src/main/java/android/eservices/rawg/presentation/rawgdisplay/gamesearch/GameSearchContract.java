package android.eservices.rawg.presentation.rawgdisplay.gamesearch;

import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameItemViewModel;

import java.util.List;

public interface GameSearchContract {

    interface Presenter {

        void searchGames(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addGameToCollection(String gameId);

        void detachView();
    }

    interface View {
        void displayGames(List<GameItemViewModel> games);

        void onGameAddedToCollection(String gameId);
    }
}
