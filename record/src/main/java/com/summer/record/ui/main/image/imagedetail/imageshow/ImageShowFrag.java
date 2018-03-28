package com.summer.record.ui.main.image.imagedetail.imageshow;

//by summer on 2018-03-28.

import android.os.Bundle;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.ValueConstant;
import com.summer.record.data.image.Image;

public class ImageShowFrag extends BaseUIFrag<ImageShowUIOpe,ImageShowDAOpe> {

    public static ImageShowFrag getInstance(Image image){
        ImageShowFrag imageShowFrag = new ImageShowFrag();
        imageShowFrag.setArguments(new Bundle());
        imageShowFrag.getArguments().putSerializable(ValueConstant.DATA_DATA,image);

        return imageShowFrag;
    }

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().showImage((Image) getArguments().getSerializable(ValueConstant.DATA_DATA));
    }
}
