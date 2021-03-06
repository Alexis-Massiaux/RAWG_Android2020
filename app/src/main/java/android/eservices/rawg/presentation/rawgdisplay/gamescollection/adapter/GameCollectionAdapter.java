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

/**
 *  Fills the layout fragment_game_collection with data and actions
 */
public class GameCollectionAdapter extends RecyclerView.Adapter<GameCollectionAdapter.GameViewHolder>{

    public static class GameViewHolder extends RecyclerView.ViewHolder {

        private ImageView backgroundImageView;
        private TextView titleTextView;
        private TextView ratingTextView;
        private ImageButton gameDeleteButton;
        private View v;

        private GameCollectionActionInterface gameCollectionActionInterface;
        private GameCollectionViewModel gameCollectionViewModel;

        public GameViewHolder(View v, final GameCollectionActionInterface gameActionInterface) {
            super(v);
            this.v = v;
            backgroundImageView = v.findViewById(R.id.collection_imageview);
            titleTextView = v.findViewById(R.id.collection_title);
            ratingTextView = v.findViewById(R.id.collection_rating);
            gameDeleteButton = v.findViewById(R.id.collection_delete_button);
            this.gameCollectionActionInterface = gameActionInterface;
            setupListeners();
        }

        /**
         * Sets the action of deleting a game after a click on the remove button
         */
        private void setupListeners() {
            gameDeleteButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    gameCollectionActionInterface.onRemoveGame(gameCollectionViewModel.getGameId());
                }
            });
        }

        /**
         * Fills the fields of the layout fragment_game_collection
         * @param gameItemViewModel - the data
         */
        void bind(GameCollectionViewModel gameItemViewModel) {
            this.gameCollectionViewModel = gameItemViewModel;
            titleTextView.setText(gameItemViewModel.getGameTitle());
            ratingTextView.setText(gameItemViewModel.getGameRating());
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

    /**
     * Displays all the Game send back by the database
     * @param gameCollectionViewModelList
     */
    public void bindViewModels(List<GameCollectionViewModel> gameCollectionViewModelList) {
        this.gameCollectionViewModelList.clear();
        this.gameCollectionViewModelList.addAll(gameCollectionViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameCollectionAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_collection, parent, false);
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
