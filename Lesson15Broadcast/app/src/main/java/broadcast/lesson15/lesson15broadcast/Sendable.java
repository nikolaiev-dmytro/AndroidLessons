package broadcast.lesson15.lesson15broadcast;

import android.content.Context;

/**
 * Created by User on 13.03.2018.
 */

public interface Sendable {
    void send(Context context, String line);
}
