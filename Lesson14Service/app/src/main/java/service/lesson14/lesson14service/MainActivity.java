package service.lesson14.lesson14service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.Os;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {
    Button mStartServiceButton;
    Button mStopServiceButton;
    EditText mSizeText;
    BroadcastReceiver mBroadcastReceiver;
    public static final int STATUS_START = 1;
    public static final int STATUS_FINISH = 0;
    public static final String BROADCAST_ACTION = "MainActivity";
    public static final String STATUS = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSizeText = findViewById(R.id.size_edit_text);
        mStartServiceButton = findViewById(R.id.start_service);
        mStopServiceButton = findViewById(R.id.stop_service);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int statusCode = intent.getIntExtra(STATUS, 0);
                if (statusCode == STATUS_START) {
                    notify(statusCode,getString(R.string.start_service_message));
                } else if (statusCode==STATUS_FINISH){
                    notify(statusCode,getString(R.string.stop_service_message));
                }
            }

            private void notify(int statusCode,String message) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(message)
                        .setAutoCancel(true);
                Notification notification = builder.build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    notificationManager.notify(statusCode, notification);
                }
            }
        };
        IntentFilter filter=new IntentFilter(BROADCAST_ACTION);
        registerReceiver(mBroadcastReceiver,filter);
        mStartServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("size", Integer.valueOf(mSizeText.getText().toString()));
                startService(intent);
            }
        });
        mStopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
