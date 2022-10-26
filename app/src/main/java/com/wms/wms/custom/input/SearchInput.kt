package com.wms.wms.custom.input

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.wms.wms.R


class SearchInput(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs) {
    var dClose: Drawable? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.SearchInput, 0, 0
        ).apply {

            try {
//              mShowText = getBoolean(R.layout.SearchInput_showText, false)
//              textPos = getInteger(R.styleable.SearchInput_labelPosition, 0)
            } finally {
                recycle()
            }
        }

//        layout_width="match_parent"
//        android:layout_height="40dp"
//        android:layout_margin="16dp"
//        app:boxStrokeWidth="0dp"
        hint = "Search"
        background = ContextCompat.getDrawable(context, R.drawable.bg_card)

        setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(context, R.drawable.ic_baseline_search_24_margin5),
            null,
            null,
            null
        )

        doAfterTextChanged {
            if (it.isNullOrEmpty()) {
                dClose = null
                setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_search_24_margin5),
                    null,
                    null,
                    null
                )
            } else {
                dClose = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24_margin5)
                setCompoundDrawablesWithIntrinsicBounds(
                    dClose, null, null, null
                )
            }
        }

    }

    private fun isTouchInCloseArea(event: MotionEvent): Boolean {
        if (dClose == null) {
            return false
        }
        val rBounds = dClose!!.getBounds()
        val x = event.x.toInt()
        val y = event.y.toInt()

        //check to make sure the touch event was within the bounds of the drawable
        return x <= rBounds.width() && x >= 0 && y >= 0 && y <= rBounds.height()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP && this.isTouchInCloseArea(event)) {

            //System.out.println("touch");
            this.setText("")
            event.action =
                MotionEvent.ACTION_CANCEL //use this to prevent the keyboard from coming up
        }
        return super.onTouchEvent(event)
    }

//    fun isShowText(): Boolean {
//        return mShowText
//    }
//
//    fun setShowText(showText: Boolean) {
//        mShowText = showText
//        invalidate()
//        requestLayout()
//    }
}