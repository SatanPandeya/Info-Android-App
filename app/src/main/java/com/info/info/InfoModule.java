package com.info.info;

import com.info.home.HomeView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wolfmatrix on 4/1/17.
 */
@Module
public class InfoModule {
    private InfoView.View infoView;

    public InfoModule(InfoView.View infoView) {
        this.infoView = infoView;
    }

    @Singleton
    @Provides
    InfoView.View providesInfoView(){
        return infoView;
    }

    @Singleton
    @Provides
    InfoView.Presenter providesPresenter(InfoView.View infoView){
        return new InfoPresenter(infoView);
    }
}
