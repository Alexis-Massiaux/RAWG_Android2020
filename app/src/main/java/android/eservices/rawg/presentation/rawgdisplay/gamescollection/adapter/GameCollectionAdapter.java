package android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter;

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

public class GameCollectionAdapter extends RecyclerView.Adapter<GameCollectionAdapter.GameViewHolder>{

    public static class GameViewHolder extends RecyclerView.ViewHolder {

        private ImageView backgroundImageView;
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageButton gameAddButton;
        private View v;

        private GameCollectionActionInterface gameCollectionActionInterface;
        private GameCollectionViewModel gameCollectionViewModel;

        public GameViewHolder(View v, final GameCollectionActionInterface gameActionInterface) {
            super(v);
            this.v = v;
            backgroundImageView = v.findViewById(R.id.game_imageview);
            titleTextView = v.findViewById(R.id.game_title_textview);
            ratingTextView = v.findViewById(R.id.game_rating_textview);
            gameAddButton = v.findViewById(R.id.game_add_button);
            this.gameCollectionActionInterface = gameActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            gameAddButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    gameCollectionActionInterface.onRemoveGame(gameCollectionViewModel.getGameId());
                }
            });
        }

        void bind(GameCollectionViewModel gameItemViewModel) {
            this.gameCollectionViewModel = gameItemViewModel;
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

    private List<GameCollectionViewModel> gameCollectionViewModelList;
    private GameCollectionActionInterface gameActionInterface;

    public GameCollectionAdapter(GameCollectionActionInterface gameActionInterface) {
        gameCollectionViewModelList = new ArrayList<>();
        this.gameActionInterface = gameActionInterface;
    }

    public void bindViewModels(List<GameCollectionViewModel> gameCollectionViewModelList) {
        this.gameCollectionViewModelList.clear();
        this.gameCollectionViewModelList.addAll(gameCollectionViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameCollectionAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        GameCollectionAdapter.GameViewHolder gameViewHolder = new GameCollectionAdapter.GameViewHolder(v, gameActionInterface);
        return gameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameCollectionAdapter.GameViewHolder holder, int position) {
        holder.bind(gameCollectionViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return gameCollectionViewModelList.size();
    }

}
