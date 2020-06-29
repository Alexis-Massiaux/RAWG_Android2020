package android.eservices.rawg.presentation.rawgdisplay.youtubevideos.adapter;

import android.eservices.rawg.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fills the layout fragment_game_youtube with data and actions
 */
public class GameYoutubeAdapter extends RecyclerView.Adapter<GameYoutubeAdapter.GameYoutubeViewHolder> {

    public static class GameYoutubeViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView videoImageView;
        private TextView titleTextView;
        private TextView channelTextView;
        private TextView countTextView;

        private GameYoutubeViewModel gameYoutubeViewModel;
        private GameYoutubeActionInterface gameYoutubeActionInterface;


        public GameYoutubeViewHolder(@NonNull View view, final GameYoutubeActionInterface gameYoutubeActionInterface) {
            super(view);
            this.view = view;
            this.gameYoutubeActionInterface = gameYoutubeActionInterface;

            videoImageView = view.findViewById(R.id.youtube_imageview);
            titleTextView = view.findViewById(R.id.youtube_title);
            channelTextView = view.findViewById(R.id.youtube_channel);
            countTextView = view.findViewById(R.id.youtube_count);
            setupListeners();
        }

        /**
         * Sets the action of opening the video on Youtube after a click on the icon
         */
        private void setupListeners() {
            videoImageView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    gameYoutubeActionInterface.openYoutubeVideo(gameYoutubeViewModel.getVideoId());
                }
            });
        }

        /**
         * Fills the fields of the layout fragment_game_youtube
         * @param gameYoutubeViewModel - the data
         */
        void bind(GameYoutubeViewModel gameYoutubeViewModel) {
            this.gameYoutubeViewModel = gameYoutubeViewModel;
            titleTextView.setText(gameYoutubeViewModel.getVideoTitle());
            channelTextView.setText(gameYoutubeViewModel.getVideoChannel());
            countTextView.setText(gameYoutubeViewModel.getVideoCount());
            Glide.with(view)
                    .load(gameYoutubeViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(videoImageView);
        }
    }

    private List<GameYoutubeViewModel> gameYoutubeViewModels;
    private GameYoutubeActionInterface gameYoutubeActionInterface;

    public GameYoutubeAdapter(GameYoutubeActionInterface gameYoutubeActionInterface) {
        gameYoutubeViewModels = new ArrayList<>();
        this.gameYoutubeActionInterface = gameYoutubeActionInterface;
    }

    /**
     * Displays all the Youtube video related to the collection
     * @param gameYoutubeViewModels
     */
    public void bindViewModels(List<GameYoutubeViewModel> gameYoutubeViewModels) {
        this.gameYoutubeViewModels.clear();
        this.gameYoutubeViewModels.addAll(gameYoutubeViewModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameYoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_youtube, parent, false);
        GameYoutubeAdapter.GameYoutubeViewHolder gameYoutubeViewHolder = new GameYoutubeAdapter.GameYoutubeViewHolder(v, gameYoutubeActionInterface);
        return gameYoutubeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameYoutubeViewHolder holder, int position) {
        holder.bind(gameYoutubeViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return gameYoutubeViewModels.size();
    }
}
