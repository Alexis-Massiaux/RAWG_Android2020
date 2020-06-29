package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

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
