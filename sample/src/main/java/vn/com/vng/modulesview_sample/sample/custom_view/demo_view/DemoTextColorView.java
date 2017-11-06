package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTextColorView extends ModulesView {
    public DemoTextColorView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("Text color 0xff445566");
        mTextModule1.setTextColor(0xff445566);
        mTextModule1.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("Text color 0xff998877");
        mTextModule2.setTextColor(0xff998877);
        mTextModule2.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule1)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("Text color 0xffee0934");
        mTextModule3.setTextColor(0xffee0934);
        mTextModule3.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule2)
                .setPadding(dp(8), dp(4), dp (8), dp(4));

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
    }

}
