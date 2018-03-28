package com.summer.record.ui.main.image.image;

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
import com.summer.record.BR;
import com.summer.record.R;
import com.summer.record.databinding.FragMainImageBinding;
import com.summer.record.databinding.ItemImageImageBinding;

import java.util.ArrayList;

public class ImageUIOpe extends BaseUIOpe<FragMainImageBinding> {


    public void loadImages(final ArrayList<UIImage> images, ViewListener listener){

        bind.recycle.setLayoutManager(new GridLayoutManager(context,4));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_image_image, BR.item_image_image,images,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemImageImageBinding itemImageImageBinding = (ItemImageImageBinding) holder.viewDataBinding;
                if(images.get(position).getId()!=-1){
                    GlideApp.with(context).asBitmap().centerCrop().load(images.get(position).getUri()).into(itemImageImageBinding.ivImage);
                }else{
                    GlideApp.with(context).asBitmap().centerCrop().load(R.color.black).into(itemImageImageBinding.ivImage);
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
                    if(images.get(pos).isFrist()&&pos%4==0){
                        c.drawText(images.get(pos).getDateStr(),parent.getChildAt(i).getLeft(),parent.getChildAt(i).getTop()-ScreenUtil.字宽度*3,paint);
                    }
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = parent.getChildAdapterPosition(view);
                if(images.get(pos).isFrist()){
                    outRect.top = (int) (ScreenUtil.最小DIMEN*20);
                }else{
                    outRect.top = 0;
                }
            }
        });
    }
}
