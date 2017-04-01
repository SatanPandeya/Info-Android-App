package com.info.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.R;

/**
 * Created by wolfmatrix on 3/31/17.
 */

public abstract class ToolBarActivity extends BaseActivity {
    private static ToolBarActivity toolBarActivityInstance;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    public ToolBarActivity(){}
    public static ToolBarActivity getToolBarActivityInstance(){
        if (toolBarActivityInstance == null){
            toolBarActivityInstance = new ToolBarActivity() {
                @Override
                protected int getToolbarResourceLayout() {
                    return R.layout.activity_toolbar;
                }
            };
        }
        return toolBarActivityInstance;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_toolbar;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View container = findViewById(R.id.flContainerId);
        if (container instanceof ViewGroup){
            ((ViewGroup) container)
                    .addView(LayoutInflater.from(this)
                            .inflate(getToolbarResourceLayout(), (ViewGroup) container, false));
        }
        toolbar = (Toolbar) findViewById(R.id.toolbarId);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTextId);
        setSupportActionBar(toolbar);
    }

    public  void setToolbarTitle(String title){
        toolbarTitle.setText(title);
    }

    protected abstract int getToolbarResourceLayout();
}
