package com.summer.record.ui.main.image.imagedetail;

//by summer on 2018-03-28.

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.record.ui.main.image.image.UIImage;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDetailDAOpe extends BaseDAOpe {

    ArrayList<UIImage> images;
}
