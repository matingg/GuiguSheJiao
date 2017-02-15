package com.atguigu.guigushejiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.guigushejiao.controller.fragment.ContactListFragment;
import com.atguigu.guigushejiao.controller.fragment.ConverSitionFragment;
import com.atguigu.guigushejiao.controller.fragment.SettingFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_ft_ft)
    FrameLayout mainFtFt;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    private Fragment contactListFragment;
    private Fragment converSitionFragment;
    private Fragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initDate();
        initListener();
//        switchFragment(R.id.rb_main_contact);
        rgMain.check(R.id.rb_main_contact);

    }


//private List<BaseFragment>datas ;
    private void initDate() {

        contactListFragment = new ContactListFragment();
        converSitionFragment = new ConverSitionFragment();
        settingFragment = new SettingFragment();


    }
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment (checkedId);
            }
        });

    }

    private void switchFragment(int checkedId) {
        Fragment fragment = null;
        switch (checkedId){
//            会话
            case R.id.rb_main_chat :
            fragment = converSitionFragment ;

                break;
//            联系人
            case R.id.rb_main_contact :
                fragment = contactListFragment ;


                break;
//            设置
            case R.id.rb_main_setting :
                fragment = settingFragment ;




                break;
        }
        if(fragment == null) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_ft_ft , fragment)
                .commit();
    }

}
