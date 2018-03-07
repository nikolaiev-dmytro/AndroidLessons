package adapter.lesson12.lesson12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mButtonFill;
    private ListView mListView;
    private final String[] catNames = new String[]{
            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
            "Китти", "Масяня", "Симба"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonFill = findViewById(R.id.button_fill);
        mListView = findViewById(R.id.list_view);
        final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, catNames);
        mButtonFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setAdapter(mArrayAdapter);
            }
        });
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://www.umori.li/api/random?num=10");
                    //URL url = new URL("http://google.com");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb=new StringBuilder();
                    String read;

                    while((read=br.readLine()) != null) {
                        //System.out.println(read);
                        sb.append(read);
                    }

                    br.close();
                    Log.d("TAG", "String: " + sb.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection!=null) {
                        urlConnection.disconnect();
                    }
                }

            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
}
