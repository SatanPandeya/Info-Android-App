package com.info.add;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wolfmatrix on 4/1/17.
 */
@Singleton
@Component(modules = AddModule.class)
public interface AddComponent {
    void inject(AddActivity infoActivity);
}
