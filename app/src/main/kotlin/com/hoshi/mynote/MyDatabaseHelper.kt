package com.hoshi.mynote

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 小记事本数据库工具类
 */
class MyDatabaseHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    // 重写该方法，创建数据库
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TEXT)
    }

    // 重写该方法，实现数据库版本号提升后的处理逻辑
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {

        /**
         * 创建数据库的 SQL 语句
         */
        const val CREATE_TEXT = ("create table text ("
                + "_id integer primary key autoincrement, "
                + "title text, "
                + "content text, "
                + "createTime text)")
    }
}
