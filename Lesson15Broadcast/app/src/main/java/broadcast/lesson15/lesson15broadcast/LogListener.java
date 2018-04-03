package broadcast.lesson15.lesson15broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 13.03.2018.
 */

public class LogListener implements Listen{
    private BroadcastReceiver mBroadcastReceiver;
    public void init(Context context) {
        mBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: "+ intent.getStringExtra("line"));
            }
        };
        IntentFilter filter=new IntentFilter("SendBroadCast");
        context.registerReceiver(mBroadcastReceiver,filter);
    }

    public void destroy(Context context) {
        context.unregisterReceiver(mBroadcastReceiver);
    }
}
