package vn.com.vng.modulesview_sample.sample.custom_view.demo_view;

import android.content.Context;
import android.text.TextUtils;

import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.TextModule;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTextEllipselView extends ModulesView {
    public DemoTextEllipselView(Context context) {
        super(context);
        init();
    }

    TextModule mTextModule;

    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTextModule = new TextModule(mContext);
        mTextModule.setText("Ellipze ellipsize ellipsize ellipsize ellipsize ellipsize ellipsize ellipsize ellipsize ellipsize ellipsize");
        mTextModule.setMaxLines(1);
        mTextModule.setEllipsize(TextUtils.TruncateAt.END);
        mTextModule.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setPadding(dp(4));

        addModule(mTextModule);
    }

}
