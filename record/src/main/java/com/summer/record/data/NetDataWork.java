package com.summer.record.data;

//by summer on 2018-03-30.

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;

import org.xutils.common.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class NetDataWork {

    public static class Data{

        public static void updateRecords(Context context, BaseBean baseBean, NetI<BaseBean> adapter){
            NetGet.postData(context,RecordURL.获取地址("/record/updateRecords"),baseBean,adapter);
        }

        public static void getAllRecords(Context context,Record record, NetI<ArrayList<Record>> adapter){
            NetGet.getData(context,RecordURL.获取地址("/record/getAllRecords"),record,adapter);
        }


        public static void uploadRecords(Context context, List<KeyValue> list, NetI<BaseBean> adapter){
            NetGet.file(context,RecordURL.获取地址("/record/uploadRecords"),list,adapter);
        }

    }




}
