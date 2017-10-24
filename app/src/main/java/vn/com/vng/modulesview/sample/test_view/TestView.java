package vn.com.vng.modulesview.sample.test_view;

import android.content.Context;
import android.view.ViewGroup;

import vn.com.vng.modulesview.modules_view.GroupModule;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.TextModule;

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


    private void init() {

        setPadding(dp(8), dp(8), dp(8), dp(8));

        mGroup1 = new GroupModule(getContext());
        mGroup1.setBackgroundColor(0xffbbccdd);

        mGroup1.getLayoutParams()
                .anchorTopToParent(true)
                .setPadding(dp(8))
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mText1 = new TextModule(getContext());
        mText1.setText("TEXT 1");
        mText1.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mText2 = new TextModule(getContext());
        mText2.setText("TEXT 2");
        mText2.setBackgroundColor(0xff112233);
        mText2.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorTopToBottom(mText1);


        mGroup1.addModule(mText1);
        mGroup1.addModule(mText2);


        mGroup2 = new GroupModule(getContext());
        mGroup2.setBackgroundColor(0xff556677);
        mGroup2.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToRight(mGroup1);

        mText3 = new TextModule(getContext());
        mText3.setText("TEXT 3");
        mText3.getLayoutParams()
                .setMargin(dp(8))
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mText4 = new TextModule(getContext());
        mText4.setText("TEXT 4");
        mText4.getLayoutParams()
                .setPadding(dp(16))
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorTopToBottom(mText3);


        mGroup2.addModule(mText3);
        mGroup2.addModule(mText4);


        mText5 = new TextModule(getContext());
        mText5.setText("HELLO");
        mText5.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToRight(mGroup2)
                .anchorTopToParent(true);


        addModule(mGroup1);
        addModule(mGroup2);
        addModule(mText5);

    }
}