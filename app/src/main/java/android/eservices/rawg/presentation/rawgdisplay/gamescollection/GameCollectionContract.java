package android.eservices.rawg.presentation.rawgdisplay.gamescollection;

import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionViewModel;

import java.util.List;

public interface GameCollectionContract {

    interface View {
        void displayCollection(List<GameCollectionViewModel> gameCollectionViewModels);

        void onGameRemoved();
    }

    interface Presenter {
        void attachView(View view);

        void getCollection();

        void onRemoveGame(String id);

        void detachView();
    }
}
