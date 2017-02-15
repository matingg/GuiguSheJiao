package com.atguigu.guigushejiao.mode.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atguigu.guigushejiao.mode.bean.UserInfo;
import com.atguigu.guigushejiao.mode.db.UserAccountDB;
import com.atguigu.guigushejiao.mode.table.UserAccountTable;

/**
 * Created by 麻少亭 on 2017/2/14.
 */

// 用户账号表的操作类
public class UserAccountDao {

    private final UserAccountDB mHelper;

    public UserAccountDao(Context context) {
        mHelper = new UserAccountDB(context);
    }

    // 添加用户到数据库
    public void addAccount(UserInfo user) {

        // 获取数据库链接
        SQLiteDatabase db = mHelper.getWritableDatabase();

        // 操作数据库
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID, user.getHxId());
        values.put(UserAccountTable.COL_NAME, user.getName());
        values.put(UserAccountTable.COL_NICK, user.getNick());
        values.put(UserAccountTable.COL_PHOTO, user.getPhoto());

        db.replace(UserAccountTable.TAB_NAME, null, values);
    }

    // 根据环信id获取所有用户信息
    public UserInfo getAccountByHxId(String hxId) {

        // 获取数据库链接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 操作数据库
        String sql = "select * from " + UserAccountTable.TAB_NAME + " where " + UserAccountTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        UserInfo account = null;

        if (cursor.moveToNext()) {
            account = new UserInfo();

            account.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            account.setHxId(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            account.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            account.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }

        // 关闭资源
        cursor.close();

        // 返回数据
        return account;
    }
}