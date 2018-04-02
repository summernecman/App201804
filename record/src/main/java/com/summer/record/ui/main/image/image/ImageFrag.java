package com.summer.record.ui.main.image.image;

//by summer on 2018-03-27.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.summer.record.R;
import com.summer.record.data.Record;
import com.summer.record.ui.main.image.imagedetail.ImageDetailFrag;
import com.summer.record.ui.main.main.MainValue;
import com.summer.record.ui.main.record.RecordDAOpe;

import java.util.ArrayList;

import butterknife.OnClick;

public class ImageFrag  extends BaseUIFrag<ImageUIOpe,RecordDAOpe> implements ViewListener{

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getD().getImages(getBaseAct(), new OnFinishWithObjI() {
            @Override
            public void onNetFinish(Object o) {
                getP().getD().setRecords((ArrayList<Record>) o);
                getP().getU().loadImages(getP().getD().getRecords(),ImageFrag.this);
            }
        });
    }

    @OnClick({R.id.iv_add,R.id.tv_refresh,R.id.tv_upload})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_add:

                break;
            case R.id.tv_refresh:
                ArrayList<Record>  list = getP().getD().getNoNullRecords(getP().getD().getRecords());
                getP().getD().setIndex(0);
                getP().getD().updateRecordsStep(getBaseAct(), list, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if(o==null){
                            getP().getD().getAllRecords(getBaseAct(), Record.ATYPE_IMAGE,new UINetAdapter<ArrayList<Record>>(getBaseUIFrag()) {
                                @Override
                                public void onSuccess(ArrayList<Record> o) {
                                    super.onSuccess(o);
                                    getP().getD().setRecords(getP().getD().dealRecord(o));
                                    getP().getU().loadImages(getP().getD().getRecords(),ImageFrag.this);
                                }

                                @Override
                                public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                                    super.onNetFinish(haveData, url, baseResBean);
                                    getP().getU().updateTitle(baseResBean.getOther());
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.tv_upload:
                v.setSelected(!v.isSelected());
                getP().getD().setIndex(0);
                if(!v.isSelected()){
                    getP().getD().setIndex(getP().getD().getNoNullRecords(getP().getD().getRecords()).size());
                    return;
                }
                getP().getD().uploadRecords(getBaseUIAct(), getP().getD().getNoNullRecords(getP().getD().getRecords()), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        LogUtil.E(o);
                    }
                });
                break;
        }
    }


    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.图片,ImageDetailFrag.getInstance(getP().getD().getRecords(), (Integer) view.getTag(R.id.position)));
                break;
        }
    }
}
