package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * List of Youtube videos retrieved by the API
 */
public class YoutubeVideoSearchResponse {

    @SerializedName("results")
    private List<YoutubeVideos> videos;

    public List<YoutubeVideos> getVideos() {
        return videos;
    }
}
