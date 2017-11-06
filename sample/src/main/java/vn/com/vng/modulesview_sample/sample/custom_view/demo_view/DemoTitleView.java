package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.Module;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTitleView extends ModulesView {
    public DemoTitleView(Context context) {
        super(context);
        init();
    }


    TextModule mTextModule;
    Module mLineModule;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setPadding(0, dp(8), 0 , dp(8));

        mTextModule = new TextModule(mContext);
        mTextModule.setTextStyle(TextModule.TEXT_STYLE_BOLD);
        mTextModule.setTextColor(0xff050505);
        mTextModule.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule.setTextSize(dp(22));
        mTextModule.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8));

        mLineModule = new Module(mContext);
        mLineModule.setBackgroundColor(0xffcccccc);
        mLineModule.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 2)
                .setBellowOf(mTextModule);


        addModule(mTextModule);
        addModule(mLineModule);
    }

    public void setText(CharSequence text){
        mTextModule.setText(text);
    }
}
