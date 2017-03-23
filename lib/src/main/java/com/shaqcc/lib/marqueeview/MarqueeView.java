package com.shaqcc.lib.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.shaqcc.lib.R;


/**
 * 跑马灯效果控件
 *
 * 使用时必须在XML 文件设置自身的高度，属性为：marquee_height
 * Created by bayin on 2017/3/21.
 */

public class MarqueeView extends ListView {

    private int mHeight;
    private boolean isStart = false;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context, attrs, defStyleAttr);
    }

    /**
     * init
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MarqueeView, defStyleAttr, 0);
        mHeight = attributes.getInt(R.styleable.MarqueeView_marquee_height, 0);
        mScrollY = dip2px(mHeight);
        attributes.recycle();

        this.setDivider(null);
        this.setFastScrollEnabled(false);
        this.setDividerHeight(0);
        this.setEnabled(false);
    }

    private int position = -1;

    private int mScrollY;

    private long mTimer = 1000;

    private Context mContext;

    /**
     * 获取高度
     *
     * @return
     */
    public int getMarqueeViewHeight() {
        return mHeight;
    }

    private Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 开启轮播
            switchItem();
            handler.postDelayed(this, mTimer);
        }
    };

    /**
     * dp-->px
     *
     * @param dipValue
     * @return
     */
    private int dip2px(float dipValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 开始轮播
     */
    private void switchItem() {
        /*
      向上滚动距离
     */
        int scroll_Y;
        if (position == -1) {
            scroll_Y = 0;
        } else {
            scroll_Y = mScrollY;
        }
        smoothScrollBy(scroll_Y, 2000);
        setSelection(position);
        position++;
    }

    /**
     * 刷新列表后，重置position
     */
    public void reset() {
        position = 0;
        smoothScrollToPosition(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    /**
     * 设置轮播间隔时间
     *
     * @param _time 毫秒单位
     */
    public void setTimer(long _time) {
        this.mTimer = _time;
    }

    /**
     * 开启轮播
     */
    public void start() {
        if (!isStart) {
            handler.postDelayed(runnable, 1000);
            isStart = true;
        }
    }

    /**
     * 关闭轮播
     */
    public void stop() {
        if (isStart) {
            handler.removeCallbacks(runnable);
            isStart = false;
        }
    }
}
