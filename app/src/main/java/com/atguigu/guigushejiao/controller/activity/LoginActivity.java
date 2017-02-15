package com.atguigu.guigushejiao.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.atguigu.guigushejiao.MainActivity;
import com.atguigu.guigushejiao.R;
import com.atguigu.guigushejiao.mode.Model;
import com.atguigu.guigushejiao.mode.bean.UserInfo;
import com.atguigu.guigushejiao.utils.ShowToast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/*
登录界面
 */
public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.login_et_username)
    EditText loginEtUsername;
    @InjectView(R.id.login_et_password)
    EditText loginEtPassword;
    @InjectView(R.id.login_btn_register)
    Button loginBtnRegister;
    @InjectView(R.id.login_btn_login)
    Button loginBtnLogin;
    private String passName;
    private String passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.login_btn_register, R.id.login_btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_register:
                ShowToast.show(this, "注冊");
//                判断是否注册过
                if (validate()) {
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(passName, passWord);
                                ShowToast.showUI(LoginActivity.this, "註冊成功");
                            } catch (HyphenateException e) {
                                e.printStackTrace();
                                ShowToast.showUI(LoginActivity.this, "註冊失敗" + e.getMessage());
                            }
                        }
                    });


                }
                break;
            case R.id.login_btn_login:
                ShowToast.show(this, "登陸");
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (passName != null && passWord != null) {
                            EMClient.getInstance().login(passName, passWord, new EMCallBack() {
                                @Override
                                public void onSuccess() {
//                                需要做的处理
                                    Model.getInstance().loginOnSuccess(EMClient.getInstance().getCurrentUser());
//                                保存客戶数据
                                    Model.getInstance().getUserAccountDao().addAccount(new UserInfo(EMClient.getInstance().getCurrentUser()));

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(int i, String s) {
                                    ShowToast.showUI(LoginActivity.this, s);
                                }

                                @Override
                                public void onProgress(int i, String s) {

                                }
                            });
                        }

                    }
                });
                break;
        }
    }

    private boolean validate() {
        passName = loginEtUsername.getText().toString().trim();
        passWord = loginEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passName) || TextUtils.isEmpty(passWord)) {
            ShowToast.show(this, "账号或密码不能为空");
            return false;
        }
        return true;
    }
}
