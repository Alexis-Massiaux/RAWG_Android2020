package android.eservices.rawg.data.di;

import android.content.Context;
import android.eservices.rawg.data.api.GameService;
import android.eservices.rawg.data.repository.search.GameSearchRemoteDataSource;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    private static Retrofit retrofit;
    private static Gson gson;
    private static Context applicationContext;

    private static GameService gameService;
    private static GameSearchRemoteDataSource gameSearchRemoteDataSource;

    /**
     * RemoteDataSource
     */
    private static GameSearchRemoteDataSource getGamesSearchRemoteDataSource() {
        if(gameSearchRemoteDataSource == null) {
            gameSearchRemoteDataSource = new GameSearchRemoteDataSource(getGameService());
        }
        return gameSearchRemoteDataSource;
    }

    /**
     * Service
     */
    private static GameService getGameService() {
        if(gameService == null) {
            gameService = getRetrofit().create(GameService.class);
        }
        return gameService;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.rawg.io/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    private static void setContext(Context context) {
        applicationContext = context;
    }
}
