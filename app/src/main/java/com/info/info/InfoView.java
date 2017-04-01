package com.info.info;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public interface InfoView {
    interface View{
        void setupStatusBarColor();
        void setupPresenter();
        void bindView();
        void initDagger();
        void navigate();
        void showAlert();
        void setupError();
        void setupDBHelper();
        void setupTitle();
    }

    interface Presenter{
        void destroy();
        void onSuccess();
        void validCredentials(String fName, String lName);
    }
}
