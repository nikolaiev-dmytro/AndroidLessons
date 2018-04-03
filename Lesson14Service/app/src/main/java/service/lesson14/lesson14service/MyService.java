package service.lesson14.lesson14service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 09.03.2018.
 */

public class MyService extends Service {
    String[] mStrings;
    Random mRandom;
    List<String> mStringList;

    @Override
    public void onCreate() {
        super.onCreate();
        mRandom = new Random();
        mStringList = new ArrayList<>();
        Log.d(TAG, "onCreate: MyService");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: MyService");
        mStrings = new String[intent.getIntExtra("size", 1_000_000)];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
                intent.putExtra(MainActivity.STATUS, MainActivity.STATUS_START);
                sendBroadcast(intent);
                for (int i = 0; i < mStrings.length; i++) {
                    mStrings[i] = String.valueOf(mRandom.nextInt(100_000_000) + 1);
                }
                String temp = mStrings[0];
                for (int i = 0; i < mStrings.length; i++) {
                    for (int j = 1; j < mStrings.length - 1; j++) {
                        if (mStrings[j - 1].compareTo(mStrings[j]) > 0) {
                            temp = mStrings[j - 1];
                            mStrings[j - 1] = mStrings[j];
                            mStrings[j] = temp;
                        }
                    }
                }
                intent.putExtra(MainActivity.STATUS, MainActivity.STATUS_FINISH);
                sendBroadcast(intent);

                stopSelf();
            }
        };
        Thread thread = new Thread(runnable, "MyThread");
        thread.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyService");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: MyService");
        return null;
    }
}
