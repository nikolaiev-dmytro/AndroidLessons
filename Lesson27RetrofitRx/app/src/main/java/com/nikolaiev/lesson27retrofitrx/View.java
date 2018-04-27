package com.nikolaiev.lesson27retrofitrx;

import java.util.ArrayList;
import java.util.List;

public interface View {
    void loadLocations(ArrayList<Location> locations);

    void search(String query);
}
