package application.lesson14.lesson14application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 09.03.2018.
 */

public class Activity1 extends AppCompatActivity implements View.OnClickListener {
    Button mButton1;
    Button mButton2;
    Button mButton3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1 = findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = findViewById(R.id.button2);

        mButton2.setOnClickListener(this);
        mButton3 = findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                Intent intent = new Intent(this, Activity1.class);
                startActivity(intent);
            }
            case R.id.button2: {
                Intent intent = new Intent(this, Activity2.class);
                startActivity(intent);
            }
            case R.id.button3: {
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            }
        }
    }
}
