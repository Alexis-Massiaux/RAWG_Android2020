package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

public class YoutubeVideos {

    @SerializedName("external_id")
    private String id;

    String name;

    @SerializedName("channel_title")
    String channelTitle;

    @SerializedName("view_count")
    String viewCount;

    @SerializedName("thumbnails")
    VideoThumbmail imageLink;

    public String getId() { return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public VideoThumbmail getImageLink() {
        return imageLink;
    }

    public void setImageLink(VideoThumbmail imageLink) {
        this.imageLink = imageLink;
    }
}
