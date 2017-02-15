package com.atguigu.guigushejiao.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atguigu.guigushejiao.R;
import com.atguigu.guigushejiao.controller.activity.LoginActivity;
import com.atguigu.guigushejiao.mode.Model;
import com.atguigu.guigushejiao.utils.ShowToast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/2/15.
 * <p>
 * <p>
 * 设置
 */

public class SettingFragment extends Fragment {
    @InjectView(R.id.bt_setting_out)
    Button btSettingOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);

        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.bt_setting_out)
    public void onClick() {
        ShowToast.show(getActivity(),"退出登录");
//        第一步网络 第二步本地 第三步页面
//        告诉环信服务器 要退出了
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {
//                 退出成功
//                        跳转至登录页面   关闭当前页面
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                       getActivity().finish();

                    }

                    @Override
                    public void onError(int i, String s) {
//                        退出失败  吐司
                        ShowToast.show(getActivity(),"退出失败");

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

    }
}
