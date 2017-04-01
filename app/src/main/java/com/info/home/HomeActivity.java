package com.info.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.info.R;
import com.info.base.ToolBarActivity;
import com.info.info.InfoActivity;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeActivity extends ToolBarActivity implements HomeView.View {
    @BindView(R.id.toolbarTextId)
    TextView title;
    @BindView(R.id.homeFabId)
    FloatingActionButton floatingActionButton;

    @Inject
    HomePresenter homePresenter;
    private Unbinder unbinder;

    @Override
    protected int getToolbarResourceLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupPresenter();
        bindView();
        initDagger();
        setupStatusBarColor();
        setupTitle();
    }

    @Override
    public void setupPresenter() {
        homePresenter = new HomePresenter(this);
    }

    @Override
    public void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initDagger() {
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
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
    public void setupTitle() {
        title.setText(R.string.home);
    }

    @OnClick(R.id.homeFabId)
    public void navigateView(View view) {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.colorOrange)
                .setButtonsColorRes(R.color.colorOrangeButton)
                .setTitle(R.string.text_confirmation)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setMessage(R.string.text_message)
                .setPositiveButton(R.string.text_yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homePresenter.onSuccess();
                    }
                })
                .setNegativeButton(R.string.text_no, null)
                .setCancelable(false)
                .show();
    }

    @Override
    public void navigate() {
        startActivity(new Intent(HomeActivity.this, InfoActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        homePresenter.destroy();
    }
}
