package com.info.info;

/**
 * Created by wolfmatrix on 4/5/17.
 */

public interface InfoView {
    interface View {
        void setupPresenter();
        void bindView();
        void setupDagger();
        void disableDefaultTitle();
        void setupStatusBarColor();
        void setupTitle();
        void setupDbHelper();
    }

    interface Presenter {
        void destroy();
    }
}
