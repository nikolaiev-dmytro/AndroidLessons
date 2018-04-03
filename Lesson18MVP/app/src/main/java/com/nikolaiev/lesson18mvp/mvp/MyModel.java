package com.nikolaiev.lesson18mvp.mvp;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30.03.2018.
 */

public class MyModel extends Model {
    List<String> mList=new ArrayList<>();
    Bundle mBundle=new Bundle();

    public MyModel(List<String> list) {
        mList = list;
    }

    public MyModel() {
        mList=new ArrayList<>();
    }


    @Override
    public void saveInstance() {
        mBundle.putStringArrayList("strings", (ArrayList<String>) mList);
    }

    @Override
    public Bundle getInstance() {
        return mBundle;
    }

    @Override
    public void addItem(String s) {
        mList.add(s);
    }

    @Override
    public List<String> getList() {
        return mList;
    }

}
