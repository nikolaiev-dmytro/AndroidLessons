package api.lesson13.lesson13api;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import api.lesson13.lesson13api.model.Artist;
import api.lesson13.lesson13api.model.Image;
import api.lesson13.lesson13api.model.Stats;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 06.03.2018.
 */

public class ArtistListFragment extends Fragment {
    List<Artist> mArtistList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    static final String BUNDLE_JSON_ARRAY_KEY = "bundle_json_array_key";
    JSONArray mArtistJSONArray;


    public void newInstance(JSONArray jsonArray) {
        mArtistJSONArray = jsonArray;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_JSON_ARRAY_KEY)) {
            try {
                mArtistJSONArray = new JSONArray(savedInstanceState.getString(BUNDLE_JSON_ARRAY_KEY));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mArtistJSONArray.length(); i++) {
                    try {
                        Artist artist = new Artist();
                        JSONObject artistJsonObject = mArtistJSONArray.getJSONObject(i);
                        artist.setName(artistJsonObject.getString("name"));
                        artist.setMbid(artistJsonObject.getString("mbid"));
                        artist.setStats(new Stats(artistJsonObject.getString("listeners")));
                        artist.setUrl(artistJsonObject.getString("url"));
                        artist.setStreamable(artistJsonObject.getString("streamable"));
                        List<Image> imageList = new ArrayList<>();
                        JSONArray imagesJsonArray = artistJsonObject.getJSONArray("image");
                        for (int j = 0; j < imagesJsonArray.length(); j++) {
                            JSONObject imageJsonObject = imagesJsonArray.getJSONObject(j);
                            Image image = new Image();
                            image.setText(imageJsonObject.getString("#text"));
                            image.setSize(imageJsonObject.getString("size"));
                            imageList.add(image);
                        }
                        artist.setImage(imageList);
                        mArtistList.add(artist);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();
        Log.d(TAG, "onViewCreated: " + getActivity().getSupportFragmentManager().getFragments().toString());

    }

    public void refresh() {
        mAdapter = new RecyclerViewAdapter(getActivity(), mArtistList);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_JSON_ARRAY_KEY, mArtistJSONArray.toString());
    }
}
