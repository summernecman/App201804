package com.summer.record.data.video;

//by summer on 2018-03-28.

import android.net.Uri;

import com.android.lib.bean.BaseBean;

import java.io.File;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Video extends BaseBean {

    private int id;

    private String name;

    private String resolution;

    private long duration;

    private long date;

    private String path;

    private Uri uri;

    public Video(int id, String name, String resolution, long duration, long date, String path) {
        this.id = id;
        this.name = name;
        this.resolution = resolution;
        this.duration = duration;
        this.date = date;
        this.path = path;
        if(path!=null){
            uri = Uri.fromFile(new File(path));
        }
    }
}
