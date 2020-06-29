package android.eservices.rawg.presentation.rawgdisplay.youtubevideos.mapper;

import android.eservices.rawg.data.api.model.YoutubeVideos;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeViewModel;

public class YoutubeVideoToViewModelMapper {

    public GameYoutubeViewModel toGameYoutubeViewModel(YoutubeVideos video) {
        GameYoutubeViewModel gameYoutubeViewModel = new GameYoutubeViewModel();

        gameYoutubeViewModel.setVideoId(video.getId());
        gameYoutubeViewModel.setIconUrl(video.getImageLink().getImageUrl().getUrl());
        gameYoutubeViewModel.setVideoChannel(video.getChannelTitle());
        gameYoutubeViewModel.setVideoCount(video.getViewCount());
        gameYoutubeViewModel.setVideoTitle(video.getName());

        return gameYoutubeViewModel;
    }
}
