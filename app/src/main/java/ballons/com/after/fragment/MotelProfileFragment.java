package ballons.com.after.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import ballons.com.after.FeedImageView;
import ballons.com.after.R;
import ballons.com.after.Utils.GradientOverImageDrawable;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;

/*
* carregar dados no scroll
* https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews
* https://gist.github.com/anonymous/b4d3597e913327afadd5
 */

public class MotelProfileFragment extends Fragment {

    private int mPosition;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private List<FeedItem> mFeedItems;


    public MotelProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_motel_profile, container, false);

        String mId = getArguments().getString("id");
        String mName = getArguments().getString("name");

        getActivity().setTitle(mName);

        TextView mProfileName = (TextView) mView.findViewById(R.id.profile_name);
        TextView mProfileLocalization = (TextView) mView.findViewById(R.id.profile_localization);
        ImageView mHeaderImage = (ImageView) mView.findViewById(R.id.header_profile_image);

        mProfileName.setTextColor(Color.WHITE);
        mProfileLocalization.setTextColor(Color.WHITE);


        //http://www.ixtendo.com/how-to-add-a-caption-text-over-an-image-in-android/
        //TODO: integrar com a imagem baixada com o Volley
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.profile_motel_pic);
        int gradientStartColor = Color.argb(0, 0, 0, 0);
        int gradientEndColor = Color.argb(255, 0, 0, 0);
        GradientOverImageDrawable gradientOverImageDrawable =
                new GradientOverImageDrawable(getResources(), image);
        gradientOverImageDrawable.setGradientColors(gradientStartColor, gradientEndColor);
        mHeaderImage.setImageDrawable(gradientOverImageDrawable);

        /*
        Cache mCache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = mCache.get(URL_FEED);

        FeedImageView feedImageView = (FeedImageView) mView.findViewById(R.id.header_profile_image);

        if( item.getImage() != null){
            feedImageView.setImageUrl(item.getImage(), mImageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                @Override
                public void onError() {

                }

                @Override
                public void onSuccess() {

                }
            });
        }

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

            JsonObjectRequest jsoReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Response.Listener<JSONObject>() {
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
        */
        return mView;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mId) {
        this.mPosition = mId;
    }

    private void getDataFromServer(){
        //TODO: Utilizar o Volley para realizar o GET com os dados do motel
    }

    /*
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
    */
}
