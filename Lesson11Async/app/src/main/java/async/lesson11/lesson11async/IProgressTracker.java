package async.lesson11.lesson11async;

/**
 * Created by User on 02.03.2018.
 */

public interface IProgressTracker {
    void onProgress(String message);

    void onComplete();
}
