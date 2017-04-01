package com.info.home;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wolfmatrix on 3/31/17.
 */
@Module
public class HomeModule {
    private HomeView.View homeView;

    public HomeModule(HomeView.View homeView) {
        this.homeView = homeView;
    }

    @Singleton
    @Provides
    HomeView.View providesHomeView(){
        return homeView;
    }

    @Singleton
    @Provides
    HomeView.Presenter providesPresenter(HomeView.View homeView){
        return new HomePresenter(homeView);
    }
}
