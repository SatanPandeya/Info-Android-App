package com.info.info;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wolfmatrix on 4/5/17.
 */
@Singleton
@Component(modules = InfoModule.class)
public interface InfoComponent {
    void inject(InfoActivity infoActivity);
}
