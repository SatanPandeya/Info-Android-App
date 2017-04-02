package com.info.info;

import android.text.TextUtils;

import javax.inject.Inject;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoPresenter implements InfoView.Presenter {
    private InfoView.View infoView;

    @Inject
    public InfoPresenter(InfoView.View infoView) {
        this.infoView = infoView;
    }

    @Override
    public void onSuccess() {
        if (infoView != null) {
            infoView.navigate();
        }
    }

    @Override
    public void setupError() {
        if (infoView != null){
            infoView.onError();
        }
    }

    @Override
    public void validCredentials(String fName, String lName) {
        if (infoView != null){
            infoView.saveData(fName, lName);
        }
    }


    @Override
    public void destroy() {
        infoView = null;
    }
}
