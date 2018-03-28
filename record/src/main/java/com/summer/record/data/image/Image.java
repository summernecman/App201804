package com.summer.record.data.image;

//by summer on 2018-03-28.

import android.net.Uri;

import com.android.lib.bean.BaseBean;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image extends BaseBean {

    private int id;

    private String name;

    private String type;

    private String width;

    private String height;

    private long date;

    private String path;

    private Uri uri;

    public Image(int id, String name, String type, String width, String height, long date, String path) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.width = width;
        this.height = height;
        this.date = date;
        this.path = path;
        if(path!=null){
            uri = Uri.fromFile(new File(path));
        }
    }
}
