package com.info.home;

import android.view.View;

/**
 * Created by wolfmatrix on 4/3/17.
 */

public interface RecyclerViewClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
