package com.shaqcc.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.shaqcc.lib.marqueeview.MarqueeAdapter;
import com.shaqcc.lib.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TmallData> mTmallDataList;
    private MarqueeView mTmallMarquee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mTmallMarquee = (MarqueeView) findViewById(R.id.marquee);
        mTmallMarquee.setAdapter(new TmallAdapter(mTmallDataList));
        mTmallMarquee.setTimer(3000);
        mTmallMarquee.start();
    }

    private void initData() {
        if (mTmallDataList == null) mTmallDataList = new ArrayList<TmallData>();
        TmallData tmallData;
        for (int i = 0; i < 20; i++) {
            tmallData = new TmallData();
            tmallData.setTitle("天猫春季特惠，轻奢大牌" + i);
            tmallData.setDesc("特惠巨献，买一送" + i);
            if (i % 2 == 0) {
                tmallData.setTag("热门");
            } else {
                tmallData.setTag("促销");
            }

            mTmallDataList.add(tmallData);
        }
    }

    private class TmallAdapter extends MarqueeAdapter<TmallData> {

        public TmallAdapter(List<TmallData> list) {
            super(list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder holder;
            if (convertView == null) {
                holder = new MyHolder();
                convertView = View.inflate(MainActivity.this, R.layout.item_tmall_layout, null);
                holder.tvTag = (TextView) convertView.findViewById(R.id.tmall_tv_tag);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tmall_tv_title);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tmall_tv_desc);
                convertView.setTag(holder);

                /**
                 * important 重新手工设置item的高度，setAdapter之后，XML文件中item设置的高度会被重新计算
                 **/
                convertView.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        dip2px(mTmallMarquee.getMarqueeViewHeight())));
            } else {
                holder = (MyHolder) convertView.getTag();
            }

            //set data
            TmallData tmallData = mTmallDataList.get((int) getItemId(position));
            holder.tvTitle.setText(tmallData.getTitle());
            holder.tvDesc.setText(tmallData.getDesc());
            holder.tvTag.setText(tmallData.getTag());
            if (tmallData.getTag().equals("热门")) {
                holder.tvTag.setBackgroundColor(Color.parseColor("#ffff4444"));
            } else {
                holder.tvTag.setBackgroundColor(Color.parseColor("#ff669900"));
            }
            return convertView;
        }
    }

    private class MyHolder {
        private TextView tvTag, tvTitle, tvDesc;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
