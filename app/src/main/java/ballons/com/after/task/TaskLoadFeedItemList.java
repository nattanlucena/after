package ballons.com.after.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import ballons.com.after.app.AppController;
import ballons.com.after.callbacks.FeedListItemLoadedListener;
import ballons.com.after.model.FeedItem;
import ballons.com.after.utils.FeedItemUtils;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class TaskLoadFeedItemList extends AsyncTask<Void, Void, ArrayList<FeedItem>> {

    private FeedListItemLoadedListener mListener;
    private RequestQueue mRequestQueue;
    private AppController instance;

    public TaskLoadFeedItemList(FeedListItemLoadedListener listener) {
        mListener = listener;
        instance = AppController.getInstance();
        mRequestQueue = instance.getRequestQueue();

    }

    @Override
    protected ArrayList<FeedItem> doInBackground(Void... voids) {
        ArrayList<FeedItem> items = FeedItemUtils.loadFeedItemList(mRequestQueue);

        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<FeedItem> items) {
        if (mListener != null)
            mListener.onFeedListItemLoadedListener(items);
    }
}
