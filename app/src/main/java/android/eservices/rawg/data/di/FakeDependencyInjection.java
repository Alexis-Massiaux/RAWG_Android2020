package android.eservices.rawg.data.di;

import android.content.Context;
import android.eservices.rawg.data.api.GameService;
import android.eservices.rawg.data.repository.collection.GameCollectionDataRepository;
import android.eservices.rawg.data.repository.collection.GameCollectionLocalDataSource;
import android.eservices.rawg.data.repository.collection.GameCollectionRemoteDataSource;
import android.eservices.rawg.data.repository.search.GameSearchDataRepository;
import android.eservices.rawg.data.repository.search.GameSearchRemoteDataSource;
import android.eservices.rawg.db.GameDatabase;

import androidx.room.Room;

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
    private static GameSearchDataRepository gameSearchDataRepository;
    private static GameSearchRemoteDataSource gameSearchRemoteDataSource;
    private static GameDatabase gameDatabase;
    private static GameCollectionDataRepository gameCollectionDataRepository;
    private static GameCollectionLocalDataSource gameCollectionLocalDataSource;
    private static GameCollectionRemoteDataSource gameCollectionRemoteDataSource;


    /**
     * DataRepository
     */
    public static GameSearchDataRepository getGameSearchDataRepository() {
        if(gameSearchDataRepository == null) {
            gameSearchDataRepository = new GameSearchDataRepository(getGameSearchRemoteDataSource());
        }
        return gameSearchDataRepository;
    }

    public static GameCollectionDataRepository getGameCollectionDataRepository() {
        if(gameCollectionDataRepository == null) {
            gameCollectionDataRepository = new GameCollectionDataRepository(getGameCollectionLocalDataSource(), getGameCollectionRemoteDataSource());
        }
        return gameCollectionDataRepository;
    }

    /**
     * RemoteDataSource
     */
    private static GameSearchRemoteDataSource getGameSearchRemoteDataSource() {
        if(gameSearchRemoteDataSource == null) {
            gameSearchRemoteDataSource = new GameSearchRemoteDataSource(getGameService());
        }
        return gameSearchRemoteDataSource;
    }

    private static GameCollectionRemoteDataSource getGameCollectionRemoteDataSource() {
        if(gameCollectionRemoteDataSource == null) {
            gameCollectionRemoteDataSource = new GameCollectionRemoteDataSource(getGameService());
        }
        return gameCollectionRemoteDataSource;
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

    public static void setContext(Context context) {
        applicationContext = context;
    }

    /**
     * LocalDataSource
     */
    public static GameCollectionLocalDataSource getGameCollectionLocalDataSource() {
        if(gameCollectionLocalDataSource == null) {
            gameCollectionLocalDataSource = new GameCollectionLocalDataSource(getGameDatabase());
        }
        return gameCollectionLocalDataSource;
    }

    /**
     * Database
     */
    public static GameDatabase getGameDatabase() {
        if(gameDatabase == null) {
            gameDatabase = Room.databaseBuilder(applicationContext.getApplicationContext(), GameDatabase.class,"game-database").build();
        }
        return gameDatabase;
    }
}
