package async.lesson11.lesson11async;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnTaskCompleteListener {
    private static final String TAG = "MainActivity";
    public Button mStartButton;
    private final String BUNDLE_RESULT_TEXT = "bundle_result_text";
    public TextView mResultTextView;
    public ProgressBar mProgressBar;
    public double mResult;
    public double mResultTime;
    AsyncTaskManager mAsyncTaskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultTextView = findViewById(R.id.result_textview);
        if (savedInstanceState != null) {
            mResultTextView.setText(savedInstanceState.getString(BUNDLE_RESULT_TEXT));
        }
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);
        final double[] resultTime = new double[1];
        mStartButton = findViewById(R.id.button_start);
        mAsyncTaskManager = new AsyncTaskManager(this, mProgressBar, this, mResultTextView, mStartButton);
        mAsyncTaskManager.handleRetainedTask(getLastCustomNonConfigurationInstance());
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // executeWithRunnable(resultTime);
                executeWithAsyncTask();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_RESULT_TEXT, mResultTextView.getText().toString());
    }

    private void executeWithAsyncTask() {

        mAsyncTaskManager.setupTask(new MathTask(getResources()));

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mAsyncTaskManager.retainTask();
    }

    private void executeWithRunnable(double mResultTime) {
        Runnable runnable = new Runnable() {
            double resultTime;

            @Override
            public void run() {
                double a = System.currentTimeMillis();
                Log.d(TAG, String.valueOf(a));
                mResult = 1;
                for (int i = 0; i < 10_000_000; i++) {
                    mResult = Math.pow(i, i) * Math.cos(mResult);
                }
                double b = System.currentTimeMillis();
                Log.d(TAG, String.valueOf(b));
                resultTime = b - a;
                Log.d(TAG, "Result " + String.valueOf(resultTime));
                mResultTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        mResultTextView.setText(String.valueOf(resultTime));
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onTaskComplete(MathTask task) {

    }

    class MathTask extends AsyncTask<Void, String, String> {
        protected final Resources mResources;
        private double mResult;
        private String mResultMessage;
        private String mProgressMessage;
        private IProgressTracker mProgressTracker;

        public MathTask(Resources resources) {
            mResources = resources;
        }

        public void setProgressTracker(IProgressTracker progressTracker) {
            mProgressTracker = progressTracker;
            if (mProgressTracker != null) {
                mProgressTracker.onProgress(mProgressMessage);
                if (mResultMessage != null) {
                    mProgressTracker.onComplete();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mProgressTracker = null;
        }

        @Override
        protected String doInBackground(Void... voids) {
            double a = System.currentTimeMillis();
            Log.d(TAG, String.valueOf(a));
            mResult = 1;
            for (int i = 1; i <= 10_000_000; i++) {
                double b = System.currentTimeMillis();
                if (i > 1 && i % 1_000_000 == 0) {
                    mResultMessage=String.valueOf(i) + " : " + String.valueOf(b - a);
                    publishProgress(mResultMessage);
                }
                mResult = Math.pow(i, i) * Math.cos(mResult);
            }
            double c = System.currentTimeMillis();
            Log.d(TAG, String.valueOf(c));
            mResultTime = c - a;
            Log.d(TAG, "Result " + String.valueOf(mResultTime));
            return mResultMessage;
        }

        @Override
        protected void onPreExecute() {
            mStartButton.setVisibility(View.INVISIBLE);
            mStartButton.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mResultMessage = s;
            if (mProgressTracker != null) {
                mProgressTracker.onComplete();
            }
            mResultTextView.setText(String.valueOf(mResultMessage));
            mStartButton.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mProgressMessage = values[0];
            if (mProgressTracker != null) {
                mProgressTracker.onProgress(mProgressMessage);
            }

        }
    }

}

