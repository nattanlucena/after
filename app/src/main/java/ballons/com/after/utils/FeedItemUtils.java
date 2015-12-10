package ballons.com.after.utils;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ballons.com.after.json.EndPoints;
import ballons.com.after.json.FeedItemRequestor;
import ballons.com.after.json.Parser;
import ballons.com.after.model.FeedItem;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class FeedItemUtils {


    public static ArrayList<FeedItem> loadFeedItemList(RequestQueue requestQueue) {
        ArrayList<FeedItem> items;
        /*
        items = new ArrayList<FeedItem>();
        JSONObject jsonObject = FeedItemRequestor.sendRequestFeedItemList(requestQueue, EndPoints.getRequestUrl(30));
        items = Parser.parseJsonFeed(jsonObject);
        */
        items = new ArrayList<FeedItem>();
        JSONObject jsonObject = FeedItemRequestor.sendRequestFeedItemList(requestQueue, UrlEndpoints.URL_FEED_LIST);
        items = Parser.parseJsonFeed(jsonObject);
        return items;
    }
}
