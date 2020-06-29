package android.eservices.rawg.presentation.rawgdisplay.gamescollection.adapter;

/**
 * Actions of the Game collection part
 */
public interface GameCollectionActionInterface {

    /**
     * Deletion of a game according to a game ig
     * @param id - the game id
     */
    void onRemoveGame(String id);
}
