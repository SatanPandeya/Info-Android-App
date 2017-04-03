package com.info.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.info.Model.InfoModel;
import com.info.R;
import com.info.base.ToolBarActivity;
import com.info.database.InfoDBHelper;
import com.info.add.AddActivity;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.List;

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
    @BindView(R.id.recyclerListViewId)
    RecyclerView recyclerView;

    @Inject
    HomePresenter homePresenter;
    private Unbinder unbinder;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<InfoModel> infoModelList;
    private InfoDBHelper infoDBHelper;
    private HomeAdapter homeAdapter;

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
        setupDatabase();
        setupAdapter();
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

    @Override
    public void setupDatabase() {
        infoDBHelper = new InfoDBHelper(this);
    }

    @Override
    public void setupAdapter() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        infoModelList = infoDBHelper.getAllInfo();
        homeAdapter = new HomeAdapter(this, infoModelList);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(HomeActivity.this, "Clicked: " + infoModelList.get(position).getFName() , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(HomeActivity.this, "Long Clicked: " + infoModelList.get(position).getFName(), Toast.LENGTH_LONG).show();
            }
        }));
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
        startActivity(new Intent(HomeActivity.this, AddActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        homePresenter.destroy();
    }
}
