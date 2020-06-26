package android.eservices.rawg.presentation.rawgdisplay.gamesearch.adapter;

import android.eservices.rawg.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    public static class GameViewHolder extends RecyclerView.ViewHolder {

        private ImageView backgroundImageView;
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageButton gameAddButton;
        private View v;

        private GameActionInterface gameActionInterface;
        private GameItemViewModel gameItemViewModel;

        public GameViewHolder(View v, final GameActionInterface gameActionInterface) {
            super(v);
            this.v = v;
            backgroundImageView = v.findViewById(R.id.game_imageview);
            titleTextView = v.findViewById(R.id.game_title_textview);
            ratingTextView = v.findViewById(R.id.game_rating_textview);
            gameAddButton = v.findViewById(R.id.game_add_button);
            this.gameActionInterface = gameActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            gameAddButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    gameActionInterface.addToCollection(gameItemViewModel.getGameId());
                }
            });
        }

        void bind(GameItemViewModel gameItemViewModel) {
            this.gameItemViewModel = gameItemViewModel;
            titleTextView.setText(gameItemViewModel.getGameTitle());
            ratingTextView.setText(gameItemViewModel.getGameRating());
            //favoriteSwitch.setChecked(bookItemViewModel.isFavorite());
            Glide.with(v)
                    .load(gameItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(backgroundImageView);

        }
    }

    private List<GameItemViewModel> gameItemViewModelList;
    private GameActionInterface gameActionInterface;

    public GameAdapter(GameActionInterface gameActionInterface) {
        gameItemViewModelList = new ArrayList<>();
        this.gameActionInterface = gameActionInterface;
    }

    public void bindViewModels(List<GameItemViewModel> gameItemViewModelList) {
        this.gameItemViewModelList.clear();
        this.gameItemViewModelList.addAll(gameItemViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        GameViewHolder gameViewHolder = new GameViewHolder(v, gameActionInterface);
        return gameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(gameItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return gameItemViewModelList.size();
    }
}
