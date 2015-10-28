package ballons.com.after.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import ballons.com.after.R;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;
import ballons.com.after.model.MotelRoomItem;

/**
 * Created by nattan on 10/27/15.
 */
public class MotelRoomListAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<MotelRoomItem> mItems;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    public MotelRoomListAdapter(Activity activity, List<MotelRoomItem> items){
        mActivity = activity;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View mView = view;

        if( mInflater == null)
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(mView == null)
            mView = mInflater.inflate(R.layout.motel_room_item, null);

        if(mImageLoader == null)
            mImageLoader = AppController.getInstance().getImageLoader();


        TextView mName = (TextView) mView.findViewById(R.id.motel_room_list_title);
        TextView mRating = (TextView) mView.findViewById(R.id.motel_room_list_rating);
        TextView mDescription = (TextView) mView.findViewById(R.id.motel_room_list_description);
        TextView mPriceRange = (TextView) mView.findViewById(R.id.motel_room_list_price_range);

        NetworkImageView mThumbnail = (NetworkImageView) mView.findViewById(R.id.motel_room_list_thumbnail);

        MotelRoomItem item = mItems.get(position);

        mName.setText(item.getName());
        mRating.setText(item.getRating());
        mDescription.setText(item.getDescription());
        mPriceRange.setText(item.getPriceRange());
        mThumbnail.setImageUrl(item.getThumb(), mImageLoader);


        return mView;
    }
}
