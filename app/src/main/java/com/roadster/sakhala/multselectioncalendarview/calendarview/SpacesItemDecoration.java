package com.roadster.sakhala.multselectioncalendarview.calendarview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.roadster.sakhala.multselectioncalendarview.R;

/**
 * Created by atulsakhala on 30/08/17.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(Context context) {
        this.space = context.getResources().getDimensionPixelSize(R.dimen.pixel_size);;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.top = 12;
        outRect.bottom = 12;
        outRect.left = space;
        outRect.right = space;
    }
}
