package com.summer.record.ui.main.main;

//by summer on 2018-03-27.

import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.fragment.two.FragManager2;
import com.summer.record.ui.main.image.ImageFrag;
import com.summer.record.ui.main.sett.SettFrag;
import com.summer.record.ui.main.text.TextFrag;
import com.summer.record.ui.main.video.VideoFrag;

public class MainAct extends BaseUIActivity<MainUIOpe,MainDAOpe> implements OnAppItemSelectListener {



    @Override
    protected void initNow() {
        super.initNow();
        FragManager2.getInstance().start(this,MainValue.视频,MainValue.视频ID,new VideoFrag());
//        FragManager2.getInstance().start(this,MainValue.图片,MainValue.图片ID,new ImageFrag());
//        FragManager2.getInstance().start(this,MainValue.文字,MainValue.文字ID,new TextFrag());
//        FragManager2.getInstance().start(this,MainValue.设置,MainValue.设置ID,new SettFrag());
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int i) {
        getP().getU().showView(i);
    }
}
