package android.eservices.rawg.presentation.rawgdisplay.gamesearch;

import android.eservices.rawg.data.api.model.GameSearchResponse;
import android.eservices.rawg.data.di.FakeDependencyInjection;
import android.eservices.rawg.data.repository.search.GameSearchDataRepository;
import android.eservices.rawg.data.repository.search.GameSearchRemoteDataSource;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.mapper.GameToViewModelMapper;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GameSearchPresenter implements GameSearchContract.Presenter {

    private CompositeDisposable cd;
    private GameSearchContract.View view;
    private GameSearchDataRepository gameSearchDataRepository;
    private GameToViewModelMapper gameToViewModelMapper;

    public GameSearchPresenter() {
        this.cd = new CompositeDisposable();
        this.gameSearchDataRepository = FakeDependencyInjection.getGameSearchDataRepository();
        this.gameToViewModelMapper = new GameToViewModelMapper();
    }

    @Override
    public void searchGame(String keywords) {
        this.cd.add(this.gameSearchDataRepository.getGames(keywords).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<GameSearchResponse>() {
                    @Override
                    public void onSuccess(GameSearchResponse game) {
                        view.displayGames(gameToViewModelMapper.map(game.getGames()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                }));
    }

    @Override
    public void attachView(GameSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        cd.clear();
    }

    @Override
    public void addGameToCollection(String gameId) {

    }

    @Override
    public void detachView() {
        this.view = null;
        cd.dispose();
    }
}
