package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.graphics.Typeface;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTextTypefaceView extends ModulesView {
    public DemoTextTypefaceView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;
    TextModule mTextModule4;
    TextModule mTextModule5;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("DEFAULT");
        mTextModule1.setTypeFace(Typeface.DEFAULT);
        mTextModule1.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("DEFAULT BOLD");
        mTextModule2.setTypeFace(Typeface.DEFAULT_BOLD);
        mTextModule2.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBelowOf(mTextModule1)
                .setPadding(dp(8), dp(4), dp (8), dp(4));


        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("MONOSPACE");
        mTextModule3.setTypeFace(Typeface.MONOSPACE);
        mTextModule3.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBelowOf(mTextModule2)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("SANS_SERIF");
        mTextModule4.setTypeFace(Typeface.SANS_SERIF);
        mTextModule4.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBelowOf(mTextModule3)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule5 = new TextModule(mContext);
        mTextModule5.setText("SERIF");
        mTextModule5.setTypeFace(Typeface.SERIF);
        mTextModule5.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBelowOf(mTextModule4)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
        addModule(mTextModule4);
        addModule(mTextModule5);
    }

}
