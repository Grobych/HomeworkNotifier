package com.globa.homeworknotifier.util

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.globa.homeworknotifier.interfaces.RecyclerViewClickListener


class TaskTouchListener : RecyclerView.OnItemTouchListener {
    private var mListener: RecyclerViewClickListener? = null

    var mGestureDetector: GestureDetector? = null

    constructor(context: Context?, listener: RecyclerViewClickListener?) {
        mListener = listener
        mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView: View? = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector!!.onTouchEvent(e)) {
            mListener!!.recyclerViewListClicked(childView, view.getChildAdapterPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("Not yet implemented")
    }
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}