package viewpager.animations.lesson8animations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Man on 09.02.2018.
 */

public class Fragment2 extends Fragment {
    private Button mToRightAndUpButton;
    private Button mToLeftAndUpButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getBackground());
        mToLeftAndUpButton=(Button) getActivity().findViewById(R.id.to_left_and_up_animation_button);
        mToLeftAndUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.left_and_up_in,R.anim.left_and_up_out);
            }
        });
        mToRightAndUpButton=(Button) getActivity().findViewById(R.id.to_right_and_up_animation_button);
        mToRightAndUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public int getBackground() {
        return Color.GREEN;
    }
}
