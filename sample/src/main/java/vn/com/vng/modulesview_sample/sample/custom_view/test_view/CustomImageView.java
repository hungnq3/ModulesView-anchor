package vn.com.vng.modulesview_sample.sample.custom_view.test_view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by HungNQ on 09/11/2017.
 */

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        long t = System.nanoTime();
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.i("Measure time","[ImageView]: " + String.valueOf(System.nanoTime() - t));
////        Log.i("Measure time","[TextView]: ");
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        long time = System.nanoTime();
//        super.onLayout(changed, l, t, r, b);
//        Log.i("Layout time","[ImageView]: " + String.valueOf(System.nanoTime() - time));
////        Log.i("Layout time","[TextView]: " );
//
//    }

}
