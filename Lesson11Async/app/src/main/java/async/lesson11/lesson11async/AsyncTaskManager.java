package async.lesson11.lesson11async;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;

/**
 * Created by User on 02.03.2018.
 */

public class AsyncTaskManager implements IProgressTracker, DialogInterface.OnCancelListener {
    private OnTaskCompleteListener mTaskCompleteListener;
    private ProgressBar mProgressBar;
    private MainActivity.MathTask mTask;
    private TextView mResultTextView;
    private Button mStartButton;

    public AsyncTaskManager(Context context, ProgressBar progressBar, OnTaskCompleteListener taskCompleteListener, TextView resultTextView, Button button) {
        mTaskCompleteListener = taskCompleteListener;
        mProgressBar = progressBar;
        mResultTextView = resultTextView;
        mStartButton=button;
    }

    public void setupTask(MainActivity.MathTask asyncTask) {
        mTask = asyncTask;
        mTask.setProgressTracker(this);
        mTask.execute();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mTask.cancel(true);
        mTaskCompleteListener.onTaskComplete(mTask);
        mTask = null;
    }

    @Override
    public void onProgress(String message) {
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mStartButton.setVisibility(View.INVISIBLE);
        }
        mResultTextView.setText(message);
    }

    @Override
    public void onComplete() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mStartButton.setVisibility(View.VISIBLE);
        mTaskCompleteListener.onTaskComplete(mTask);
        mTask = null;
    }

    public Object retainTask() {
        if (mTask != null) {
            mTask.setProgressTracker(null);
        }
        return mTask;
    }

    public void handleRetainedTask(Object instance) {
        if (instance instanceof MainActivity.MathTask) {
            mTask = (MainActivity.MathTask) instance;
            mTask.setProgressTracker(this);
        }
    }

    public boolean isWorking() {
        return mTask != null;
    }
}
