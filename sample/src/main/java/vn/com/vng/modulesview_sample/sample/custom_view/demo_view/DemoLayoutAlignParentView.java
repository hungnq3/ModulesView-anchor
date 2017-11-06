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

public class DemoLayoutAlignParentView extends ModulesView {
    public DemoLayoutAlignParentView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;
    TextModule mTextModule4;
    TextModule mTextModule5;
    TextModule mTextModule6;
    TextModule mTextModule7;
    TextModule mTextModule8;
    TextModule mTextModule9;
    private void init() {
        setSize(LayoutParams.MATCH_PARENT, dp(400));
        setBackgroundColor(0xffcccccc);

        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("AlignParentLeft\nAlignParentTop");
        mTextModule1.setBackgroundColor(0xff123456);
        mTextModule1.setTextColor(0xfff5f5f5);
        mTextModule1.setTextSize(sp(10.5f));
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentTop(true)
                .setDimensions(dp(110), dp(100));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("CenterInParent");
        mTextModule2.setBackgroundColor(0xff123456);
        mTextModule2.setTextColor(0xfff5f5f5);
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.setTextSize(sp(10.5f));
        mTextModule2.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setCenterInParent(true)
                .setDimensions(dp(110), dp(100));

        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("AlignParentTop\nCenterInHorizontal");
        mTextModule3.setBackgroundColor(0xff123456);
        mTextModule3.setTextSize(sp(10.5f));
        mTextModule3.setTextColor(0xfff5f5f5);
        mTextModule3.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule3.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setCenterInHorizontal(true)
                .setDimensions(dp(110), dp(100));

        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("AlignParentLeft\nCenterInVertical");
        mTextModule4.setBackgroundColor(0xff123456);
        mTextModule4.setTextColor(0xfff5f5f5);
        mTextModule4.setTextSize(sp(10.5f));
        mTextModule4.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule4.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setCenterInVertical(true)
                .setDimensions(dp(110), dp(100));

        mTextModule5 = new TextModule(mContext);
        mTextModule5.setText("AlignParentRight\nAlignParentTop");
        mTextModule5.setBackgroundColor(0xff123456);
        mTextModule5.setTextColor(0xfff5f5f5);
        mTextModule5.setTextSize(sp(10.5f));
        mTextModule5.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule5.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentRight(true)
                .setDimensions(dp(110), dp(100));

        mTextModule6 = new TextModule(mContext);
        mTextModule6.setText("AlignParentLeft\nAlignParentBottom");
        mTextModule6.setBackgroundColor(0xff123456);
        mTextModule6.setTextColor(0xfff5f5f5);
        mTextModule6.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule6.setTextSize(sp(10.5f));
        mTextModule6.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentBottom(true)
                .setDimensions(dp(110), dp(100));

        mTextModule7 = new TextModule(mContext);
        mTextModule7.setText("CenterInHorizontal\nAlignParentBottom");
        mTextModule7.setBackgroundColor(0xff123456);
        mTextModule7.setTextColor(0xfff5f5f5);
        mTextModule7.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule7.setTextSize(sp(10.5f));
        mTextModule7.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentBottom(true)
                .setCenterInHorizontal(true)
                .setDimensions(dp(110), dp(100));

        mTextModule8 = new TextModule(mContext);
        mTextModule8.setText("AlignParentRight\nCenterInVertical");
        mTextModule8.setBackgroundColor(0xff123456);
        mTextModule8.setTextColor(0xfff5f5f5);
        mTextModule8.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule8.setTextSize(sp(10.5f));
        mTextModule8.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentRight(true)
                .setCenterInVertical(true)
                .setDimensions(dp(110), dp(100));

        mTextModule9 = new TextModule(mContext);
        mTextModule9.setText("AlignParentRight\nAlignParentBottom");
        mTextModule9.setBackgroundColor(0xff123456);
        mTextModule9.setTextColor(0xfff5f5f5);
        mTextModule9.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule9.setTextSize(sp(10.5f));
        mTextModule9.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignParentBottom(true)
                .setAlignParentRight(true)
                .setDimensions(dp(110), dp(100));

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
        addModule(mTextModule4);
        addModule(mTextModule5);
        addModule(mTextModule6);
        addModule(mTextModule7);
        addModule(mTextModule8);
        addModule(mTextModule9);

    }
}
