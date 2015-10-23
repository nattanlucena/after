package ballons.com.after.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

import ballons.com.after.FeedImageView;
import ballons.com.after.R;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;

/**
 * Created by Nattan on 23/10/2015.
 */
public class FeedListAdapter extends BaseAdapter {

    private Activity mAcitivity;
    private LayoutInflater mInflater;
    private List<FeedItem> mFeedItems;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Activity activity, List<FeedItem> items){
        mAcitivity = activity;
        mFeedItems= items;
    }

    @Override
    public int getCount() {
        return mFeedItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mFeedItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(mInflater == null)
            mInflater = (LayoutInflater) mAcitivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.feed_item, null);

        if(mImageLoader == null)
            mImageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView timestamp = (TextView) view.findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) view.findViewById(R.id.txtStatusMsg);
        TextView url = (TextView) view.findViewById(R.id.txtUrl);

        NetworkImageView profilePic = (NetworkImageView) view.findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) view.findViewById(R.id.feedImage1);

        FeedItem item = mFeedItems.get(position);

        name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        if(!TextUtils.isEmpty(item.getStatus())){
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        }else{
            statusMsg.setVisibility(View.GONE);
        }

        if( item.getUrl() != null){
            url.setText(Html.fromHtml(
                    "<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a>"
            ));
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        }else{
            statusMsg.setVisibility(View.GONE);
        }

        profilePic.setImageUrl(item.getProfilePic(), mImageLoader);

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
        }else{
            feedImageView.setVisibility(View.GONE);
        }

        return view;
    }
}
