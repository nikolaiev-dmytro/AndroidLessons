package fragment.lesson7fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button nextButton;
    private Button prevButton;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private FragmentTransaction transaction;
    private FrameLayout frame;
    private FrameLayout mFrame1;
    private FrameLayout mFrame2;
    private int mFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mFrame1 = (FrameLayout) findViewById(R.id.frame1);
            mFrame2 = (FrameLayout) findViewById(R.id.frame2);

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame1, fragment1, fragment1.getClass().getCanonicalName());
            transaction.add(R.id.frame2, fragment2, fragment1.getClass().getCanonicalName());
            transaction.commitAllowingStateLoss();
        } else {
            frame = (FrameLayout) findViewById(R.id.frame);
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame, fragment1, fragment1.getClass().getCanonicalName());
            transaction.commitAllowingStateLoss();
            mFragmentId = fragment1.getId();
            nextButton = (Button) findViewById(R.id.next_button);
            prevButton = (Button) findViewById(R.id.prev_button);
            nextButton.setOnClickListener(this);
            prevButton.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View view) {
        transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.next_button: {
                if (mFragmentId == fragment1.getId()) {
                    transaction.replace(R.id.frame, fragment2);
                    mFragmentId = fragment2.getId();
                } else {
                    transaction.replace(R.id.frame, fragment1, fragment1.getClass().getCanonicalName());
                    mFragmentId = fragment1.getId();
                }
                break;
            }
            case R.id.prev_button: {
                if (mFragmentId == fragment1.getId()) {
                    transaction.replace(R.id.frame, fragment2);
                    mFragmentId = fragment2.getId();
                } else {
                    transaction.replace(R.id.frame, fragment1, fragment1.getClass().getCanonicalName());
                    mFragmentId = fragment1.getId();
                }
                break;
            }
        }
        transaction.commitAllowingStateLoss();
    }

}
