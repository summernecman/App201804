package com.summer.record.ui.main.image.imagedetail.imageshow;

//by summer on 2018-03-28.

import com.android.lib.GlideApp;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.record.data.image.Image;
import com.summer.record.databinding.FragMainImageImagedetailImageBinding;

public class ImageShowUIOpe extends BaseUIOpe<FragMainImageImagedetailImageBinding> {

    public void showImage(Image image){
        GlideApp.with(context).asBitmap().centerInside().load(image.getPath()).into(bind.ivImage);

    }
}
