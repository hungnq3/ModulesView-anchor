package vn.com.vng.modulesview.sample.custom_view.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.TextModule;
import vn.com.vng.modulesview.sample.model.SocialModel;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialContentTextView extends ModulesView {
    public SocialContentTextView(Context context) {
        this(context, null);
    }

    public SocialContentTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SocialContentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SocialContentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    TextModule mTextContent;


    private void init() {
        mTextContent = buildContentTextModule();
        mTextContent.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setMargin(dp(8), dp(4), dp(8), dp(4));
        addModule(mTextContent);
    }


    private TextModule buildContentTextModule() {
        TextModule module = new TextModule(getContext());
        module.setTextSize(sp(14));
        module.setTextColor(0xff222222);
        return module;
    }



//    @Override
//    protected void onPostMeasureChildren(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(getMeasuredWidth(), mTextContent.getHeight() + mTextContent.getLayoutParams().getMarginTop() + mTextContent.getLayoutParams().getMarginBottom());
//    }

    public void bindModel(SocialModel model) {
        if (model != null)
            mTextContent.setText(model.getContent());
        else
            mTextContent.setText(null);
        requestLayout();
    }
}
