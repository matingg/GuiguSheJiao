package com.atguigu.guigushejiao.controller.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.guigushejiao.R;
import com.atguigu.guigushejiao.controller.activity.InviteActivity;
import com.atguigu.guigushejiao.utils.ShowToast;
import com.hyphenate.easeui.ui.EaseContactListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/2/15.
 * <p>
 * <p>
 * 联系人
 */

public class ContactListFragment extends EaseContactListFragment {

    @InjectView(R.id.iv_contact_notify)
    ImageView ivContactNotify;
    @InjectView(R.id.ll_contact_invite)
    LinearLayout llContactInvite;
    @InjectView(R.id.ll_contact_group)
    LinearLayout llContactGroup;

    @Override//祖父类的虚拟方法
    protected void initView() {
        super.initView();
//        初始化头布局

        View view = View.inflate(getActivity(), R.layout.fragment_contact_header, null);
        ButterKnife.inject(this, view);
//        父类捡来的listView
        listView.addHeaderView(view);
//        父类右侧头布局添加 + 号
        titleBar.setRightImageResource(R.mipmap.em_add);
//        + 号设置监听
        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                + 号点击事件 跳转至查找布局
                ShowToast.show(getActivity(),"查找");
                Intent intent = new Intent(getContext(),InviteActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override//祖父类的虚拟方法
    protected void setUpView() {
        super.setUpView();




    }




    @OnClick({R.id.ll_contact_invite, R.id.ll_contact_group})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_contact_invite:
                ShowToast.show(getActivity(),"好友邀请");



                break;
            case R.id.ll_contact_group:
                ShowToast.show(getActivity(),"群组");





                break;
        }
    }












    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
