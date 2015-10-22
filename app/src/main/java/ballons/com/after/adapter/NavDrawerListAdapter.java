package ballons.com.after.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ballons.com.after.R;
import ballons.com.after.model.NavDrawerItem;

/**
 * Created by nattan on 10/22/15.
 */
public class NavDrawerListAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<NavDrawerItem> mNavDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> items){
        mContext = context;
        mNavDrawerItems = items;
    }

    @Override
    public int getCount() {
        return mNavDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mNavDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if( view == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView mIcon = (ImageView) view.findViewById(R.id.icon);
        TextView mTitle = (TextView) view.findViewById(R.id.title);
        TextView mCount = (TextView) view.findViewById(R.id.counter);

        mIcon.setImageResource(mNavDrawerItems.get(i).getIcon());
        mTitle.setText(mNavDrawerItems.get(i).getTitle());


        if(mNavDrawerItems.get(i).isCounterVisible()){
            mCount.setText(mNavDrawerItems.get(i).getCount());
        }else{
            mCount.setVisibility(View.GONE);
        }

        return null;
    }
}
