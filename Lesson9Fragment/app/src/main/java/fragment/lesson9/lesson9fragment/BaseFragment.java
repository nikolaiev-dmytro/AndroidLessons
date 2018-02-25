package fragment.lesson9.lesson9fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 20.02.2018.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract int getLayout();

    protected abstract TYPE getType();

    private TYPE fragments;


    enum TYPE {START_FRAGMENT, SECOND_FRAGMENT, THIRD_FRAGMENT, FOURTH_FRAGMENT}

    public TYPE getFragments() {
        return fragments;
    }

    public void setFragments(TYPE fragments) {
        this.fragments = fragments;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAct().addType(getType());

    }

    @Override
    public void onPause() {
        super.onPause();
        getAct().removeType(getType());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);

    }

    protected MainActivity getAct() {
        return (MainActivity) getActivity();
    }

}
