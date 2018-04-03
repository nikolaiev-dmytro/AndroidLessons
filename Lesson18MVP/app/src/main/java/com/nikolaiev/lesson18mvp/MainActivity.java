package com.nikolaiev.lesson18mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.nikolaiev.lesson18mvp.mvp.ListAdapter;
import com.nikolaiev.lesson18mvp.mvp.Model;
import com.nikolaiev.lesson18mvp.mvp.MyModel;
import com.nikolaiev.lesson18mvp.mvp.MyPresenter;
import com.nikolaiev.lesson18mvp.mvp.Presenter;
import com.nikolaiev.lesson18mvp.mvp.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View{
    RecyclerView mRecyclerView;
    EditText mEditText;
    Presenter mPresenter;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        mRecyclerView=findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEditText=findViewById(R.id.edit_text);
        mButton=findViewById(R.id.button);
        Model model=new MyModel();
        mPresenter=new MyPresenter();
        mPresenter.putModel(model);
        mPresenter.attachView(this);
        mButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                onExecuteButtonClick();
            }
        });
    }

    @Override
    public String getText() {
        return mEditText.getText().toString();
    }

    @Override
    public void onExecuteButtonClick() {
    mPresenter.addToModel(getText());
    showToList(mPresenter.load());

    }

    @Override
    public void showToList(List<String> strings) {
        ListAdapter adapter=new ListAdapter(strings);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
