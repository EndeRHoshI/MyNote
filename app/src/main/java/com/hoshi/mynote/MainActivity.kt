package com.hoshi.mynote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 小记事本主活动，展示数据库里边的文章
 */
class MainActivity : AppCompatActivity() {

  private var adapter: MyNoteAdapter? = null
  private var dbHelper: MyDatabaseHelper? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    adapter = MyNoteAdapter(this, mutableListOf())
    dbHelper = MyDatabaseHelper(this, "TextStore.db", null, 1)

    iv_back.setOnClickListener {
      finish()
    }

    iv_edit.setOnClickListener {
      // 点击编辑按钮，记录创建时间，并且跳转到编辑活动
      val intent = Intent(this, EditActivity::class.java)
      intent.putExtra("createTime", System.currentTimeMillis())
      startActivity(intent)
    }
  }

  // 在 onResume 方法里更新页面内容
  override fun onResume() {
    super.onResume()

    val dataList = mutableListOf<MyNoteEntity>()
    val db = dbHelper?.writableDatabase ?: return
    val cursor = db.query("Text", null, null, null, null, null, null)
    if (cursor != null && cursor.moveToFirst()) {

      val titleIndex = cursor.getColumnIndex("title")
      val contentIndex = cursor.getColumnIndex("content")
      val createTimeIndex = cursor.getColumnIndex("createTime")

      do {
        dataList.add(
          MyNoteEntity(
            cursor.getString(titleIndex),
            cursor.getString(contentIndex),
            cursor.getLong(createTimeIndex)
          )
        )
      } while (cursor.moveToNext())
    }
    cursor.close()
    adapter?.updateData(dataList)
    rv_note_list.layoutManager = LinearLayoutManager(this)
    rv_note_list.adapter = adapter
  }
}
