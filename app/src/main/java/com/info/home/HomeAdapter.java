package com.info.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.Model.InfoModel;
import com.info.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wolfmatrix on 4/2/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolderClass> {
    private Context context;
    private List<InfoModel> infoModelList;

    public HomeAdapter(Context context, List<InfoModel> infoModelList) {
        this.context = context;
        this.infoModelList = infoModelList;
    }

    @Override
    public HomeAdapter.MyViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View homeContainerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        return new MyViewHolderClass(homeContainerView);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolderClass holder, int position) {
        InfoModel infoModel = infoModelList.get(position);
        holder.firstName.setText(infoModel.getFName());
        holder.lastName.setText(infoModel.getLName());
    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class MyViewHolderClass extends RecyclerView.ViewHolder {
        @BindView(R.id.firstNameItemId)
        TextView firstName;
        @BindView(R.id.lastNameItemId)
        TextView lastName;

        public MyViewHolderClass(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
