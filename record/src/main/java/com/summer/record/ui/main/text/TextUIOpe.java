package com.summer.record.ui.main.text;

//by summer on 2018-03-27.

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.ScreenUtil;
import com.summer.record.BR;
import com.summer.record.R;
import com.summer.record.data.text.Text;
import com.summer.record.databinding.FragMainTextBinding;

import java.util.ArrayList;

public class TextUIOpe extends BaseUIOpe<FragMainTextBinding> {

    public void initTexts(ArrayList<Text> texts){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_text_text, BR.item_text_text,texts));
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(ScreenUtil.最小DIMEN);
        bind.recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                for(int i=0;i<parent.getChildCount();i++){
                    View view = parent.getChildAt(i);
                    c.drawLine(parent.getLeft(),view.getTop(),parent.getRight(),view.getTop(),paint);
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = (int) (ScreenUtil.最小DIMEN*1);
            }
        });
    }

}
