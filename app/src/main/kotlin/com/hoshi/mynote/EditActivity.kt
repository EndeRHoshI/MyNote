package com.hoshi.mynote

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.hoshi.mynote.databinding.ActivityEditBinding
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
        val binding = ActivityEditBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val dbHelper = MyDatabaseHelper(this, "TextStore.db", null, 1)

        val intent = intent
        val createTime = intent.getLongExtra("createTime", 0L)

        binding.tvCreateTime.text = displayFormat.format(Date(createTime))

        binding.ivBack.setOnClickListener {
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val values = ContentValues()

            // 将文章的标题、内容和创建时间写入数据库
            values.put("title", binding.etTitle.text.toString())
            values.put("content", binding.etContent.text.toString())
            values.put("createTime", createTime)

            val titleStr = binding.etTitle.text.toString()
            val contentStr = binding.etContent.text.toString()

            if (titleStr.isNotEmpty() || contentStr.isNotEmpty()) {
                db.insert("Text", null, values)
            }
            values.clear()
            finish()
        }
    }
}