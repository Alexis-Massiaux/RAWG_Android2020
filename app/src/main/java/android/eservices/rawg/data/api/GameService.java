package android.eservices.rawg.data.api;

import android.eservices.rawg.data.api.model.Game;
import android.eservices.rawg.data.api.model.GameSearchResponse;
import android.eservices.rawg.data.api.model.YoutubeVideoSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {

    @GET("games")
    Single<GameSearchResponse> getGamesSearchResponses(@Query("search") String keywords);

    @GET("games/{id}")
    Single<Game> getGame(@Path("id") String id);

    @GET("games/{id}/youtube")
    Single<YoutubeVideoSearchResponse> getYoutubeVideos(@Path("id") String id);
}
