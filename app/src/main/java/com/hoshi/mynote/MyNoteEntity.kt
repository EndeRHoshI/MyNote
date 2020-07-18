package com.hoshi.mynote

/**
 * 小记事本实体类，包含标题、内容和创建时间
 */
data class MyNoteEntity(
  val title: String,
  val content: String,
  val createTime: Long
)
