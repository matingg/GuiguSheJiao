package com.atguigu.guigushejiao.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by 麻少亭 on 2017/2/14.
 */

public class ShowToast {

    public  static  void show(Activity activity , String string){
        Toast.makeText(activity,string,Toast.LENGTH_SHORT).show();
    }

    public  static  void showUI(final Activity activity , final String string){
        if(activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show(activity,string);
            }
        });
    }
}
