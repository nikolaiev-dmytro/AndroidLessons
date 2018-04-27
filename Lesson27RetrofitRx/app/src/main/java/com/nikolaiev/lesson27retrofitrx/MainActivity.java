package com.nikolaiev.lesson27retrofitrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View {
    private RecyclerView mRecyclerView;
    private Button searchButton;
    private EditText searchQuery;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterIml(this);
        setContentView(R.layout.activity_main);
        searchQuery = findViewById(R.id.search_query);
        searchButton = findViewById(R.id.search_button);
        mRecyclerView = findViewById(R.id.result_list);
        searchButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                search(searchQuery.getText().toString());
            }
        });
    }

    @Override
    public void loadLocations(ArrayList<Location> locations) {

    }

    @Override
    public void search(String query) {
        mPresenter.getLocationList(query);
    }
}
