package com.hoshi.mynote

import android.view.View

/**
 * View visible 扩展
 * @param visible true 为 View.VISIBLE，false 为 View.GONE
 */
fun View.fillVisible(visible: Boolean) {
  this.visibility = if (visible) {
    View.VISIBLE
  } else {
    View.GONE
  }
}