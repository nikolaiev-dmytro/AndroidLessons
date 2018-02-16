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

public class Fragment1 extends Fragment {
    private Button mToLeftButton;
    private Button mToRightButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.MAGENTA);

        mToLeftButton=(Button) getActivity().findViewById(R.id.to_left_animation_button);
        mToLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });
        mToRightButton=(Button) getActivity().findViewById(R.id.to_right_animation_button);
        mToRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.left_in,R.anim.right_out);

            }
        });
    }
}
