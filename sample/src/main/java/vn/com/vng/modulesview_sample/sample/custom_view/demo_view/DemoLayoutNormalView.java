package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.Layout;

import vn.com.vng.modulesview.GravityCompat;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoLayoutNormalView extends ModulesView {
    public DemoLayoutNormalView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    private void init() {
        setSize(LayoutParams.MATCH_PARENT, dp(400));
        setBackgroundColor(0xffdddddd);

        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("Default");
        mTextModule1.setBackgroundColor(0xff123456);
        mTextModule1.setTextColor(0xfff5f5f5);
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setDimensions(dp(100), dp(100));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("Size: (MATCH_PARENT, 400dp)\nBackground: 0xffddddd");
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.getLayoutParams()
                .setCenterInParent(true)
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        addModule(mTextModule1);
        addModule(mTextModule2);
    }
}
