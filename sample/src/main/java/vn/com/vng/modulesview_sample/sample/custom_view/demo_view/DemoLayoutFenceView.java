package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.Layout;

import vn.com.vng.modulesview.Fence;
import vn.com.vng.modulesview.GravityCompat;
import vn.com.vng.modulesview.Guideline;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoLayoutFenceView extends ModulesView {


    public DemoLayoutFenceView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule;
    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;
    TextModule mTextModule4;
    private void init() {
        setSize(LayoutParams.MATCH_PARENT, dp(400));
        setBackgroundColor(0xffcccccc);

        Guideline guideline1 = new Guideline(1/3f,0f);
        Guideline guideline2 = new Guideline(2/3f,0f);

        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("[Text1]\ntext text text text text text\ntext text");
        mTextModule1.setBackgroundColor(0xff123456);
        mTextModule1.setTextColor(0xfff5f5f5);
        mTextModule1.setTextSize(sp(10.5f));
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule1.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setToLeftOf(guideline1)
                .setDimensions(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("[Text2]");
        mTextModule2.setBackgroundColor(0xff445566);
        mTextModule2.setTextColor(0xfff5f5f5);
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.setTextSize(sp(10.5f));
        mTextModule2.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setToLeftOf(guideline2)
                .setToRightOf(guideline1)
                .setDimensions(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("[Text3]");
        mTextModule3.setBackgroundColor(0xff667788);
        mTextModule3.setTextColor(0xfff5f5f5);
        mTextModule3.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule3.setTextSize(sp(10.5f));
        mTextModule3.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setToRightOf(guideline2)
                .setDimensions(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);


        mTextModule4 = new TextModule(mContext);
        mTextModule4.setText("AlignRight to [Text2]\nToBellowOf Fence(text1, text2, text3)");
        mTextModule4.setBackgroundColor(0xff667788);
        mTextModule4.setTextColor(0xfff5f5f5);
        mTextModule4.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule4.setTextSize(sp(10.5f));
        mTextModule4.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setBellowOf(new Fence(mTextModule1, mTextModule2, mTextModule3))
                .setAlignRight(mTextModule2)
                .setDimensions(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);



        mTextModule = new TextModule(mContext);
        mTextModule.setText("Guideline1: xPercent = 1/3f\nGuideline2: xPercent = 2/3f");
        mTextModule.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule.getLayoutParams()
                .setCenterInParent(true)
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);



        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
        addModule(mTextModule4);
        addModule(mTextModule);

    }
}
