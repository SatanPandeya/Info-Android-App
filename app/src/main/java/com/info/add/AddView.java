package com.info.add;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public interface AddView {
    interface View{
        void setupStatusBarColor();
        void setupPresenter();
        void bindView();
        void initDagger();
        void navigate();
        void setupDBHelper();
        void setupTitle();
        void saveData(String fName, String lName, String phNo);
        void onError();
    }

    interface Presenter{
        void destroy();
        void validCredentials(String fName, String lName, String phNo);
        void onSuccess();
        void setupError();
    }
}
