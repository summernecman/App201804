package com.summer.record.ui.main.image.image;

//by summer on 2018-03-28.

import android.net.Uri;

import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.record.data.image.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UIImage extends Image {

    private String dateStr;

    private boolean frist = false;


    public UIImage(int id, String name, String type, String width, String height, long date, String path) {
        super(id, name, type, width, height, date, path);
        Date d=new Date(date*1000);
        DateFormat df=new SimpleDateFormat(DateFormatUtil.YYYY_MM_DD);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateStr = df.format(d);
        LogUtil.E(dateStr);
    }
}
