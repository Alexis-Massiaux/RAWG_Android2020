package android.eservices.rawg.presentation.rawgdisplay.gamescollection;

import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionViewModel;

import java.util.List;

/**
 * Some actions related to the fragment of the save Game collection
 */
public interface GameCollectionContract {

    interface View {
        /**
         * Displays the list of save games in the collection
         * @param gameCollectionViewModels - the list of games
         */
        void displayCollection(List<GameCollectionViewModel> gameCollectionViewModels);
    }

    interface Presenter {
        void attachView(View view);

        /**
         * Recover the local backup collection
         */
        void getCollection();

        /**
         * Deleting a game saved in the collection and according to a game id
         * @param id - a game id
         */
        void onRemoveGame(String id);

        void detachView();
    }
}
