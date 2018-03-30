package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.summer.record.R;
import com.summer.record.data.video.Record;
import com.summer.record.ui.main.main.MainValue;
import com.summer.record.ui.main.video.videoplay.VideoPlayFrag;

import java.util.ArrayList;

import butterknife.OnClick;

public class VideoFrag extends BaseUIFrag<VideoUIOpe,VideoDAOpe> implements ViewListener{

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getD().getVideos(getBaseAct(), new OnFinishWithObjI() {
            @Override
            public void onNetFinish(Object o) {
                getP().getD().setVideos((ArrayList<Record>) o);
                getP().getU().loadVideos(getP().getD().getVideos(),VideoFrag.this);
            }
        });
    }

    @OnClick({R.id.iv_add,R.id.tv_refresh,R.id.tv_upload})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_add:

                break;
            case R.id.tv_refresh:
                getP().getD().updateVideos(getBaseAct(), getP().getD().getNoNullVideos(), new UINetAdapter<BaseBean>(getBaseUIFrag()) {
                    @Override
                    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                        super.onNetFinish(haveData, url, baseResBean);
                        LogUtil.E("onNetFinish");
                    }
                });
                break;
            case R.id.tv_upload:
                v.setSelected(!v.isSelected());
                getP().getD().setIndex(0);
                if(!v.isSelected()){
                    getP().getD().setIndex(getP().getD().getNoNullVideos().size());
                    return;
                }
                getP().getD().uploadVideos(getBaseUIAct(), getP().getD().getNoNullVideos(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        LogUtil.E(o);
                    }
                });
                break;
        }
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.视频, VideoPlayFrag.getInstance((Record) view.getTag(R.id.data)));
                break;
        }
    }
}
