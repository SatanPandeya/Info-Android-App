package com.info.home;

import javax.inject.Inject;

/**
 * Created by wolfmatrix on 3/31/17.
 */

class HomePresenter implements HomeView.Presenter {
    private HomeView.View homeView;

    @Inject
    HomePresenter(HomeView.View homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onSuccess() {
        if (homeView != null){
            homeView.navigate();
        }
    }

    @Override
    public void destroy() {
        homeView = null;
    }
}
