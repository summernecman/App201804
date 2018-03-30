package com.summer.record.data.video;

import android.net.Uri;

import com.android.lib.bean.BaseBean;
import com.android.lib.util.data.DateFormatUtil;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.summer.record.data.db.RecordDataBase;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Table(database = RecordDataBase.class)
public class Record extends BaseBean{

    public Integer id;

    public String locpath;

    public String netpath;

    public Long ctime;

    public Long utime;

    public String atype;

    public String btype;

    public String address;

    public Long duration;

    public String name;

    public String content;

    public Uri uri;

    public String dateStr;

    public boolean frist = false;

    public Record() {
    }

    public Record(Integer id, String locpath, Long ctime, Long utime, Long duration, String name) {
        this.id = id;
        this.locpath = locpath;
        this.ctime = ctime;
        this.utime = utime;
        this.duration = duration;
        this.name = name;
        if(locpath!=null){
            uri = Uri.fromFile(new File(locpath));
        }
        Date d=new Date((ctime));
        DateFormat df=new SimpleDateFormat(DateFormatUtil.YYYY_MM_DD);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateStr = df.format(d);
    }

}