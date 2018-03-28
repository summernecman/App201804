package com.summer.record.ui.main.text;

//by summer on 2018-03-27.

import com.android.lib.base.fragment.BaseUIFrag;

public class TextFrag extends BaseUIFrag<TextUIOpe,TextDAOpe>{

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initTexts(getP().getD().getTexts());
    }
}
