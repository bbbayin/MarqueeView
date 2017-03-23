package com.shaqcc.lib.marqueeview;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by bayin on 2017/3/21.
 */

public abstract class MarqueeAdapter<D> extends BaseAdapter {
    protected List<D> mDList;
    private int dataSize = 0;

    public MarqueeAdapter(List<D> list) {
        this.mDList = list;
        if (mDList == null || mDList.size() == 0) dataSize = 0;
        else dataSize = mDList.size();
    }

    @Override
    public int getCount() {
        if (mDList == null || mDList.size() == 0) {
            return 0;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public Object getItem(int position) {

        return mDList.get(position % dataSize);
    }

    @Override
    public long getItemId(int position) {
        return position % dataSize;
    }

}
