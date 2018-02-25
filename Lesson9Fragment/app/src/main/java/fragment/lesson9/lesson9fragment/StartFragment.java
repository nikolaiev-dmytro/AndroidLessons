package fragment.lesson9.lesson9fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;

/**
 * Created by user on 20.02.2018.
 */

public class StartFragment extends BaseFragment {
    Button mStartSecondFragment;
    Button mHideSecondFragment;

    @Override
    protected int getLayout() {
        return R.layout.start_fragment_layout;
    }

    @Override
    protected TYPE getType() {
        return TYPE.START_FRAGMENT;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mHideSecondFragment = view.findViewById(R.id.hide_second_button);
        mHideSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.detach(getActivity().getSupportFragmentManager().getFragments().get(getActivity().getSupportFragmentManager().getFragments().size() - 1));
                transaction.commit();
                Log.d(TAG, getActivity().getSupportFragmentManager().getFragments().toString());
                Log.d(TAG, getActivity().getSupportFragmentManager().getFragments().get(0).getLifecycle().getCurrentState().toString());
            }

        });
        mStartSecondFragment = view.findViewById(R.id.start_second_button);
        mStartSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.foreground_framelayout, new SecondFragment());
                transaction.commit();
                Log.d(TAG, getActivity().getSupportFragmentManager().getFragments().toString());
            }
        });
        return view;
    }
}
