package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.two.FragManager2;
import com.summer.record.R;
import com.summer.record.data.video.Video;
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
                ArrayList<UIVideo> videos = (ArrayList<UIVideo>) o;
                getP().getU().loadVideos(videos,VideoFrag.this);
            }
        });
    }

    @OnClick({R.id.iv_add})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_add:

                break;
        }
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.视频, VideoPlayFrag.getInstance((Video) view.getTag(R.id.data)));
                break;
        }
    }
}
