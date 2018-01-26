package lesson1.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_SHORT).show();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_SHORT).show();

        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        Toast.makeText(getApplicationContext(),"onDestroy",Toast.LENGTH_SHORT).show();

        // The activity is about to be destroyed.
    }
}
