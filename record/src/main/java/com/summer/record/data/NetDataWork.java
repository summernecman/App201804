package com.summer.record.data;

//by summer on 2018-03-30.

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;

import org.xutils.common.util.KeyValue;

import java.util.List;

public class NetDataWork {

    public static class Video{

        public static void updatevideos(Context context, BaseBean baseBean, NetI<BaseBean> adapter){
            NetGet.postData(context,RecordURL.获取地址("/video/updatevideos"),baseBean,adapter);
        }


        public static void uploadvideos(Context context, List<KeyValue> list, NetI<BaseBean> adapter){
            NetGet.file(context,RecordURL.获取地址("/video/uploadvideos"),list,adapter);
        }

    }


}
