package api.lesson13.lesson13api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButtonSearch;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url=getResources().getString(R.string.api_root);
        mEditText = findViewById(R.id.edit_text);
        mButtonSearch = findViewById(R.id.search_button);
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpURLConnection httpURLConnection = null;
                String request = url.concat("?method=artist.getinfo&artist=")
                        .concat(mEditText.getText().toString())
                        .concat("&api_key=")
                        .concat(getResources().getString(R.string.api_key))
                        .concat("&format=json");
                try {
                    URL urlRequest = new URL(request);
                    httpURLConnection = (HttpURLConnection) urlRequest.openConnection();
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();
                    Log.d("TAG", "String: " + stringBuilder.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        });
    }
}
