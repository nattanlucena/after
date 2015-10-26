package ballons.com.after.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ballons.com.after.R;

public class MotelProfileFragment extends Fragment {

    private int mPosition;

    public MotelProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("Motel Profile");
        return inflater.inflate(R.layout.fragment_motel_profile, container, false);
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mId) {
        this.mPosition = mId;
    }

}
