package com.summer.record.app;

//by summer on 2018-03-28.

import com.android.lib.aplication.LibAplication;

public class RecordApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    @Override
    public void initImagePicker() {

    }
}
