# MarqueeView
跑马灯效果，适用商城动态滚动广告        

# PreView         
![ScreenShot](https://github.com/ShaqCc/MarqueeView/blob/master/screenshot/screen.gif)     

# Usage     
### just use it like LiseView        

## gradle         

`compile 'com.shaqcc:marqueelib:1.0.0'`          


## Maven       

        <dependency>
         <groupId>com.shaqcc</groupId>
         <artifactId>marqueelib</artifactId>
         <version>1.0.0</version>
         <type>pom</type>
        </dependency>      
    
## XML      

    <com.shaqcc.lib.marqueeview.MarqueeView
            android:id="@+id/marquee"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            app:marquee_height="40"
            android:paddingLeft="10dp"/>         
            
## Java Code            

```
        mTmallMarquee = (MarqueeView) findViewById(R.id.marquee);
        mTmallMarquee.setAdapter(new TmallAdapter(mTmallDataList));
        mTmallMarquee.setTimer(3000);
        mTmallMarquee.start();
```      

### Adapter

`private class InvestAdapter extends MarqueeAdapter<D> {...}`







