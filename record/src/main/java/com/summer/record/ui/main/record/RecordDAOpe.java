package com.summer.record.ui.main.record;

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
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.summer.record.data.NetDataWork;
import com.summer.record.data.Record;
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
public class RecordDAOpe extends BaseDAOpe {

    ArrayList<Record> records;

    private int index=0;

    public void getVideos(final Context context, final OnFinishWithObjI i){
        new AsyncTask<String, String, ArrayList<Record>>() {
            @Override
            protected ArrayList<Record> doInBackground(String... strings) {
                ArrayList<Record> videos =  FileTool.getVideos(context);
                return dealRecord(videos);
            }

            @Override
            protected void onPostExecute(ArrayList<Record> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }

    public void getImages(final Context context, final OnFinishWithObjI i){
        new AsyncTask<String, String, ArrayList<Record>>() {
            @Override
            protected ArrayList<Record> doInBackground(String... strings) {
                ArrayList<Record> videos =  FileTool.getImages(context);
                return dealRecord(videos);
            }

            @Override
            protected void onPostExecute(ArrayList<Record> videos) {
                super.onPostExecute(videos);
                i.onNetFinish(videos);
            }
        }.execute();
    }



    public static ArrayList<Record> dealRecord(ArrayList<Record> videos){
        HashMap<String,ArrayList<Record>> map = new HashMap<>();
        for(int i=0;i<videos.size();i++){
            videos.get(i).init();
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
                    map.get(strs[i]).add(new Record("",-1,null,0l,0l,map.get(strs[i]).get(0).getUtime(),null));
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



    public void updateRecords(Context context, ArrayList<Record> videos, NetI<BaseBean> adapter){
        if(videos==null){
            return;
        }
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videos));
        NetDataWork.Data.updateRecords(context,baseReqBean,adapter);
    }


    public void updateRecordsStep(final Context context, final ArrayList<Record> videos, final OnFinishListener adapter){
        final int start = getIndex()*500;
        int end = getIndex()*500+500;
        if(start>=videos.size()){
            adapter.onFinish(null);
            return;
        }
        if(end>=videos.size()){
            end = videos.size();
        }
        ArrayList<Record> list = new ArrayList<>();
        for(int i=start;i<end;i++){
            list.add(videos.get(i));
        }
        final int finalEnd = end;
        updateRecords(context, list, new UINetAdapter<BaseBean>(context) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                super.onNetFinish(haveData, url, baseResBean);
                adapter.onFinish(start+""+ finalEnd);
                setIndex(getIndex()+1);
                updateRecordsStep(context, videos, adapter);
            }
        });
    }


    public void getAllRecords(Context context,String atype, NetI<ArrayList<Record>> adapter){
        Record record = new Record();
        record.setAtype(atype);
        NetDataWork.Data.getAllRecords(context,record,adapter);
    }


    public void uploadRecord(Context context, Record record, NetI<BaseBean> adapter){
        if(record==null){
            return;
        }
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file",new File(record.getLocpath())));
        list.add(new KeyValue("locpath",record.getLocpath()));

        NetDataWork.Data.uploadRecords(context,list,adapter);
    }

    public void uploadRecords(final Context context, final ArrayList<Record> list, final OnFinishListener listener){
        if(list==null||list.size()<=getIndex()){
            return;
        }
        Record record = list.get(getIndex());
        uploadRecord(context,record,new NetAdapter<BaseBean>(context){
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                setIndex(getIndex()+1);
                listener.onFinish(getIndex());
                uploadRecords(context, list, listener);
            }
        });
    }

    public ArrayList<Record> getNoNullRecords(ArrayList<Record> list){
        ArrayList<Record> records = new ArrayList<>();
        for(int i = 0; list!=null&&i< list.size(); i++){
            if(list.get(i).getLocpath()!=null){
                records.add(list.get(i));
            }
        }
        return records;
    }
}