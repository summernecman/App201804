package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.GlideApp;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.StringUtil;
import com.summer.record.BR;
import com.summer.record.R;
import com.summer.record.data.Record;
import com.summer.record.databinding.FragMainVideoBinding;
import com.summer.record.databinding.ItemVideoVideoBinding;

import java.util.ArrayList;
import java.util.List;

public class VideoUIOpe extends BaseUIOpe<FragMainVideoBinding> {


    public void loadVideos(final ArrayList<Record> videos, ViewListener listener){

        bind.recycle.setLayoutManager(new GridLayoutManager(context,4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_video_video, BR.item_video_video,videos,listener){

            @Override
            public void onBindViewHolder(@NonNull AppViewHolder holder, int position, @NonNull List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                if(payloads==null||payloads.size()==0){
                    onBindViewHolder(holder,position);
                }else{
                    ItemVideoVideoBinding itemVideoVideoBinding = (ItemVideoVideoBinding) holder.viewDataBinding;
                    if(videos.get(position).isDoing()){
                        itemVideoVideoBinding.ivUpload.setVisibility(View.VISIBLE);
                        LogUtil.E("doing");
                    }else{
                        itemVideoVideoBinding.ivUpload.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ItemVideoVideoBinding itemVideoVideoBinding = (ItemVideoVideoBinding) holder.viewDataBinding;
                itemVideoVideoBinding.getRoot().setTag(com.android.lib.R.id.data, this.list.get(position));
                itemVideoVideoBinding.getRoot().setTag(com.android.lib.R.id.position, Integer.valueOf(position));
                itemVideoVideoBinding.setVariable(this.vari, this.list.get(position));
                itemVideoVideoBinding.executePendingBindings();
                GlideApp.with(context).asBitmap().centerCrop().thumbnail(0.1f).placeholder(Color.WHITE).load(videos.get(position).isNull()?R.color.white:videos.get(position).getUri()).into(itemVideoVideoBinding.ivVideo);
                if(!videos.get(position).isNull()){
                    itemVideoVideoBinding.getRoot().setOnClickListener(this);
                    itemVideoVideoBinding.getRoot().setClickable(true);
                    itemVideoVideoBinding.getRoot().setAlpha(1f);
                    itemVideoVideoBinding.bg.setBackgroundColor(Color.WHITE);
                    switch (videos.get(position).getStatus()){
                        case Record.本地无服务器有:
                            itemVideoVideoBinding.getRoot().setAlpha(0.3f);
                            break;
                        case Record.本地有服务器无:
                            itemVideoVideoBinding.bg.setBackgroundColor(Color.RED);
                            break;
                        case Record.本地有服务器有:

                            break;
                        default:
                            break;
                    }
                }else{
                    itemVideoVideoBinding.getRoot().setBackgroundColor(Color.WHITE);
                    itemVideoVideoBinding.bg.setBackgroundColor(Color.WHITE);
                    itemVideoVideoBinding.getRoot().setClickable(false);
                }

                if(videos.get(position).isDoing()){
                    itemVideoVideoBinding.ivUpload.setVisibility(View.VISIBLE);
                    LogUtil.E("doing");
                }else{
                    itemVideoVideoBinding.ivUpload.setVisibility(View.GONE);
                }
            }
        });
        bind.recycle.addItemDecoration(new VideoItemDecoration(videos));
    }

    public void updateTitle(Object o){
        bind.recordtitle.tvLab.setText(StringUtil.getStr(o));
    }

    public void scrollToPos(ArrayList<Record> records,Record record){
        LogUtil.E(record.getId());
        GridLayoutManager gridLayoutManager = (GridLayoutManager) bind.recycle.getLayoutManager();
        gridLayoutManager.scrollToPositionWithOffset(record.getId(),0);
        for(int i=0;i<records.size();i++){
            record.setDoing(false);
        }
        records.get(record.getId()).setDoing(true);
        bind.recycle.getAdapter().notifyItemChanged(record.getId(),record);
    }
}
