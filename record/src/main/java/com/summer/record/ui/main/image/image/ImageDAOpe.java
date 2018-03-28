package com.summer.record.ui.main.image.image;

//by summer on 2018-03-27.

import android.content.Context;
import android.os.AsyncTask;

import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.record.tool.FileTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class ImageDAOpe extends BaseDAOpe {



    public void getImages(final Context context, final OnFinishWithObjI i){
        long a = System.currentTimeMillis();
        new AsyncTask<String, String, ArrayList<UIImage>>() {
            @Override
            protected ArrayList<UIImage> doInBackground(String... strings) {
                ArrayList<UIImage> images =  FileTool.getImages(context);
                HashMap<String,ArrayList<UIImage>> map = new HashMap<>();
                for(int i=0;i<images.size();i++){
                    String date = images.get(i).getDateStr();
                    if(map.get(date)==null){
                        map.put(date,new ArrayList<UIImage>());
                    }
                    map.get(date).add(images.get(i));
                }
                String[] strs = new String[map.keySet().size()];
                strs = map.keySet().toArray(strs);
                for(int i=0;i<strs.length;i++){
                    ArrayList<UIImage> videos1 = map.get(strs[i]);
                    if(videos1.size()%4!=0){
                        int left = videos1.size()%4;
                        for(int j=0;j<4-left;j++){
                            map.get(strs[i]).add(new UIImage(-1,null,null,"","",map.get(strs[i]).get(0).getDate(),null));
                        }
                    }
                }
                ArrayList<UIImage> v = new ArrayList<>();
                strs = map.keySet().toArray(strs);
                ArrayList<Long> timestr = new ArrayList<>();
                for(int i=0;i<strs.length;i++){
                    try {
                        SimpleDateFormat format = new SimpleDateFormat(DateFormatUtil.YYYY_MM_DD);
                        format.setTimeZone(TimeZone.getTimeZone("UTC"));
                        timestr.add(format.parse(strs[i]).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                for(int i=0;i<timestr.size();i++){
                    for(int j=0;j<timestr.size()-1-i;j++){
                        if(timestr.get(j)>timestr.get(j+1)){
                            long l = timestr.get(j+1);
                            timestr.set(j+1,timestr.get(j));
                            timestr.set(j,l);
                        }
                    }
                }
                ArrayList<String> ss = new ArrayList<>();
                for(int i=timestr.size()-1;i>=0;i--){
                    Date d=new Date(timestr.get(i));
                    DateFormat df=new SimpleDateFormat(DateFormatUtil.YYYY_MM_DD);
                    df.setTimeZone(TimeZone.getTimeZone("UTC"));
                    ss.add(df.format(d));
                }

                for(int i=0;i<ss.size();i++){
                    for(int j=0;j<4;j++){
                        map.get(ss.get(i)).get(j).setFrist(true);
                    }
                    v.addAll(map.get(ss.get(i)));
                }
                return v;
            }

            @Override
            protected void onPostExecute(ArrayList<UIImage> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }

}
