package com.nikolaiev.lesson27retrofitrx;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterIml implements Presenter {
    private View mMainActivity;
    private ArrayList<Location> mLocationList;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private LocationSearchApi api = LocationSearchClient.getInstance().getApi();

    public PresenterIml(View mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void getLocationList(String query) {
        mCompositeDisposable.add(api.getSearchResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, Throwable::printStackTrace));
    }

    private void handleResponse(ArrayList<Location> locations) {
        mLocationList = new ArrayList<>(locations);
        mMainActivity.loadLocations(mLocationList);
    }

    @Override
    public ArrayList<Location> setLocationList() {
        return mLocationList;
    }
}
