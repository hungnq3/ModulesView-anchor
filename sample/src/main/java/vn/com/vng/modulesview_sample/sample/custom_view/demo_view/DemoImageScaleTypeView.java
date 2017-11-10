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

public class DemoImageScaleTypeView extends ModulesView {
    public DemoImageScaleTypeView(Context context) {
        super(context);
        init();
    }

    ImageModule mImageModule1;
    TextModule mTextModule1;
    ImageModule mImageModule2;
    TextModule mTextModule2;
    ImageModule mImageModule3;
    TextModule mTextModule3;
    ImageModule mImageModule4;
    TextModule mTextModule4;
    ImageModule mImageModule5;
    TextModule mTextModule5;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mImageModule1 = new ImageModule(mContext);
        mImageModule1.setImageResource(R.drawable.img);
        mImageModule1.setBackgroundColor(0xffcccccc);
        mImageModule1.setScaleType(ImageModule.CENTER);
        mImageModule1.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setCenterInHorizontal(true);


        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("ScaleType: CENTER");
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.setTextSize(sp(12));
        mTextModule1.getLayoutParams()
                .setBelowOf(mImageModule1)
                .setCenterInHorizontal(true);


        mImageModule2 = new ImageModule(mContext);
        mImageModule2.setImageResource(R.drawable.img);
        mImageModule2.setBackgroundColor(0xffcccccc);
        mImageModule2.setScaleType(ImageModule.CENTER_INSIDE);
        mImageModule2.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setBelowOf(mTextModule1)
                .setMarginTop(dp(8))
                .setCenterInHorizontal(true);


        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("ScaleType: CENTER_INSIDE");
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.setTextSize(sp(12));
        mTextModule2.getLayoutParams()
                .setBelowOf(mImageModule2)
                .setCenterInHorizontal(true);

        mImageModule3 = new ImageModule(mContext);
        mImageModule3.setImageResource(R.drawable.img);
        mImageModule3.setBackgroundColor(0xffcccccc);
        mImageModule3.setScaleType(ImageModule.CENTER_CROP);
        mImageModule3.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setBelowOf(mTextModule2)
                .setMarginTop(dp(8))
                .setCenterInHorizontal(true);


        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("ScaleType: CENTER_CROP");
        mTextModule3.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule3.setTextSize(sp(12));
        mTextModule3.getLayoutParams()
                .setBelowOf(mImageModule3)
                .setCenterInHorizontal(true);

        mImageModule4 = new ImageModule(mContext);
        mImageModule4.setImageResource(R.drawable.img);
        mImageModule4.setBackgroundColor(0xffcccccc);
        mImageModule4.setScaleType(ImageModule.FIT_CENTER);
        mImageModule4.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setBelowOf(mTextModule3)
                .setMarginTop(dp(8))
                .setCenterInHorizontal(true);


        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("ScaleType: FIT_CENTER");
        mTextModule4.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule4.setTextSize(sp(12));
        mTextModule4.getLayoutParams()
                .setBelowOf(mImageModule4)
                .setCenterInHorizontal(true);

        mImageModule5 = new ImageModule(mContext);
        mImageModule5.setImageResource(R.drawable.img);
        mImageModule5.setBackgroundColor(0xffcccccc);
        mImageModule5.setScaleType(ImageModule.FIT_XY);
        mImageModule5.getLayoutParams()
                .setDimensions(dp(200), dp(200))
                .setBelowOf(mTextModule4)
                .setMarginTop(dp(8))
                .setCenterInHorizontal(true);


        mTextModule5 = new TextModule(mContext);
        mTextModule5.setText("ScaleType: FIT_XY");
        mTextModule5.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule5.setTextSize(sp(12));
        mTextModule5.getLayoutParams()
                .setBelowOf(mImageModule5)
                .setCenterInHorizontal(true);


        addModule(mImageModule1);
        addModule(mTextModule1);
        addModule(mImageModule2);
        addModule(mTextModule2);
        addModule(mImageModule3);
        addModule(mTextModule3);
        addModule(mImageModule4);
        addModule(mTextModule4);
        addModule(mImageModule5);
        addModule(mTextModule5);
    }
}
