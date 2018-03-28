package com.summer.record.ui.main.text;

//by summer on 2018-03-27.

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.record.data.text.Text;

import java.util.ArrayList;

public class TextDAOpe extends BaseDAOpe {


    public ArrayList<Text> getTexts(){
        ArrayList<Text> texts = new ArrayList<>();
        for(int i=0;i<100;i++){
            texts.add(new Text("public final Cursor query (Uri uri, String[] projection,String selection,String[] selectionArgs, String sortOrder)\n" +
                    "第一个参数，uri，上面我们提到了Android提供内容的叫Provider，那么在Android中怎么区分各个Provider？有提供联系人的，有提供图片的等等。所以就需要有一个唯一的标识来标识这个Provider，Uri就是这个标识，android.provider.ContactsContract.Contacts.CONTENT_URI就是提供联系人的内容提供者，可惜这个内容提供者提供的数据很少。"));
        }
        return texts;
    }
}
