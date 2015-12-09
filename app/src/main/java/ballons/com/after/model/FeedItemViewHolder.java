/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package ballons.com.after.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ballons.com.after.FeedImageView;
import ballons.com.after.R;

/**
 * Created by nattanlucena on 09/12/15.
 */
public class FeedItemViewHolder extends RecyclerView.ViewHolder {

    public FeedImageView mImageView;
    public TextView mTextView;

    public FeedItemViewHolder(View itemView) {
        super(itemView);

        this.mImageView = (FeedImageView) itemView.findViewById(R.id.img_card_item);
        this.mTextView = (TextView) itemView.findViewById(R.id.txt_card_item);
    }

}
