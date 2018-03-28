package com.summer.record.data.text;

//by summer on 2018-03-28.

import com.android.lib.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Text extends BaseBean {

    private String text;

    private long date;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
