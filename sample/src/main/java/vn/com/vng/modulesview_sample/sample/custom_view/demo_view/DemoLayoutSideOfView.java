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

public class DemoLayoutSideOfView extends ModulesView {
    public DemoLayoutSideOfView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;
    TextModule mTextModule4;
    TextModule mTextModule5;
    private void init() {
        setSize(LayoutParams.MATCH_PARENT, dp(400));
        setBackgroundColor(0xffcccccc);

        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("[ViewCenter]\nCenterInParent");
        mTextModule1.setBackgroundColor(0xff123456);
        mTextModule1.setTextColor(0xfff5f5f5);
        mTextModule1.setTextSize(sp(10.5f));
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setCenterInParent(true)
                .setDimensions(dp(140), dp(140));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("ToLeftOf [ViewCenter]");
        mTextModule2.setBackgroundColor(0xff445566);
        mTextModule2.setTextColor(0xfff5f5f5);
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.setTextSize(sp(10.5f));
        mTextModule2.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setToLeftOf(mTextModule1)
                .setDimensions(dp(60), LayoutParams.WRAP_CONTENT);
        
        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("ToRightOf [ViewCenter]");
        mTextModule3.setBackgroundColor(0xff445566);
        mTextModule3.setTextColor(0xfff5f5f5);
        mTextModule3.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule3.setTextSize(sp(10.5f));
        mTextModule3.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setToRightOf(mTextModule1)
                .setDimensions(dp(60), LayoutParams.WRAP_CONTENT);


        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("AboveOf [ViewCenter]");
        mTextModule4.setBackgroundColor(0xff445566);
        mTextModule4.setTextColor(0xfff5f5f5);
        mTextModule4.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule4.setTextSize(sp(10.5f));
        mTextModule4.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAboveOf(mTextModule1)
                .setDimensions(dp(60), LayoutParams.WRAP_CONTENT);

        mTextModule5 = new TextModule(mContext);
        mTextModule5.setText("BellowOf to [ViewCenter]");
        mTextModule5.setBackgroundColor(0xff445566);
        mTextModule5.setTextColor(0xfff5f5f5);
        mTextModule5.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule5.setTextSize(sp(10.5f));
        mTextModule5.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setBelowOf(mTextModule1)
                .setDimensions(dp(60), LayoutParams.WRAP_CONTENT);

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
        addModule(mTextModule4);
        addModule(mTextModule5);

    }
}
