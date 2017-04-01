package com.info.info;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.info.Model.InfoModel;
import com.info.R;
import com.info.base.ToolBarActivity;
import com.info.database.InfoDBHelper;
import com.info.home.HomeActivity;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoActivity extends ToolBarActivity implements InfoView.View {
    @BindView(R.id.infoFabId)
    FloatingActionButton infoFabButton;
    @BindView(R.id.firstNameId)
    TextInputEditText firstName;
    @BindView(R.id.lastNameId)
    TextInputEditText lastName;

    @Inject
    InfoPresenter infoPresenter;
    private Unbinder unbinder;
    private InfoDBHelper infoDBHelper;
    @Override
    protected int getToolbarResourceLayout() {
        return R.layout.activity_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupStatusBarColor();
        setupPresenter();
        bindView();
        initDagger();
        setupDBHelper();
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
    public void setupPresenter() {
        infoPresenter = new InfoPresenter(this);
    }

    @Override
    public void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initDagger() {
        DaggerInfoComponent.builder().infoModule(new InfoModule(this)).build().inject(this);
    }

    @Override
    public void setupDBHelper() {
        infoDBHelper = new InfoDBHelper(this);
    }

    @Override
    public void setupError() {
        firstName.setError(getString(R.string.error));
        lastName.setError(getString(R.string.error));
    }

    @OnClick(R.id.infoFabId)
    public void navigate(View view) {
        infoDBHelper.addToDoList(new InfoModel(firstName.getText().toString(), lastName.getText().toString()));
    }


    @Override
    public void navigate() {
        infoPresenter.validCredentials(firstName.getText().toString(), lastName.getText().toString());
        Intent intent = new Intent(InfoActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        infoPresenter.destroy();
    }

    @Override
    public void onBackPressed() {
        showAlert();
    }

    @Override
    public void showAlert() {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.colorOrange)
                .setButtonsColorRes(R.color.colorOrangeButton)
                .setTitle(R.string.text_confirmation)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setMessage(R.string.text_message)
                .setPositiveButton(R.string.text_yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infoPresenter.onSuccess();
                    }
                })
                .setNegativeButton(R.string.text_no, null)
                .setCancelable(false)
                .show();
    }

}
