package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.graphics.Typeface;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.Module;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoHeaderView extends ModulesView {
    public DemoHeaderView(Context context) {
        super(context);
        init();
    }


    TextModule mTextModule;
    Module mLineModule;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setPadding(dp(4), dp(4), dp (4), dp(4));

        mTextModule = new TextModule(mContext);
        mTextModule.setTypeFace(Typeface.DEFAULT_BOLD);
        mTextModule.setUnderLine(true);
        mTextModule.setTextColor(0xff050505);
        mTextModule.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);


        addModule(mTextModule);

    }

    public void setText(CharSequence text){
        mTextModule.setText(text);
        mTextModule.invalidate();
    }
}
