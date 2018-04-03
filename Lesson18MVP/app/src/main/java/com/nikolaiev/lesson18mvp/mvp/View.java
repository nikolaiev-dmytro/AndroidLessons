package com.nikolaiev.lesson18mvp.mvp;

import java.util.List;

/**
 * Created by User on 30.03.2018.
 */

public interface View {
    String getText();
    void onExecuteButtonClick();
    void showToList(List<String> strings);
}
