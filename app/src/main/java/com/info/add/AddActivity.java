package com.info.add;

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
import android.widget.TextView;

import com.info.Model.InfoModel;
import com.info.R;
import com.info.base.ToolBarActivity;
import com.info.database.InfoDBHelper;
import com.info.home.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class AddActivity extends ToolBarActivity implements AddView.View {
    @BindView(R.id.toolbarTextId)
    TextView title;
    @BindView(R.id.infoFabId)
    FloatingActionButton infoFabButton;
    @BindView(R.id.firstNameId)
    TextInputEditText firstName;
    @BindView(R.id.lastNameId)
    TextInputEditText lastName;

    @Inject
    AddPresenter infoPresenter;
    private Unbinder unbinder;
    private InfoDBHelper infoDBHelper;
    @Override
    protected int getToolbarResourceLayout() {
        return R.layout.activity_add;
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
        setupTitle();
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
        infoPresenter = new AddPresenter(this);
    }

    @Override
    public void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void initDagger() {
        DaggerAddComponent.builder().addModule(new AddModule(this)).build().inject(this);
    }

    @Override
    public void setupDBHelper() {
        infoDBHelper = new InfoDBHelper(this);
    }

    @Override
    public void onError() {
        firstName.setError(getString(R.string.error));
        lastName.setError(getString(R.string.error));
    }

    @Override
    public void setupTitle() {
        title.setText(R.string.add);
    }

    @OnClick(R.id.infoFabId)
    public void navigateView(View view){
        infoPresenter.validCredentials(firstName.getText().toString(), lastName.getText().toString());
    }

    @Override
    public void navigate() {
        infoDBHelper.addInfo(new InfoModel(firstName.getText().toString(), lastName.getText().toString()));
        Intent intent = new Intent(AddActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void saveData(String fName, String lName) {
        boolean error = false;
        if (TextUtils.isEmpty(fName)){
            infoPresenter. setupError();
            error = true;
        } if (TextUtils.isEmpty(lName)){
            infoPresenter.setupError();
            error = true;
        } if (!error){
            infoPresenter.onSuccess();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        infoPresenter.destroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
