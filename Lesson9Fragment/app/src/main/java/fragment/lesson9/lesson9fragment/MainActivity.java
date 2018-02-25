package fragment.lesson9.lesson9fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<BaseFragment.TYPE> types = new ArrayList<>();
    FragmentTransaction transaction;
    String TAG = "MainActivity";
    FrameLayout backLayout;
    FrameLayout frontLayout;

    public void addType(BaseFragment.TYPE type) {
        types.add(type);
    }

    public void removeType(BaseFragment.TYPE type) {
        types.remove(type);
    }

    public void printAllFragment() {
        Log.d(TAG, types.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backLayout = findViewById(R.id.backgroundF_framelayout);
        frontLayout = findViewById(R.id.foreground_framelayout);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.backgroundF_framelayout,new StartFragment());
        transaction.commit();
        Log.d(TAG, getSupportFragmentManager().getFragments().toString());

    }
}