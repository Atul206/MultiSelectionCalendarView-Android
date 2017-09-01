package com.roadster.sakhala.multselectioncalendarview.calendarview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by atulsakhala on 30/08/17.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration() {
        this.space = 0;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.top = 18;
        outRect.bottom = 10;
        outRect.left = 0;
        outRect.right = 0;
    }
}
