package com.nikolaiev.lesson18mvp.mvp;

import android.os.Bundle;

import java.util.List;

/**
 * Created by User on 30.03.2018.
 */

public abstract class Model {
    public abstract void saveInstance();

    public abstract Bundle getInstance();

    public abstract void addItem(String s);
    public abstract List<String> getList();
}
