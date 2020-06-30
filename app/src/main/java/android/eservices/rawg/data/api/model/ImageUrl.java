package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * URL of an image retrived by the API
 */
public class ImageUrl {

    @SerializedName("url")
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
