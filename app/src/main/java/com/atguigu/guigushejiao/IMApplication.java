package com.atguigu.guigushejiao;

import android.app.Application;
import android.content.Context;

import com.atguigu.guigushejiao.mode.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by 麻少亭 on 2017/2/14.
 */

public class IMApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        EMOptions options = new EMOptions();

        options.setAutoAcceptGroupInvitation(false);// 不自动接受群邀请信息
        options.setAcceptInvitationAlways(false);// 不总是一直接受所有邀请

        // 初始化EaseUI
        EaseUI.getInstance().init(this, options);

        // 初始化模型层数据
        Model.getInstance().init(this);

        mContext = this;
    }

    // 获取全局上下文
    public static Context getApplication() {
        return mContext;
    }


}

