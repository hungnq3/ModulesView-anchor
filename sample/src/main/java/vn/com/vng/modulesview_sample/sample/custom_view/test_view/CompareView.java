package vn.com.vng.modulesview_sample.sample.custom_view.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;

import vn.com.vng.modulesview.GroupModule;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.ImageModule;
import vn.com.vng.modulesview.widget.TextModule;
import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 07/11/2017.
 */

public class CompareView extends ModulesView {
    public CompareView(Context context) {
        super(context);
        init();
    }

    public CompareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CompareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    ImageModule mImageModule;
    GroupModule mGroupModule;
    TextModule mTextModule1;
    TextModule mTextModule2;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setPadding(dp(8), dp(8), dp(8), dp(8));

        mImageModule = new ImageModule(getContext());
        mImageModule.setScaleType(ImageModule.CENTER_CROP);
        mImageModule.setImageResource(R.drawable.img);
        mImageModule.getLayoutParams()
                .setDimensions(dp(60), dp(60));

        mGroupModule = new GroupModule(getContext());
        mGroupModule.getLayoutParams()
                .setMargin(dp(8),0,0,0)
                .setCenterInVertical(true)
                .setToRightOf(mImageModule);

        mTextModule1 = new TextModule(getContext());
        mTextModule1.setText("Primary text");
        mTextModule1.setTextColor(0xff050505);
        mTextModule1.setTextStyle(TextModule.TEXT_STYLE_BOLD);


        mTextModule2 = new TextModule(getContext());
        mTextModule2.setText("Secondary text");
        mTextModule2.setTextSize(sp(12));
        mTextModule2.getLayoutParams()
                .setBellowOf(mTextModule1)
                .setAlignLeft(mTextModule1);


        mGroupModule.addModule(mTextModule1);
        mGroupModule.addModule(mTextModule2);

        addModule(mImageModule);
        addModule(mGroupModule);
    }

//    @Override
//    public void layout(int l, int t, int r, int b) {
//        long time = System.nanoTime();
//        super.layout(l, t, r, b);
//        Log.i("Layout time", "[ModulesView] layout: " + String.valueOf(System.nanoTime() - time));
//
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        long t = System.nanoTime();
//        super.draw(canvas);
//        Log.i("Drawing time", "[ModulesView] draw: " + String.valueOf(System.nanoTime() - t));
//    }
}
