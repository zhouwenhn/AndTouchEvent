package com.chowe.cn.androidaction;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author chowen
 * @version 0.1
 * @since 16/8/29
 */
public class MyRelativeLayout extends RelativeLayout {
    private static String TAG = MyRelativeLayout.class.getSimpleName();

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>dispatchTouchEvent>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>dispatchTouchEvent>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>dispatchTouchEvent>ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onInterceptTouchEvent>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onInterceptTouchEvent>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onInterceptTouchEvent>ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onTouchEvent>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onTouchEvent>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION>>MyRelativeLayout>>onTouchEvent>ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
