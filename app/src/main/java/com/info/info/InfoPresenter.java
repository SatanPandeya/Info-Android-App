package com.info.info;

import javax.inject.Inject;

/**
 * Created by wolfmatrix on 4/5/17.
 */

public class InfoPresenter implements InfoView.Presenter {
    private InfoView.View infoView;

    @Inject
    public InfoPresenter(InfoView.View infoView) {
        this.infoView = infoView;
    }

    @Override
    public void destroy() {
        infoView = null;
    }
}
