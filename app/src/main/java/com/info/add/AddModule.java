package com.info.add;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wolfmatrix on 4/1/17.
 */
@Module
public class AddModule {
    private AddView.View infoView;

    public AddModule(AddView.View infoView) {
        this.infoView = infoView;
    }

    @Singleton
    @Provides
    AddView.View providesInfoView(){
        return infoView;
    }

    @Singleton
    @Provides
    AddView.Presenter providesPresenter(AddView.View infoView){
        return new AddPresenter(infoView);
    }
}
