package lesson4.lesson4intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button send;
    private EditText firstName;
    private EditText lastName;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.button1);
        firstName = (EditText) findViewById(R.id.editText1);
        lastName = (EditText) findViewById(R.id.editText2);
        send.setOnClickListener(this);
        String result = getIntent().getStringExtra("result");
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        if (!(result==null)) {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("user", new User(firstName.getText().toString(), lastName.getText().toString()));
                startActivity(intent);
                break;
            }
            case R.id.clear: {
                firstName.setText("");
                lastName.setText("");
                break;
            }
            default:
                break;
        }
    }
}
