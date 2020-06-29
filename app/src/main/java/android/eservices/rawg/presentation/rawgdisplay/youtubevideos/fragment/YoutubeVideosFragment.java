package android.eservices.rawg.presentation.rawgdisplay.youtubevideos.fragment;

import android.content.Intent;
import android.eservices.rawg.R;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.GameYoutubeContract;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.GameYoutubePresenter;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeActionInterface;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeAdapter;
import android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter.GameYoutubeViewModel;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * The fragment that displays the Youtube videos related to the collection
 */
public class YoutubeVideosFragment extends Fragment implements GameYoutubeActionInterface, GameYoutubeContract.View {

    public static final String TAB_NAME = "YoutubeVideos";
    private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    private View rootView;
    private RecyclerView recyclerView;

    private GameYoutubeAdapter gameYoutubeAdapter;
    private GameYoutubeContract.Presenter gameYoutubePresenter;

    public YoutubeVideosFragment() {
        //Required empty public constructor
    }

    public YoutubeVideosFragment newInstance() {
        return new YoutubeVideosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_game_youtube, container, false);
        return rootView;
    }

    /**
     * Instanciation of the RecyclerView
     */
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.game_youtube_recyclerview);
        gameYoutubeAdapter = new GameYoutubeAdapter(this);
        recyclerView.setAdapter(gameYoutubeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        gameYoutubePresenter = new GameYoutubePresenter();
        gameYoutubePresenter.attachView(this);
        gameYoutubePresenter.getVideosFromCollection();
    }

    @Override
    public void displayVideos(List<GameYoutubeViewModel> videos) {
        gameYoutubeAdapter.bindViewModels(videos);
    }

    @Override
    public void openYoutubeVideo(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(YOUTUBE_URL + videoId));
        startActivity(intent);
    }
}
