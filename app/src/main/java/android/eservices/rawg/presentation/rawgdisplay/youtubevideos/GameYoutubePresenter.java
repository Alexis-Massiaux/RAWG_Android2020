package android.eservices.rawg.presentation.rawgdisplay.youtubevideos;

import android.eservices.rawg.data.api.model.YoutubeVideoSearchResponse;
import android.eservices.rawg.data.di.FakeDependencyInjection;
import android.eservices.rawg.data.repository.collection.GameCollectionDataRepository;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.mapper.YoutubeVideoToViewModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter part of Youtube videos in charge of retrieving the videos according to the collection
 */
public class GameYoutubePresenter implements GameYoutubeContract.Presenter{

    private CompositeDisposable cd;
    private GameYoutubeContract.View view;
    private YoutubeVideoToViewModelMapper mapper;
    private GameCollectionDataRepository gameCollectionDataRepository;

    public GameYoutubePresenter() {
        cd = new CompositeDisposable();
        mapper = new YoutubeVideoToViewModelMapper();
        gameCollectionDataRepository = FakeDependencyInjection.getGameCollectionDataRepository();
    }

    @Override
    public void getVideosFromCollection() {
        cd.add(gameCollectionDataRepository.getYoutubeVideosFromCollection()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<List<YoutubeVideoSearchResponse>>() {

                @Override
                public void onSuccess(List<YoutubeVideoSearchResponse> youtubeResponses) {
                    view.displayVideos(youtubeResponses
                            .stream()
                            .flatMap(youtubeResponse -> youtubeResponse.getVideos()
                                    .stream()
                                    .map(youtubeVideo -> mapper.toGameYoutubeViewModel(youtubeVideo)))
                            .collect(Collectors.toList()));
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }
            }));
    }

    @Override
    public void attachView(GameYoutubeContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        cd.clear();
    }

    @Override
    public void detachView() {
        view = null;
        cd.dispose();
    }
}
