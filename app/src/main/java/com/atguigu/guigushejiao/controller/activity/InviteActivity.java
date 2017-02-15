package com.atguigu.guigushejiao.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.guigushejiao.R;
import com.atguigu.guigushejiao.mode.Model;
import com.atguigu.guigushejiao.utils.ShowToast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class InviteActivity extends AppCompatActivity {


    @InjectView(R.id.invite_btn_search)
    Button inviteBtnSearch;
    @InjectView(R.id.invite_et_searchkuang)
    EditText inviteEtSearchkuang;
    @InjectView(R.id.invite_iv_photo)
    ImageView inviteIvPhoto;
    @InjectView(R.id.invite_tv_name)
    TextView inviteTvName;
    @InjectView(R.id.invite_btn_add)
    Button inviteBtnAdd;
    @InjectView(R.id.invite_rl_show)
    RelativeLayout inviteRlShow;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ButterKnife.inject(this);

    }

    //          搜索  和 添加    的点击事件
    @OnClick({R.id.invite_btn_search, R.id.invite_btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_btn_search:
                ShowToast.show(InviteActivity.this, "搜索");
//            搜索    验证是否为空 去服务器验证 有没有
                if (validate()) {
//                    显示搜索结果
                    inviteTvName.setText(username);
                    inviteRlShow.setVisibility(View.VISIBLE);

                } else {
                    inviteRlShow.setVisibility(View.GONE);
                }
                break;
            case R.id.invite_btn_add:
                ShowToast.show(InviteActivity.this, "添加");
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
//                        去环信服务器添加好友
                        try {
                            //参数为要添加的好友的username和添加理由
                            EMClient.getInstance().contactManager()
                                    .addContact(username, "添加理由");
                            ShowToast.showUI(InviteActivity.this, "添加好友成功");
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            ShowToast.showUI(InviteActivity.this, "添加好友失败" + e.getMessage());
                        }

                    }
                });
                break;
        }
    }




    private boolean validate() {
//          本地验证  缺少服务器验证
        username = inviteEtSearchkuang.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ShowToast.show(this, "用户名不能为空");
            Log.e("TAG","-----------------"+username+"-------------");
            return false;
        }
        Log.e("TAG","++++++++++++++++++++"+username+"++++++++++++++++++");
        return true;
    }


}
