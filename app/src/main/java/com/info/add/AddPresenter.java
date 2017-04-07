package com.info.add;

import javax.inject.Inject;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class AddPresenter implements AddView.Presenter {
    private AddView.View infoView;

    @Inject
    public AddPresenter(AddView.View infoView) {
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
    public void validCredentials(String fName, String lName, String phNo) {
        if (infoView != null){
            infoView.saveData(fName, lName, phNo);
        }
    }


    @Override
    public void destroy() {
        infoView = null;
    }
}
