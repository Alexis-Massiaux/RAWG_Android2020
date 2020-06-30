package android.eservices.rawg.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Icon of a Youtube video retrived by the API
 */
public class VideoThumbmail {

    @SerializedName("medium")
    ImageUrl imageUrl;

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }
}
