package com.info.home;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wolfmatrix on 3/31/17.
 */
@Singleton
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
