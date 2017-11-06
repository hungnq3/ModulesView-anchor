package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.Layout;

import vn.com.vng.modulesview.GravityCompat;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.ImageModule;
import vn.com.vng.modulesview.widget.TextModule;
import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoImageNormalView extends ModulesView {
    public DemoImageNormalView(Context context) {
        super(context);
        init();
    }

    ImageModule mImageModule;
    TextModule mTextModule;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mImageModule = new ImageModule(mContext);
        mImageModule.setImageResource(R.drawable.img);
        mImageModule.setBackgroundColor(0xffcccccc);
        mImageModule.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setCenterInHorizontal(true);


        mTextModule = new TextModule(mContext);
        mTextModule.setText("Size: (200dp,200dp)\nBackground: 0xffcccccc");
        mTextModule.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule.setTextSize(sp(12));
        mTextModule.getLayoutParams()
                .setBellowOf(mImageModule)
                .setCenterInHorizontal(true);

        addModule(mImageModule);
        addModule(mTextModule);
    }
}
