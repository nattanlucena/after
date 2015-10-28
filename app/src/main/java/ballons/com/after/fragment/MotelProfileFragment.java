package ballons.com.after.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ballons.com.after.R;
import ballons.com.after.Utils.GradientOverImageDrawable;
import ballons.com.after.adapter.MotelRoomListAdapter;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;
import ballons.com.after.model.MotelRoomItem;

/*
* carregar dados no scroll
* https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews
* https://gist.github.com/anonymous/b4d3597e913327afadd5
 */

public class MotelProfileFragment extends Fragment {

    private int mPosition;
    private ListView mRoomsListView;
    private List<MotelRoomItem> mMotelRoomItems;
    private MotelRoomListAdapter mMotelRoomListAdapter;
    private String URL_FEED = "http://api.androidhive.info/json/movies.json";

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


        mRoomsListView = (ListView) mView.findViewById(R.id.motel_room_list);
        mMotelRoomItems = new ArrayList<MotelRoomItem>();
        mMotelRoomListAdapter = new MotelRoomListAdapter(getActivity(), mMotelRoomItems);
        mRoomsListView.setAdapter(mMotelRoomListAdapter);

        mRoomsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MotelRoomItem item = (MotelRoomItem) adapterView.getItemAtPosition(i);
                //TODO: irá para o profile do quarto selecionado
                Log.d("room", item.getName());
            }
        });

        JsonArrayRequest itemReq = new JsonArrayRequest(URL_FEED,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                MotelRoomItem item = new MotelRoomItem();
                                item.setName(obj.getString("title"));
                                item.setThumb(obj.getString("image"));
                                item.setRating(obj.getString("rating"));
                                item.setDescription(obj.getString("releaseYear"));

                                mMotelRoomItems.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        mMotelRoomListAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(itemReq);
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
}
