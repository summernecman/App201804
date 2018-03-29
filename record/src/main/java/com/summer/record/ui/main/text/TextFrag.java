package com.summer.record.ui.main.text;

//by summer on 2018-03-27.

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.summer.record.data.text.Text;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TextFrag extends BaseUIFrag<TextUIOpe,TextDAOpe>{

    @Override
    public void initdelay() {
        super.initdelay();
        ArrayList<Text> list = (ArrayList<Text>) new Select().from(Text.class).queryList();
        getP().getU().initTexts(list);
    }

    @Override
    protected boolean is注册事件总线() {
        return true;
    }


    @Override
    public void dealMesage(MessageEvent event) {
        LogUtil.E("TextDB");
        ArrayList<Text> list = (ArrayList<Text>) new Select().from(Text.class).queryList();
        getP().getU().initTexts(list);
    }

}
