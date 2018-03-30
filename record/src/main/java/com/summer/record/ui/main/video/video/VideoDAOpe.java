package com.summer.record.ui.main.video.video;

//by summer on 2018-03-27.

import android.content.Context;
import android.os.AsyncTask;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetI;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.record.data.NetDataWork;
import com.summer.record.data.video.Record;
import com.summer.record.tool.FileTool;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDAOpe extends BaseDAOpe {

    ArrayList<Record> videos;

    private int index=0;

    public void getVideos(final Context context, final OnFinishWithObjI i){
        long a = System.currentTimeMillis();
        new AsyncTask<String, String, ArrayList<Record>>() {
            @Override
            protected ArrayList<Record> doInBackground(String... strings) {
                ArrayList<Record> videos =  FileTool.getVideos(context);
                HashMap<String,ArrayList<Record>> map = new HashMap<>();
                for(int i=0;i<videos.size();i++){
                    String date = videos.get(i).getDateStr();
                    if(map.get(date)==null){
                        map.put(date,new ArrayList<Record>());
                    }
                    map.get(date).add(videos.get(i));
                }
                String[] strs = new String[map.keySet().size()];
                strs = map.keySet().toArray(strs);
                for(int i=0;i<strs.length;i++){
                    ArrayList<Record> videos1 = map.get(strs[i]);
                    if(videos1.size()%4!=0){
                        int left = videos1.size()%4;
                        for(int j=0;j<4-left;j++){
                            map.get(strs[i]).add(new Record(-1,null,0l,0l,map.get(strs[i]).get(0).getUtime(),null));
                        }
                    }
                }
                ArrayList<Record> v = new ArrayList<>();
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
            protected void onPostExecute(ArrayList<Record> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }

    public void updateVideos(Context context, ArrayList<Record> videos, NetI<BaseBean> adapter){
        if(videos==null){
            return;
        }
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videos));
        NetDataWork.Video.updatevideos(context,baseReqBean,adapter);
    }

    public void uploadVideo(Context context, Record record, NetI<BaseBean> adapter){
        if(record==null){
            return;
        }
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file",new File(record.getLocpath())));
        list.add(new KeyValue("locpath",record.getLocpath()));

        NetDataWork.Video.uploadvideos(context,list,adapter);
    }

    public void uploadVideos(final Context context, final ArrayList<Record> list, final OnFinishListener listener){
        if(list==null||list.size()<=getIndex()){
            return;
        }
        Record record = list.get(getIndex());
        uploadVideo(context,record,new NetAdapter<BaseBean>(context){
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                setIndex(getIndex()+1);
                listener.onFinish(getIndex());
                uploadVideos(context, list, listener);
            }
        });
    }

    public ArrayList<Record> getNoNullVideos(){
        ArrayList<Record> records = new ArrayList<>();
        for(int i=0;getVideos()!=null&&i<getVideos().size();i++){
            if(getVideos().get(i).getLocpath()!=null){
                records.add(getVideos().get(i));
            }
        }
        return records;
    }
}
