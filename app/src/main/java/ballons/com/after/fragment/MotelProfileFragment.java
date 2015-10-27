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
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ballons.com.after.R;
import ballons.com.after.Utils.GradientOverImageDrawable;

/*
* carregar dados no scroll
* https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews
* https://gist.github.com/anonymous/b4d3597e913327afadd5
 */

public class MotelProfileFragment extends Fragment {

    private int mPosition;

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
