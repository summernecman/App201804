package com.summer.record.ui.main.main;

//by summer on 2018-03-27.

import android.view.View;

import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.summer.record.R;
import com.summer.record.databinding.ActMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding>{

    ArrayList<BottomMenuBean> bottomMenuBeans = new ArrayList<>();


    @Override
    public void initUI() {
        super.initUI();

        bottomMenuBeans.add(new BottomMenuBean("视频", R.drawable.drawable_record_main_bottom_video,null,bind.containVideo,context.getResources().getColorStateList(R.color.color_white_blue)));
        bottomMenuBeans.add(new BottomMenuBean("图片", R.drawable.drawable_record_main_bottom_image,null,bind.containImage,context.getResources().getColorStateList(R.color.color_white_blue)));
        bottomMenuBeans.add(new BottomMenuBean("文字", R.drawable.drawable_record_main_bottom_text,null,bind.containText,context.getResources().getColorStateList(R.color.color_white_blue)));
        bottomMenuBeans.add(new BottomMenuBean("设置", R.drawable.drawable_record_main_bottom_setting,null,bind.containSetting,context.getResources().getColorStateList(R.color.color_white_blue)));
        bind.bottommenu.initItems(bottomMenuBeans);
        if(context instanceof OnAppItemSelectListener){
            bind.bottommenu.setOnAppItemClickListener((OnAppItemSelectListener)context);
        }
    }

    public void showView(int pos){
       for(int i=0;i<bottomMenuBeans.size();i++){
           if(i==pos){
               bottomMenuBeans.get(i).getContainerView().setVisibility(View.VISIBLE);
           }else{
               bottomMenuBeans.get(i).getContainerView().setVisibility(View.GONE);
           }
       }
    }
}
