package fragment.lesson9.lesson9fragment;

/**
 * Created by user on 20.02.2018.
 */

public class SecondFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.second_fragment_layout;
    }

    @Override
    protected TYPE getType() {
        return TYPE.SECOND_FRAGMENT;
    }
}
