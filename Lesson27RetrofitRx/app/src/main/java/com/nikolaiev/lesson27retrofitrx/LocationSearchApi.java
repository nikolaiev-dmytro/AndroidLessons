package com.nikolaiev.lesson27retrofitrx;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationSearchApi {
    @GET("api/location/search/")
    Observable<ArrayList<Location>> getSearchResult(@Query("query") String query);
}
