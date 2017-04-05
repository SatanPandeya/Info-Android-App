package com.info.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.info.R;

/**
 * Created by wolfmatrix on 4/5/17.
 */

public abstract class CoordinatorToolbarActivity extends BaseActivity {
    private Toolbar toolbar;
    @Override
    protected int getResourceLayout() {
        return R.layout.coord_tool_bar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View flContainer = findViewById(R.id.flContainerId);
        if (flContainer instanceof ViewGroup){
            ((ViewGroup) flContainer)
                    .addView(LayoutInflater.from(this)
                            .inflate(getCoordinatorResourceLayout(), (ViewGroup) flContainer, false));
        }
        toolbar = (Toolbar) findViewById(R.id.coToolbarId);
        setSupportActionBar(toolbar);
    }

    protected abstract int getCoordinatorResourceLayout();
}
