package viewpager.animations.lesson8animations;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private Button mPrevButton;
    private Button mShowFirstFragmentButton;
    private Button mShowSecondFragmentButton;
    FrameLayout mFrameLayout1;
    FrameLayout mFrameLayout2;
    Fragment1 mFragment1;
    Fragment2 mFragment2;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mFragment1 = new Fragment1();
        mFragment2 = new Fragment2();
        mFrameLayout1 = (FrameLayout) findViewById(R.id.frame1);
        mFrameLayout2 = (FrameLayout) findViewById(R.id.frame2);
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backAnimation();

            }
        });
        mShowFirstFragmentButton = (Button) findViewById(R.id.fragment1_button);
        mShowFirstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.add(R.id.frame1,mFragment1,mFragment1.getClass().getCanonicalName());
                mFragmentTransaction.commitAllowingStateLoss();
            }
        });
        mShowSecondFragmentButton = (Button) findViewById(R.id.fragment2_button);
        mShowSecondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.add(R.id.frame2,mFragment2,mFragment2.getClass().getCanonicalName());
                mFragmentTransaction.commitAllowingStateLoss();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backAnimation();
    }

    private void backAnimation() {
        finish();
        overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
    }
}
