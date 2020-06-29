package android.eservices.rawg.presentation.rawgdisplay.gamescollection.fragment;

import android.eservices.rawg.R;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.GameCollectionContract;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.GameCollectionPresenter;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionActionInterface;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionAdapter;
import android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter.GameCollectionViewModel;
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
 * The fragment of the collection in charge of displaying the games saved in local database
 */
public class GamesCollectionFragment extends Fragment implements GameCollectionActionInterface, GameCollectionContract.View{

    public static final String TAB_NAME = "GamesCollection";

    private View rootView;
    private RecyclerView recyclerView;

    private GameCollectionAdapter gameCollectionAdapter;
    private GameCollectionContract.Presenter gameCollectionPresenter;

    public GamesCollectionFragment(){
        //Required empty public constructor
    }

    public GamesCollectionFragment newInstance() {
        return new GamesCollectionFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_game_collection, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        gameCollectionPresenter = new GameCollectionPresenter();
        gameCollectionPresenter.attachView(this);
        gameCollectionPresenter.getCollection();
    }


    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.collection_recycler_view);
        gameCollectionAdapter = new GameCollectionAdapter(this);
        recyclerView.setAdapter(gameCollectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayCollection(List<GameCollectionViewModel> gameCollectionViewModels) {
        gameCollectionAdapter.bindViewModels(gameCollectionViewModels);
    }

    @Override
    public void onRemoveGame(String id) {

    }

    @Override
    public void onGameRemoved() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gameCollectionPresenter.detachView();
    }
}
