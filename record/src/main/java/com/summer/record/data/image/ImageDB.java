package com.summer.record.data.image;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.summer.record.data.db.RecordDataBase;

//by summer on 2018-03-29.
@Table(database = RecordDataBase.class)
public class ImageDB extends BaseModel{

    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String name;
    @Column
    public String type;
    @Column
    public String width;
    @Column
    public String height;
    @Column
    public Long date;
    @Column
    public String path;
}
