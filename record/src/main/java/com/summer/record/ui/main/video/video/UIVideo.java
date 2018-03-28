package com.summer.record.ui.main.video.video;

//by summer on 2018-03-28.

import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.SystemUtil;
import com.summer.record.data.video.Video;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UIVideo extends Video{

    private String dateStr;

    private boolean frist = false;

    public UIVideo(int id, String name, String resolution, long duration, long date, String path) {
        super(id, name, resolution, duration, date, path);
        Date d=new Date(date*1000);
        DateFormat df=new SimpleDateFormat(DateFormatUtil.YYYY_MM_DD);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateStr = df.format(d);
        LogUtil.E(dateStr);
    }
}
