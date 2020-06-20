package android.eservices.rawg.data.api;

import android.eservices.rawg.data.api.model.GameSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameService {

    @GET("games")
    Single<GameSearchResponse> getGamesSearchResponses(@Query("search") String keywords);
}
