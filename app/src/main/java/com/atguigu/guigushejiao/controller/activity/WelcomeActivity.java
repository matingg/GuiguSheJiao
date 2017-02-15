package com.atguigu.guigushejiao.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.guigushejiao.MainActivity;
import com.atguigu.guigushejiao.R;
import com.atguigu.guigushejiao.mode.Model;
import com.hyphenate.chat.EMClient;

public class WelcomeActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
//                判断是否登录过
                enterMainOrWelcom();

            }

        }
    };

    //                判断是否登录过  如果登录过跳转至主页面  没有登录过跳转至登录的页面
    protected void enterMainOrWelcom() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                boolean islogged = EMClient.getInstance().isLoggedInBefore();
                if (islogged) {
//                    登录过需要的处理
                    Model.getInstance().loginOnSuccess(EMClient.getInstance().getCurrentUser());
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.sendEmptyMessageDelayed(0, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
