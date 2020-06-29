package android.eservices.rawg.presentation.rawgdisplay.youtubevideos;

import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeViewModel;

import java.util.List;

/**
 * Some actions related to the Youtube videos fragment
 */
public interface GameYoutubeContract {

    interface Presenter {

        void attachView(GameYoutubeContract.View view);

        void cancelSubscription();

        void detachView();

        /**
         * Retrieves videos associated with the collection
         */
        void getVideosFromCollection();
    }

    interface View {
        /**
         * Displays videos
         * @param videos - the videos to display
         */
        void displayVideos(List<GameYoutubeViewModel> videos);
    }
}
