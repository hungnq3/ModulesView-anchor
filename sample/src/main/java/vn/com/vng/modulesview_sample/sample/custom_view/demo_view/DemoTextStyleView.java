package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.graphics.Typeface;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTextStyleView extends ModulesView {
    public DemoTextStyleView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;
    TextModule mTextModule4;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("NORMAL");
        mTextModule1.setTextStyle(TextModule.TEXT_STYLE_NORMAL);
        mTextModule1.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("BOLD");
        mTextModule2.setTextStyle(TextModule.TEXT_STYLE_BOLD);
        mTextModule2.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule1)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("ITALIC");
        mTextModule3.setTextStyle(TextModule.TEXT_STYLE_ITALIC);
        mTextModule3.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule2)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("BOLD ITALIC");
        mTextModule4.setTextStyle(TextModule.TEXT_STYLE_BOLD_ITALIC);
        mTextModule4.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule3)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
        addModule(mTextModule4);
    }

}
