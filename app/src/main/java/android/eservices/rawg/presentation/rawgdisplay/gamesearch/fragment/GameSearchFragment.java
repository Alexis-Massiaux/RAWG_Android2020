package android.eservices.rawg.presentation.rawgdisplay.gamesearch.fragment;

import android.eservices.rawg.R;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.GameSearchContract;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.GameSearchPresenter;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameActionInterface;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameAdapter;
import android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter.GameItemViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameSearchFragment extends Fragment implements GameActionInterface, GameSearchContract.View {

    public static final String TAB_NAME = "GameSearch";
    private View rootView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GameAdapter gameAdapter;
    private GameSearchPresenter gameSearchPresenter;

    public GameSearchFragment() {
        //Required empty public constructor
    }

    public static GameSearchFragment newInstance() {
        return new GameSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_game_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();

        progressBar = rootView.findViewById(R.id.progress_bar);

        this.gameSearchPresenter = new GameSearchPresenter();
        this.gameSearchPresenter.attachView(this);

    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    gameSearchPresenter.cancelSubscription();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameSearchPresenter.searchGames(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void displayGames(List<GameItemViewModel> games) {
        progressBar.setVisibility(View.GONE);
        gameAdapter.bindViewModels(games);
    }

    @Override
    public void onGameAddedToCollection(String gameId) {
        String message = "Ajout du jeux : "+gameId+" à la collection";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }

    @Override
    public void addToCollection(String gameId) {
        gameSearchPresenter.addGameToCollection(gameId);
    }
}
