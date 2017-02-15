package com.atguigu.guigushejiao.mode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atguigu.guigushejiao.mode.table.UserAccountTable;

/**
 * Created by 麻少亭 on 2017/2/14.
 */

public class UserAccountDB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;

    public UserAccountDB(Context context) {
        super(context, "account.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserAccountTable.CREATE_TAB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}