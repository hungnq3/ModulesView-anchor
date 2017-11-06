package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.Layout;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.ImageModule;
import vn.com.vng.modulesview.widget.TextModule;
import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoImageRoundedView extends ModulesView {
    public DemoImageRoundedView(Context context) {
        super(context);
        init();
    }

    ImageModule mImageModule1;
    TextModule mTextModule1;

    ImageModule mImageModule2;
    TextModule mTextModule2;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mImageModule1 = new ImageModule(mContext);
        mImageModule1.setImageResource(R.drawable.img);
        mImageModule1.setBackgroundColor(0xffcccccc);
        mImageModule1.setRoundCorner(dp(8));
        mImageModule1.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setCenterInHorizontal(true);


        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("Round corners: 8dp");
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.setTextSize(sp(12));
        mTextModule1.getLayoutParams()
                .setBellowOf(mImageModule1)
                .setCenterInHorizontal(true);

        mImageModule2 = new ImageModule(mContext);
        mImageModule2.setImageResource(R.drawable.img);
        mImageModule2.setBackgroundColor(0xffcccccc);
        mImageModule2.setRoundCorner(ImageModule.ROUND_CIRCLE);
        mImageModule2.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setMarginTop(dp(8))
                .setBellowOf(mTextModule1)
                .setCenterInHorizontal(true);


        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("Round corners: ROUND_CIRCLE");
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.setTextSize(sp(12));
        mTextModule2.getLayoutParams()
                .setBellowOf(mImageModule2)
                .setCenterInHorizontal(true);


        addModule(mImageModule1);
        addModule(mTextModule1);
        addModule(mImageModule2);
        addModule(mTextModule2);
    }
}
