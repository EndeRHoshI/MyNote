package com.hoshi.mynote

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(
  context: Context,
  name: String,
  factory: SQLiteDatabase.CursorFactory?,
  version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL(CREATE_TEXT)
  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

  companion object {
    const val CREATE_TEXT = ("create table text ("
        + "_id integer primary key autoincrement, "
        + "title text, "
        + "content text, "
        + "createTime text)")
  }
}
