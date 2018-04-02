package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.GlideApp;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.StringUtil;
import com.summer.record.BR;
import com.summer.record.R;
import com.summer.record.data.Record;
import com.summer.record.databinding.FragMainVideoBinding;
import com.summer.record.databinding.ItemVideoVideoBinding;

import java.util.ArrayList;

public class VideoUIOpe extends BaseUIOpe<FragMainVideoBinding> {


    public void loadVideos(final ArrayList<Record> videos, ViewListener listener){

        bind.recycle.setLayoutManager(new GridLayoutManager(context,4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_video_video, BR.item_video_video,videos,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ItemVideoVideoBinding itemVideoVideoBinding = (ItemVideoVideoBinding) holder.viewDataBinding;
                itemVideoVideoBinding.getRoot().setTag(com.android.lib.R.id.data, this.list.get(position));
                itemVideoVideoBinding.getRoot().setTag(com.android.lib.R.id.position, Integer.valueOf(position));
                itemVideoVideoBinding.setVariable(this.vari, this.list.get(position));
                itemVideoVideoBinding.executePendingBindings();
                if(!videos.get(position).isNull()){
                    itemVideoVideoBinding.getRoot().setOnClickListener(this);
                    itemVideoVideoBinding.getRoot().setClickable(true);
                }else{
                    itemVideoVideoBinding.getRoot().setClickable(false);
                }
                GlideApp.with(context).asBitmap().centerCrop().thumbnail(0.1f).load(videos.get(position).isNull()?R.color.white:videos.get(position).getUri()).into(itemVideoVideoBinding.ivVideo);
                itemVideoVideoBinding.getRoot().setAlpha(1f);
                switch (videos.get(position).getStatus()){
                    case Record.本地无服务器有:
                        itemVideoVideoBinding.getRoot().setAlpha(0.3f);
                        break;
                    case Record.本地有服务器无:

                        break;
                    case Record.本地有服务器有:

                        break;
                    default:
                        break;
                }
            }
        });
        final Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(ScreenUtil.字宽度*18);
        paint.setAntiAlias(true);
        bind.recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                for(int i=0;i<parent.getChildCount();i++){
                    int pos = parent.getChildAdapterPosition(parent.getChildAt(i));
                    if(videos.get(pos).isFrist()&&pos%4==0){
                        c.drawText(videos.get(pos).getDateStr(),parent.getChildAt(i).getLeft(),parent.getChildAt(i).getTop()-ScreenUtil.字宽度*3,paint);
                    }
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = parent.getChildAdapterPosition(view);
                if(videos.get(pos).isFrist()){
                    outRect.top = (int) (ScreenUtil.最小DIMEN*20);
                }else{
                    outRect.top = 0;
                }
            }
        });
    }

    public void updateTitle(Object o){
        bind.recordtitle.tvLab.setText(StringUtil.getStr(o));
    }
}
