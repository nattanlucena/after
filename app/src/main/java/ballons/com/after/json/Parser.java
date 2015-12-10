package ballons.com.after.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ballons.com.after.model.FeedItem;

/**
 * Created by nattanlucena on 12/11/15.
 */
public class Parser {

    public static ArrayList<FeedItem> parseJsonFeed(JSONObject response) {
        ArrayList<FeedItem> mFeedItems = new ArrayList<FeedItem>();
        Log.d("response", String.valueOf(response));

        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImage(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                mFeedItems.add(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mFeedItems;
    }
}
