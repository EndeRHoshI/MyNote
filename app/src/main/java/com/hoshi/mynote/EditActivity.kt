package com.hoshi.mynote

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit)

    val dbHelper = MyDatabaseHelper(this, "TextStore.db", null, 1)

    val intent = intent
    val createTime = intent.getLongExtra("createTime", 0L).toString()

    tv_create_time.text = createTime

    iv_back.setOnClickListener {
      val db: SQLiteDatabase = dbHelper.writableDatabase
      val values = ContentValues()
      values.put("title", et_title.text.toString())
      values.put("content", et_content.text.toString())
      values.put("createTime", createTime)

      val titleStr = et_title.text.toString()
      val contentStr = et_content.text.toString()

      if (titleStr.isNotEmpty() || contentStr.isNotEmpty()) {
        db.insert("Text", null, values)
      }
      values.clear()
      finish()
    }
  }
}