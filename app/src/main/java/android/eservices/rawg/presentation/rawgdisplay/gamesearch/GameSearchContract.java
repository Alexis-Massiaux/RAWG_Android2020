package android.eservices.rawg.presentation.rawgdisplay.gamesearch;

import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameItemViewModel;

import java.util.List;

/**
 * Some actions related to the fragment for the search of games
 */
public interface GameSearchContract {

    interface Presenter {

        /**
         * Research of games based on a part of the game title
         * @param keywords - part of the game title
         */
        void searchGames(String keywords);

        void attachView(View view);

        void cancelSubscription();

        /**
         * Addition of a game to the collection
         * @param gameId - the game id of the game to be saved
         */
        void addGameToCollection(String gameId);

        void detachView();
    }

    interface View {
        /**
         * Displays the list of games retrieves according to the title given
         * @param games - list of games returns by the API
         */
        void displayGames(List<GameItemViewModel> games);

        /**
         * Reaction of an addition of a game to the collection
         * @param gameId - the game id of the game that has been saved
         */
        void onGameAddedToCollection(String gameId);
    }
}
