package com.info.info;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.info.R;
import com.info.base.CoordinatorToolbarActivity;

/**
 * Created by wolfmatrix on 4/5/17.
 */

public class InfoActivity extends CoordinatorToolbarActivity {

    @Override
    protected int getCoordinatorResourceLayout() {
        return R.layout.activity_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
