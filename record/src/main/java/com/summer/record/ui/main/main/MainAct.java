package com.summer.record.ui.main.main;

//by summer on 2018-03-27.

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.util.system.PermissionUtil;
import com.summer.record.ui.main.image.image.ImageFrag;
import com.summer.record.ui.main.sett.SettFrag;
import com.summer.record.ui.main.text.TextFrag;
import com.summer.record.ui.main.video.video.VideoFrag;

public class MainAct extends BaseUIActivity<MainUIOpe,MainDAOpe> implements OnAppItemSelectListener {



    @Override
    protected void initNow() {
        super.initNow();

        if(!new PermissionUtil().is所有的权限都允许(this,getP().getD().getPermissions())){
            return;
        }
        LogUtil.E(System.currentTimeMillis());
        FragManager2.getInstance().start(this,MainValue.视频,MainValue.视频ID,new VideoFrag());
        FragManager2.getInstance().start(this,MainValue.图片,MainValue.图片ID,new ImageFrag());
        FragManager2.getInstance().start(this,MainValue.文字,MainValue.文字ID,new TextFrag());
        FragManager2.getInstance().start(this,MainValue.设置,MainValue.设置ID,new SettFrag());
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int i) {
        getP().getU().showView(i);
        setMoudle(MainValue.模块[i]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        new PermissionUtil().onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @Override
    public void onBackPressed() {
        LogUtil.E("onBackPressed");
        BaseUIFrag baseUIFrag = FragManager2.getInstance().getCurrentFrag(getMoudle());
        if(baseUIFrag!=null&&baseUIFrag.getFragM()!=null) {
            FragManager2 fragManager2 = baseUIFrag.getFragM();
            if (!fragManager2.finish(getActivity(), getMoudle(), true)) {//清除当前页面
                super.onBackPressed();//当前模块没有可清除界面
            }
        }
    }
}
