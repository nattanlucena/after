package ballons.com.after.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ballons.com.after.extras.FeedImageView;
import ballons.com.after.R;
import ballons.com.after.app.AppController;
import ballons.com.after.model.FeedItem;

/**
 * Created by Nattan on 23/10/2015.
 */
public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedItemViewHolder> {

    private List<FeedItem> mFeedItems;
    private Context mContext;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
    LayoutInflater mLayoutInflater;

    public FeedListAdapter(Context context, List<FeedItem> items) {
        this.mContext = context;
        this.mFeedItems = items;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.feed_item, null);
        FeedItemViewHolder viewHolder = new FeedItemViewHolder(view);
        return viewHolder;
    }

    public void setFeedItemList(ArrayList<FeedItem> list) {
        this.mFeedItems = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(FeedItemViewHolder holder, int position) {

        FeedItem item = mFeedItems.get(position);
        holder.mTextView.setText(item.getName());

        loadImage(item.getImage(), holder);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return (null != mFeedItems ? mFeedItems.size() : 0);
    }

    static class FeedItemViewHolder extends RecyclerView.ViewHolder {
        public FeedImageView mImageView;
        public TextView mTextView;

        public FeedItemViewHolder(View itemView) {
            super(itemView);

            this.mImageView = (FeedImageView) itemView.findViewById(R.id.img_card_item);
            this.mTextView = (TextView) itemView.findViewById(R.id.txt_card_item);
        }
    }

    private void loadImage(String url, FeedItemViewHolder holder) {
        if (mImageLoader == null)
            mImageLoader = AppController.getInstance().getImageLoader();

        if (url != null) {
            holder.mImageView.setImageUrl(url, mImageLoader);
            holder.mImageView.setVisibility(View.VISIBLE);
            holder.mImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                @Override
                public void onError() {
                    //
                }

                @Override
                public void onSuccess() {
                    //
                }
            });
        } else {
            holder.mImageView.setVisibility(View.GONE);
        }
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
