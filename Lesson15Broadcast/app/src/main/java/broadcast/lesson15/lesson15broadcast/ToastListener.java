package broadcast.lesson15.lesson15broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by User on 13.03.2018.
 */

public class ToastListener implements Listen {
    BroadcastReceiver mBroadcastReceiver;
    @Override
    public void init(Context context) {
        mBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context,intent.getStringExtra("line"),Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter filter=new IntentFilter("SendBroadCast");
        context.registerReceiver(mBroadcastReceiver,filter);
    }

    @Override
    public void destroy(Context context) {
    context.unregisterReceiver(mBroadcastReceiver);
    }
}
