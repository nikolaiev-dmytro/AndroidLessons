package api.lesson13.lesson13api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 07.03.2018.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {
    private EditText mEditText;
    private Button mButtonSearch;
    private String url;
    private static final String BUNDLE_JSON_OBJECT_KEY = "bundle_json_object_key";
    private JSONObject mJSONObject;
    private static final String BUNDLE_EDIT_TEXT_REQUEST_KEY = "bundle_edit_text_request_key";
    private String editTextRequest = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_JSON_OBJECT_KEY) && savedInstanceState.containsKey(BUNDLE_EDIT_TEXT_REQUEST_KEY)) {
            try {
                mJSONObject = new JSONObject(savedInstanceState.getString(BUNDLE_JSON_OBJECT_KEY));
                editTextRequest = savedInstanceState.getString(BUNDLE_EDIT_TEXT_REQUEST_KEY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        url = getResources().getString(R.string.api_root)
                .concat("?method=artist.search&artist=");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mEditText = view.findViewById(R.id.edit_text);
        if (editTextRequest != null) {
            mEditText.setText(editTextRequest);
        }
        mButtonSearch = view.findViewById(R.id.search_button);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonSearch.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mJSONObject != null) {
            outState.putString(BUNDLE_JSON_OBJECT_KEY, mJSONObject.toString());
            outState.putString(BUNDLE_EDIT_TEXT_REQUEST_KEY, editTextRequest);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button: {
                Runnable search = new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection httpURLConnection = null;
                        editTextRequest = mEditText.getEditableText().toString();
                        String request = url.concat(editTextRequest)
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
                            mJSONObject = new JSONObject(stringBuilder.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        }
                    }
                };
                Thread searchThread = new Thread(search);
                searchThread.start();
                try {
                    searchThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long results = 0;
                try {
                    results = mJSONObject.getJSONObject("results").getLong("opensearch:totalResults");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (results != 0) {
                    JSONArray jsonArray = new JSONArray();
                    try {
                        jsonArray = mJSONObject.getJSONObject("results").optJSONObject("artistmatches").getJSONArray("artist");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArtistListFragment artistListFragment = new ArtistListFragment();
                    artistListFragment.newInstance(jsonArray);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,artistListFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Log.d(TAG, "onClick: " + getActivity().getSupportFragmentManager().getFragments().toString());
                } else {
                    Toast.makeText(getContext(),getString(R.string.not_found_exeption),Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
