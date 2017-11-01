package vn.com.vng.modulesview_sample.sample.custom_view.test_view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.com.vng.modulesview_sample.R;
import vn.com.vng.modulesview.Fence;
import vn.com.vng.modulesview.GravityCompat;
import vn.com.vng.modulesview.GroupModule;
import vn.com.vng.modulesview.Guideline;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.Module;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.ImageModule;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 24/10/2017.
 */

public class TestView extends ModulesView {
    public TestView(Context context) {
        super(context);
        init();
    }

    GroupModule mGroup1;
    GroupModule mGroup2;
    TextModule mText1;
    TextModule mText2;
    TextModule mText3;
    TextModule mText4;
    TextModule mText5;
    ImageModule mImageModule;
    ImageModule mImageModule2;

    Module.OnClickListener mOnClickListener = new Module.OnClickListener() {
        @Override
        public void onClick(Module module) {
            Toast.makeText(getContext(), ((TextModule) module).getText(), Toast.LENGTH_SHORT).show();
        }
    };

    private void init() {
        setSize(ViewGroup.LayoutParams.MATCH_PARENT, dp(200));

        Guideline guideline1 = new Guideline().setXPercent(1 / 3f);
        Guideline guideline2 = new Guideline().setXPercent(2 / 3f);

        mText1 = new TextModule(getContext());
        mText1.setText("TEXT 111111111111111111111111111111111111111111");
        mText1.setBackgroundColor(0xffdddddd);
        mText1.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setAlignParentLeft(true)
                .setToLeftOf(guideline1);

        mText2 = new TextModule(getContext());
        mText2.setText("TEXT 222222222222");
        mText2.setBackgroundColor(0xff112233);
        mText2.setTextColor(0xffffffff);
        mText2.setOnClickListener(mOnClickListener);
        mText2.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8))
                .setToRightOf(guideline1)
                .setToLeftOf(guideline2);

        mText3 = new TextModule(getContext());
        mText3.setText("TEXT 33333\n33333333333\n333333\n33333333333\n33333333");
        mText3.setBackgroundColor(0xff445566);
        mText3.setTextColor(0xffffffff);
        mText3.setOnClickListener(mOnClickListener);
        mText3.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8))
                .setToRightOf(guideline2)
                .setAlignParentRight(true);

        mImageModule = new ImageModule(getContext());
        mImageModule.setScaleType(ImageModule.CENTER_CROP);
        mImageModule.loadImage("https://i.sharefa.st/1295569823374302192636.jpg");
        mImageModule.getLayoutParams()
                .setDimensions(dp(100),dp(100))
                .setAlignRight(mText2)
                .setBellowOf(new Fence(mText1, mText2, mText3));

        addModule(mText1);
        addModule(mText2);
        addModule(mText3);
        addModule(mImageModule);
    }

//    private void init() {
////        setSize(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        setSize(dp(400), dp(400));
//        setPadding(dp(16),dp(16),dp(8),dp(8));
//        setBackgroundColor(0xffdddddd);
////        setGravity(GravityCompat.CENTER_HORIZONTAL | GravityCompat.TOP);
//
//
//        mGroup1 = new GroupModule(getContext());
//        mGroup1.setBackgroundColor(0xffbbccdd);
//        mGroup1.setOnClickListener(new Module.OnClickListener() {
//            @Override
//            public void onClick(Module module) {
//                Toast.makeText(getContext(), "G1", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mGroup1.getLayoutParams()
//                .setPadding(dp(8), dp(8),dp(8),dp(16))
//                .setMargin(dp(8),dp(8),dp(16),dp(16))
//                .setCenterInParent(true)
//                .setGravity(GravityCompat.RIGHT | GravityCompat.BOTTOM)
//                .setDimensions(dp(100), dp(100));
//
//        mText1 = new TextModule(getContext());
//        mText1.setText("TEXT 1");
//        mText1.setOnClickListener(mOnClickListener);
//        mText1.getLayoutParams()
////                .setCenterInParent(true)
//                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//        mText2 = new TextModule(getContext());
//        mText2.setText("TEXT 2");
//        mText2.setBackgroundColor(0xff112233);
//        mText2.setOnClickListener(mOnClickListener);
//        mText2.getLayoutParams()
//                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//                .setBellowOf(mText1);
//
//        mGroup1.addModule(mText1);
//        mGroup1.addModule(mText2);
//
//        mGroup2 = new GroupModule(getContext());
//        mGroup2.setBackgroundColor(0xff556677);
//        mGroup2.getLayoutParams()
//                .setGravity(GravityCompat.CENTER)
//                .setDimensions(dp(200), dp(100))
//                .setMargin(dp(4))
//                .setPadding(dp(4))
//                .setToRightOf(mGroup1)
//                .setBellowOf(mGroup1);
//
//        mText3 = new TextModule(getContext());
//        mText3.setText("TEXT 3");
//        mText3.setOnClickListener(mOnClickListener);
//        mText3.getLayoutParams()
//                .setCenterInParent(true)
//                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//        mText4 = new TextModule(getContext());
//        mText4.setText("TEXT 4 123 123 12112312312312312323 123");
//        mText4.setOnClickListener(mOnClickListener);
//        mText4.getLayoutParams()
//                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//                .setBellowOf(mText3);
//
//
//        mGroup2.addModule(mText3);
//        mGroup2.addModule(mText4);
//
//
//        mText5 = new TextModule(getContext());
//        mText5.setText("TEXT 5 .... \n TEXXXXX");
//        mText5.setBackgroundColor(0xffdddddd);
//        mText5.setOnClickListener(mOnClickListener);
//        mText5.getLayoutParams()
//                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//                .setMarginTop(dp(8))
//                .setPadding(dp(4))
//                .setAlignParentLeft(true)
//                .setToLeftOf(new Guideline().setXPercent(1 / 3f))
//                .setBellowOf(new Fence(mGroup1, mGroup2));
//
//
//        mImageModule = new ImageModule(getContext());
//        mImageModule.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_heart));
//        mImageModule.setBackgroundColor(0xffcccccc);
//        mImageModule.setAdjustViewBound(true);
//        mImageModule.setScaleType(ImageModule.CENTER_INSIDE);
//        mImageModule.getLayoutParams()
//                .setPadding(dp(8))
//                .setDimensions(LayoutParams.WRAP_CONTENT, dp(100));
//
//        mImageModule2 = new ImageModule(getContext());
//        mImageModule2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_phone_call));
//        mImageModule2.setBackgroundColor(0xffcccccc);
//        mImageModule2.setAdjustViewBound(true);
//        mImageModule2.setScaleType(ImageModule.CENTER_INSIDE);
//        mImageModule2.getLayoutParams()
//                .setPadding(dp(8))
//                .setToRightOf(mImageModule)
//                .setDimensions(LayoutParams.WRAP_CONTENT, dp(100));
//
//        addModule(mGroup1);
//        addModule(mGroup2);
////        addModule(mText5);
//
//        addModule(mImageModule);
//        addModule(mImageModule2);
//
//    }
}
