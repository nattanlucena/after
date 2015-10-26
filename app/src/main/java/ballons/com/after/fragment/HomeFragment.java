package ballons.com.after.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ballons.com.after.R;
import ballons.com.after.adapter.FeedListAdapter;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;


public class HomeFragment extends Fragment {

    private static String TAG = HomeFragment.class.getSimpleName();
    private ListView mListView;
    private FeedListAdapter mFeedListAdapter;
    private List<FeedItem> mFeedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";


    public HomeFragment(){}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (ListView) rootView.findViewById(R.id.feed_list);

        mFeedItems = new ArrayList<FeedItem>();

        mFeedListAdapter = new FeedListAdapter(getActivity(), mFeedItems);
        mListView.setAdapter(mFeedListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new MotelProfileFragment();
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
                }
            }
        });

        /*
        * carregar dados no scroll
        * https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews
        * https://gist.github.com/anonymous/b4d3597e913327afadd5
         */

        Cache mCache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = mCache.get(URL_FEED);

        if( entry != null){
            try{
                String data = new String(entry.data, "UTF-8");

                try{
                    JSONObject feedData = new JSONObject(data);
                    if(feedData.length() == 0 )
                        Log.d("length: ", "json vazio");

                    parseJsonFeed(feedData);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{

            JsonObjectRequest jsoReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            AppController.getInstance().addToRequestQueue(jsoReq);
        }

        return rootView;
    }


    private void parseJsonFeed(JSONObject response) {
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

            // notify data changes to list adapater
            mFeedListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline(){
        try
        {
            HttpURLConnection urlc = (HttpURLConnection) (new URL(URL_FEED).openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(3000); //choose your own timeframe
            urlc.setReadTimeout(4000); //choose your own timeframe
            urlc.connect();

            return (urlc.getResponseCode() == 200);
        } catch (IOException e)
        {
            return (false);  //connectivity exists, but no internet.
        }
    }

}
