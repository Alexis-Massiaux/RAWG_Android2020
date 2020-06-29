package android.eservices.rawg.presentation.rawgdisplay.youtubevideos;

import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeViewModel;

import java.util.List;

public interface GameYoutubeContract {

    interface Presenter {

        void attachView(GameYoutubeContract.View view);

        void cancelSubscription();

        void detachView();

        void getVideosFromCollection();
    }

    interface View {
        void displayVideos(List<GameYoutubeViewModel> videos);
    }
}
