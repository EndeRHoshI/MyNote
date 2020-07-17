package com.hoshi.mynote

import android.view.View

fun View.fillVisible(visible: Boolean) {
  this.visibility = if (visible) {
    View.VISIBLE
  } else {
    View.GONE
  }
}