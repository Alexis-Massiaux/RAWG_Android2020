package android.eservices.rawg.presentation.rawgdisplay.gamescollection;

import android.eservices.rawg.data.di.FakeDependencyInjection;
import android.eservices.rawg.data.repository.collection.GameCollectionDataRepository;
import android.eservices.rawg.db.entity.GameEntity;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.mapper.GameEntityToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Presenter part of the save Game collection
 */
public class GameCollectionPresenter implements GameCollectionContract.Presenter {

    private GameCollectionContract.View view;
    private GameCollectionDataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private GameEntityToViewModelMapper mapper;

    public GameCollectionPresenter() {
        this.dataRepository = FakeDependencyInjection.getGameCollectionDataRepository();
        this.compositeDisposable = new CompositeDisposable();
        this.mapper = new GameEntityToViewModelMapper();
    }

    @Override
    public void getCollection() {
        compositeDisposable.add(dataRepository.getGamesInDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<GameEntity>>() {
                    @Override
                    public void onNext(List<GameEntity> gameEntities) {
                        view.displayCollection(mapper.map(gameEntities));
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {}

                }));

    }

    @Override
    public void attachView(GameCollectionContract.View view) {
        this.view = view;
    }

    @Override
    public void onRemoveGame(String id) {
        compositeDisposable.add(dataRepository.removeGameFromCollection(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        System.out.println("Game removed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.dispose();
    }
}
