package com.hoshi.mynote

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

/**
 * 小记事本 Adapter
 */
class MyNoteAdapter(private val context: Context, private val data: MutableList<MyNoteEntity>) :
    RecyclerView.Adapter<MyNoteAdapter.MyNoteViewHolder>() {

    // 今天内文章的日期格式
    private val displayFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    // 非今天内文章的日期格式
    private val detailedFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return MyNoteViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyNoteViewHolder, position: Int) {
        val noteEntity = data.getOrNull(position) ?: return

        val date = Date(noteEntity.createTime)
        val calendar = GregorianCalendar()
        calendar.time = date
        val createDay = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.time = Date(System.currentTimeMillis())
        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)

        val title = noteEntity.title
        val content = noteEntity.content
        val createTime = displayFormat.format(date)

        holder.tvTitle.apply {
            text = title
            isVisible = title.isNotEmpty()
        }

        holder.tvContent.apply {
            text = content
            isVisible = content.isNotEmpty()
        }

        holder.tvCreateTime.text = if (createDay == currentDay) {
            createTime
        } else {
            detailedFormat.format(date)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: MutableList<MyNoteEntity>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class MyNoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
        val tvCreateTime: TextView = itemView.findViewById(R.id.tv_create_time)
    }
}
