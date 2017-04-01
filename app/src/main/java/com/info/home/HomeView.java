package com.info.home;

/**
 * Created by wolfmatrix on 3/31/17.
 */

public interface HomeView {
    interface View{
        void setupPresenter();
        void bindView();
        void initDagger();
        void setupStatusBarColor();
        void setupTitle();
        void navigate();
    }
    interface Presenter{
        void destroy();
        void onSuccess();
    }
}
