package com.summer.record.ui.main.video.video;

//by summer on 2018-04-03.

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ArrayRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.util.ScreenUtil;
import com.summer.record.data.Record;

import java.util.ArrayList;

public class VideoItemDecoration extends RecyclerView.ItemDecoration {
    
    ArrayList<Record> records;

    final Paint paint = new Paint();
    
    public VideoItemDecoration(ArrayList<Record> records){
        this.records = records;
        paint.setColor(Color.GRAY);
        paint.setTextSize(ScreenUtil.字宽度*18);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for(int i=0;i<parent.getChildCount();i++){
            int pos = parent.getChildAdapterPosition(parent.getChildAt(i));
            if(pos>=records.size()){
                return;
            }
            if(records.get(pos).isFrist()&&pos%4==0){
                c.drawText(records.get(pos).getDateStr(),parent.getChildAt(i).getLeft()+ ScreenUtil.最小DIMEN*2,parent.getChildAt(i).getTop()-ScreenUtil.字宽度*3,paint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if(pos>=records.size()){
            return;
        }
        if(records.get(pos).isFrist()){
            outRect.top = (int) (ScreenUtil.最小DIMEN*20);
        }else{
            outRect.top = 0;
        }
    }
}
