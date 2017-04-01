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
    public void validCredentials(String fName, String lName) {
        boolean error = false;
        if (TextUtils.isEmpty(fName)){
            infoView.setupError();
            error = true;
        } if (TextUtils.isEmpty(lName)){
            infoView.setupError();
            error = true;
        } if (!error){
            infoView.navigate();
        }
    }


    @Override
    public void destroy() {
        infoView = null;
    }
}
