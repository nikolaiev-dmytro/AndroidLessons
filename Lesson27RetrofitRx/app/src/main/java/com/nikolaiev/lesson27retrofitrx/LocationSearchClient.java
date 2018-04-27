package com.nikolaiev.lesson27retrofitrx;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocationSearchClient {
    private static LocationSearchClient ourInstance = new LocationSearchClient();
    private LocationSearchApi mLocationSearchApi;
    private static final String BASE_URL = "https://www.metaweather.com/";

    public static LocationSearchClient getInstance() {
        return ourInstance;
    }

    private LocationSearchClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mLocationSearchApi = retrofit.create(LocationSearchApi.class);
    }

    public LocationSearchApi getApi() {
        return mLocationSearchApi;
    }
}
