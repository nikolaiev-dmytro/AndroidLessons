package lesson4.lesson4intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView firstName;
    private TextView lastName;
    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firstName = (TextView) findViewById(R.id.textView3);
        lastName = (TextView) findViewById(R.id.textView4);
        User user = (User) (getIntent().getParcelableExtra("user"));
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        ok=(Button)findViewById(R.id.ok);
        cancel=(Button)findViewById(R.id.cancel);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (view.getId()) {
            case R.id.cancel: {
                intent.putExtra("result", "Canceled");
                startActivity(intent);
                break;
            }
            case R.id.ok: {
                intent.putExtra("result","Data is delivered");
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
