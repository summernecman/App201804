package com.summer.record.ui.main.image.image;

//by summer on 2018-03-27.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishWithObjI;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.two.FragManager2;
import com.summer.record.R;
import com.summer.record.data.image.Image;
import com.summer.record.ui.main.image.imagedetail.ImageDetailFrag;
import com.summer.record.ui.main.main.MainValue;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageFrag  extends BaseUIFrag<ImageUIOpe,ImageDAOpe> implements ViewListener{

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getD().getImages(getBaseAct(), new OnFinishWithObjI() {
            @Override
            public void onNetFinish(Object o) {
                getP().getU().loadImages(getP().getD().getImages(),ImageFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.图片,ImageDetailFrag.getInstance(getP().getD().getImages(), (Integer) view.getTag(R.id.position)));
                break;
        }
    }
}
