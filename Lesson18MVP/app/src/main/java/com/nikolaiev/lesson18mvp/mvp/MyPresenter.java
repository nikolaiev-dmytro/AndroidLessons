package com.nikolaiev.lesson18mvp.mvp;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30.03.2018.
 */

public class MyPresenter implements Presenter {
    private View mView;
    private Model mModel;

    public MyPresenter() {
    }

    public MyPresenter(View view, Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void putModel(Model model) {
        mModel = model;
    }

    @Override
    public void attachView(View view) {
        mView = view;
    }

    @Override
    public void save() {
        mModel.saveInstance();
    }

    @Override
    public List<String> load() {
        List<String> list = mModel.getList();
          return list;

    }

    @Override
    public Bundle restore() {
        return mModel.getInstance();
    }


    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void addToModel(String s) {
        mModel.addItem(s);
    }


}
