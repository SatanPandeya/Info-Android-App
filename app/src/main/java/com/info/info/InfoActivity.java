package com.info.info;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.info.Model.InfoModel;
import com.info.R;
import com.info.base.CoordinatorToolbarActivity;
import com.info.database.InfoDBHelper;
import com.info.home.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wolfmatrix on 4/5/17.
 */

public class InfoActivity extends CoordinatorToolbarActivity implements InfoView.View{
    @Inject InfoPresenter infoPresenter;
    private Unbinder unbinder;
    private InfoDBHelper infoDBHelper;
    private InfoModel infoModel;

    @BindView(R.id.coToolbarId)
    Toolbar toolbar;
    @BindView(R.id.coToolbarTitleId)
    TextView title;
    @BindView(R.id.coCollapsingToolbarLayoutId)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected int getCoordinatorResourceLayout() {
        return R.layout.activity_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupStatusBarColor();
        setupPresenter();
        bindView();
        disableDefaultTitle();
        setupDagger();
        setupDbHelper();
        setupTitle();
    }

    @Override
    public void setupPresenter() {
        infoPresenter = new InfoPresenter(this);
    }

    @Override
    public void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setupDagger() {
        DaggerInfoComponent.builder().infoModule(new InfoModule(this)).build().inject(this);
    }

    @Override
    public void disableDefaultTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setupStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBar));
        }
    }

    @Override
    public void setupDbHelper() {
        infoDBHelper = new InfoDBHelper(this);
    }

    @Override
    public void setupTitle() {
        String tagName = getIntent().getStringExtra("ClickItemFirstName");
        infoModel = infoDBHelper.getInfo(tagName);
        collapsingToolbarLayout.setTitle(infoModel.getFName() + " " + infoModel.getLName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        infoPresenter.destroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InfoActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
