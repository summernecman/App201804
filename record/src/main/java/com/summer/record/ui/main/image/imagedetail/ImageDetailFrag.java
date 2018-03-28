package com.summer.record.ui.main.image.imagedetail;

//by summer on 2018-03-28.

import android.os.Bundle;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.summer.record.data.image.Image;

import java.util.ArrayList;

public class ImageDetailFrag extends BaseUIFrag<ImageDetailUIOpe,ImageDetailDAOpe> {


    public static ImageDetailFrag getInstance(ArrayList<Image> images){
        ImageDetailFrag imageDetailFrag = new ImageDetailFrag();
        imageDetailFrag.setArguments(new Bundle());
        imageDetailFrag.getArguments().putSerializable(ValueConstant.DATA_DATA,images);

        return imageDetailFrag;

    }

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initImages((ArrayList<Image>) getArguments().getSerializable(ValueConstant.DATA_DATA));
    }
}
