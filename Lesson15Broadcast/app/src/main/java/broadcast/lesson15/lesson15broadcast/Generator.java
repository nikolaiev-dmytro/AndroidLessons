package broadcast.lesson15.lesson15broadcast;

import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 13.03.2018.
 */

public class Generator implements Sendable{

    public void send(Context context, String line) {
        Intent intent = new Intent();
        intent.setAction("SendBroadCast");
        intent.putExtra("line", line);
        context.sendBroadcast(intent);
    }
}
