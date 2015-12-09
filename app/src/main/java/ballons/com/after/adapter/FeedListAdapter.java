package ballons.com.after.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import ballons.com.after.FeedImageView;
import ballons.com.after.R;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;
import ballons.com.after.model.FeedItemViewHolder;

/**
 * Created by Nattan on 23/10/2015.
 */
public class FeedListAdapter extends RecyclerView.Adapter<FeedItemViewHolder> {

    private List<FeedItem> mFeedItems;
    private Context mContext;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Context context, List<FeedItem> items) {
        this.mContext = context;
        this.mFeedItems = items;
    }

    @Override
    public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, null);
        FeedItemViewHolder viewHolder = new FeedItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedItemViewHolder holder, int position) {

        if (mImageLoader == null)
            mImageLoader = AppController.getInstance().getImageLoader();

        FeedItem item = mFeedItems.get(position);
        holder.mTextView.setText(item.getName());
        holder.mImageView.setImageUrl(item.getImage(), mImageLoader);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return (null != mFeedItems ? mFeedItems.size() : 0);
    }

    /*

        @Override
    public int getCount() {
        return mFeedItems.size();
    }

    @Override

    public Object getItem(int i) {
        return mFeedItems.get(i);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (mInflater == null)
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = mInflater.inflate(R.layout.feed_itembkp, null);

        if (mImageLoader == null)
            mImageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView timestamp = (TextView) view.findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) view.findViewById(R.id.txtStatusMsg);
        //TextView url = (TextView) view.findViewById(R.id.txtUrl);

        //NetworkImageView profilePic = (NetworkImageView) view.findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) view.findViewById(R.id.feedImage1);

        FeedItem item = mFeedItems.get(position);

        name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            statusMsg.setVisibility(View.GONE);
        }

        if (item.getImage() != null) {
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
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        return view;
    }
    */
}
