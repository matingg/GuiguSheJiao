package com.atguigu.guigushejiao.mode;

import android.content.Context;

import com.atguigu.guigushejiao.mode.dao.UserAccountDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 麻少亭 on 2017/2/14.
 */

public class Model {

    private  static  Model model = new Model();
    private UserAccountDao userAccountDao;

    private Model(){}

    public static Model getInstance(){
        return  model;
    }

    private Context context;



    public void init(Context context){
        this.context = context;
        userAccountDao = new UserAccountDao(context);
    }

    public UserAccountDao getUserAccountDao(){
        return  userAccountDao;
    }

    private ExecutorService thread = Executors.newCachedThreadPool();//线程池



    public ExecutorService  getGlobalThreadPool(){//线程池


        return  thread ;
    }

    public void loginOnSuccess(String currentUser) {


    }


}
