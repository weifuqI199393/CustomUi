package com.ui.lzs.viewEvent;

import android.view.View;

/**
 * Created by FuQi on 2016/1/5.
 */
public class optionClick {


    public static interface onclickListene{
        public void onclick(int position, View v, Object value);
    }

    public static interface dataNotice{
        public void data2change();
    }






}
