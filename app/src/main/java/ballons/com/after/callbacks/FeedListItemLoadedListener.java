package ballons.com.after.callbacks;

import java.util.ArrayList;

import ballons.com.after.model.FeedItem;

/**
 * Created by nattanlucena on 12/11/15.
 */
public interface FeedListItemLoadedListener {
    public void onFeedListItemLoadedListener(ArrayList<FeedItem> listItems);
}
