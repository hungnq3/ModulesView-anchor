package vn.com.vng.modulesview_sample.sample.custom_view.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by HungNQ on 08/11/2017.
 */

public class CustomRelativeLayout extends RelativeLayout {
    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//
//
//    @Override
//    public void draw(Canvas canvas) {
//        long t = System.nanoTime();
//        super.draw(canvas);
//        Log.i("Drawing time","[Native View] draw: " + String.valueOf(System.nanoTime() - t));
//    }
//
//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        long t = System.nanoTime();
//        super.dispatchDraw(canvas);
//        Log.i("Drawing time", "[Native View] dispatchDraw: " + String.valueOf(System.nanoTime() - t));
//
//    }
}
