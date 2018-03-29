package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.content.Context;
import android.os.AsyncTask;

import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.record.data.video.VideoDB;
import com.summer.record.tool.FileTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDAOpe extends BaseDAOpe {

    private ArrayList<UIVideo> videos;


    public ArrayList<UIVideo> ddd(final Context context){
        ArrayList<UIVideo> videos =  FileTool.getVideos(context);
        for(int i=0;i<videos.size();i++){
            VideoDB videoDB = new VideoDB();
            videoDB.date = videos.get(i).getDate();
            videoDB.dateStr = videos.get(i).getDateStr();
            videoDB.duration = videos.get(i).getDuration();
            videoDB.name = videos.get(i).getName();
            videoDB.path = videos.get(i).getPath();
            videoDB.resolution = videos.get(i).getResolution();
            videoDB.save();
        }
        return videos;
    }
    public void getVideos(final ArrayList<UIVideo> videos, final OnFinishWithObjI i){
        long a = System.currentTimeMillis();
        new AsyncTask<String, String, ArrayList<UIVideo>>() {
            @Override
            protected ArrayList<UIVideo> doInBackground(String... strings) {
                HashMap<String,ArrayList<UIVideo>> map = new HashMap<>();
                for(int i=0;i<videos.size();i++){
                    String date = videos.get(i).getDateStr();
                    if(map.get(date)==null){
                        map.put(date,new ArrayList<UIVideo>());
                    }
                    map.get(date).add(videos.get(i));
                }
                String[] strs = new String[map.keySet().size()];
                strs = map.keySet().toArray(strs);
                for(int i=0;i<strs.length;i++){
                    ArrayList<UIVideo> videos1 = map.get(strs[i]);
                    if(videos1.size()%4!=0){
                        int left = videos1.size()%4;
                        for(int j=0;j<4-left;j++){
                            map.get(strs[i]).add(new UIVideo(-1,null,null,0,map.get(strs[i]).get(0).getDate(),null));
                        }
                    }
                }
                ArrayList<UIVideo> v = new ArrayList<>();
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
            protected void onPostExecute(ArrayList<UIVideo> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }

    public void getVideos(final Context context, final OnFinishWithObjI i){
        long a = System.currentTimeMillis();
        new AsyncTask<String, String, ArrayList<UIVideo>>() {
            @Override
            protected ArrayList<UIVideo> doInBackground(String... strings) {
                ArrayList<UIVideo> videos =  FileTool.getVideos(context);
                HashMap<String,ArrayList<UIVideo>> map = new HashMap<>();
                for(int i=0;i<videos.size();i++){
                    String date = videos.get(i).getDateStr();
                    if(map.get(date)==null){
                        map.put(date,new ArrayList<UIVideo>());
                    }
                    map.get(date).add(videos.get(i));
                }
                String[] strs = new String[map.keySet().size()];
                strs = map.keySet().toArray(strs);
                for(int i=0;i<strs.length;i++){
                    ArrayList<UIVideo> videos1 = map.get(strs[i]);
                    if(videos1.size()%4!=0){
                        int left = videos1.size()%4;
                        for(int j=0;j<4-left;j++){
                            map.get(strs[i]).add(new UIVideo(-1,null,null,0,map.get(strs[i]).get(0).getDate(),null));
                        }
                    }
                }
                ArrayList<UIVideo> v = new ArrayList<>();
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
            protected void onPostExecute(ArrayList<UIVideo> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }
}
