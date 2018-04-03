package broadcast.lesson15.lesson15broadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button mButtonSend;
    EditText mEditText;
    protected Generator mGenerator;
    protected Listen mLogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGenerator.send(getApplicationContext(),mEditText.getEditableText().toString());
            }
        });

    }

    private void initView() {
        mButtonSend=findViewById(R.id.send_button);
        mEditText=findViewById(R.id.edit_text);
    }

    private void init() {
        mGenerator = new Generator();
        mLogListener =new ToastListener();
        mLogListener.init(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLogListener.destroy(getApplicationContext());
    }
}
