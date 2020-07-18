package com.hoshi.mynote

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 小记事本编辑活动，点击退出按钮后将文章存储到数据库
 */
class EditActivity : AppCompatActivity() {

  // 今天内文章的日期格式
  private val displayFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit)

    val dbHelper = MyDatabaseHelper(this, "TextStore.db", null, 1)

    val intent = intent
    val createTime = intent.getLongExtra("createTime", 0L)

    tv_create_time.text = displayFormat.format(Date(createTime))

    iv_back.setOnClickListener {
      val db: SQLiteDatabase = dbHelper.writableDatabase
      val values = ContentValues()

      // 将文章的标题、内容和创建时间写入数据库
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