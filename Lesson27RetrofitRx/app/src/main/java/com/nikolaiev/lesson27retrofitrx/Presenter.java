package com.nikolaiev.lesson27retrofitrx;

import java.util.ArrayList;

public interface Presenter {
    void getLocationList(String query);

    ArrayList<Location> setLocationList();

}
