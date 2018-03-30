package com.summer.record.tool;

//by summer on 2018-03-28.

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.summer.record.data.video.Record;
import com.summer.record.ui.main.image.image.UIImage;

import java.io.File;
import java.util.ArrayList;

public class FileTool {

    public static ArrayList<Record> getVideos(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<Record> videos = new ArrayList<>();
        Cursor cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Video.Media.DATE_MODIFIED+" desc");
        while (cursor.moveToNext()){
            File file = new File(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
            if(!file.exists()){
                continue;
            }
            videos.add(new Record(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)),
                    file.getPath(),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED))*1000,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED))*1000,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))

                    ));
        }
        return  videos;
    }

    public static ArrayList<UIImage> getImages(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<UIImage> images = new ArrayList<>();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Images.Media.DATE_MODIFIED+" desc");
        while (cursor.moveToNext()){
            File file = new File(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
            if(!file.exists()){
                continue;
            }
            images.add(new UIImage(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED))*1000,
                    file.getPath()
            ));
        }
        return  images;
    }
}
