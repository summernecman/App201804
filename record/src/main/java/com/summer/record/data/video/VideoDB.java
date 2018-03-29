package com.summer.record.data.video;

//by summer on 2018-03-29.

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.summer.record.data.db.RecordDataBase;

@Table(database = RecordDataBase.class)
public class VideoDB extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String name;
    @Column
    public String resolution;
    @Column
    public Long duration;
    @Column
    public Long date;
    @Column
    public String path;
    @Column
    public String dateStr;
}
