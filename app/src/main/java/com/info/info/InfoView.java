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
        void setupDBHelper();
        void setupTitle();
        void saveData(String fName, String lName);
        void onError();
    }

    interface Presenter{
        void destroy();
        void validCredentials(String fName, String lName);
        void onSuccess();
        void setupError();
    }
}
