package android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter;

/**
 * The version of a model of a Youtube video used to be displayed to the user
 */
public class GameYoutubeViewModel {

    private String videoId;
    private String iconUrl;
    private String videoTitle;
    private String videoCount;
    private String videoChannel;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }

    public String getVideoChannel() {
        return videoChannel;
    }

    public void setVideoChannel(String videoChannel) {
        this.videoChannel = videoChannel;
    }
}
