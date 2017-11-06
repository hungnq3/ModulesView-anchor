package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.Layout;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTextAlignmentView extends ModulesView {
    public DemoTextAlignmentView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule1;
    TextModule mTextModule2;
    TextModule mTextModule3;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTextModule1 = new TextModule(mContext);
        mTextModule1.setText("Alignment normal");
        mTextModule1.setAlignment(Layout.Alignment.ALIGN_NORMAL);
        mTextModule1.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(8));

        mTextModule2 = new TextModule(mContext);
        mTextModule2.setText("Alignment center");
        mTextModule2.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mTextModule2.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule1)
                .setPadding(dp(8));

        mTextModule3 = new TextModule(mContext);
        mTextModule3.setText("Alignment opposite");
        mTextModule3.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
        mTextModule3.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setBellowOf(mTextModule2)
                .setPadding(dp(8));

        addModule(mTextModule1);
        addModule(mTextModule2);
        addModule(mTextModule3);
    }

}
