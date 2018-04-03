package com.nikolaiev.lesson18mvp.mvp;

import android.os.Bundle;

import java.util.List;

/**
 * Created by User on 30.03.2018.
 */

public interface Presenter {
    void putModel(Model model);

    void attachView(View view);


    void save();
    List<String> load();
    Bundle restore();


    void destroy();
    void addToModel(String s);
}
