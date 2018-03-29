package com.summer.record.ui.main.video.videoplay;

//by summer on 2018-03-28.

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.android.lib.GlideApp;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.summer.record.R;
import com.summer.record.data.video.Video;
import com.summer.record.databinding.FragMainVideoVideoplayBinding;

import java.io.File;

public class VideoPlayUIOpe extends BaseUIOpe<FragMainVideoVideoplayBinding> {

    public void play(Video video) {
        bind.videoplayer.setUp(video.getPath(), false, new File(""), "视频播放");
        //外部辅助的旋转，帮助全屏
        final OrientationUtils orientationUtils = new OrientationUtils((Activity) context, bind.videoplayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        bind.videoplayer.setIsTouchWiget(true);
        bind.videoplayer.setIsTouchWigetFull(false);
        bind.videoplayer.getBackButton().setVisibility(View.GONE);
        bind.videoplayer.getTitleTextView().setVisibility(View.GONE);

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bind.videoplayer.setThumbImageView(imageView);
        Glide.with(context).setDefaultRequestOptions(
                new RequestOptions()
                        .frame(3000000)
                        .centerCrop())
                .load(video.getPath())
                .into(imageView);

        //关闭自动旋转
        bind.videoplayer.setRotateViewAuto(false);
        bind.videoplayer.setLockLand(true);
        bind.videoplayer.setShowFullAnimation(true);
        bind.videoplayer.setNeedLockFull(true);
        bind.videoplayer.setSeekRatio(1);
        bind.videoplayer.setRotateWithSystem(true);
        //detailPlayer.setOpenPreView(false);
        bind.videoplayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                bind.videoplayer.startWindowFullscreen(context, true, true);
            }
        });
        //bind.videoplayer.setUp(videoBean.getFile(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoBean.getCreated());
        //bind.videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");

    }
}
