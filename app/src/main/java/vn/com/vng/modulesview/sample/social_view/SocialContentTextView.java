package vn.com.vng.modulesview.sample.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModuleParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.TextModule;
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
        mTextContent.getModuleParams()
                .setDimensions(ModuleParams.MATCH_PARENT, ModuleParams.WRAP_CONTENT)
                .setMargin(dp(8), dp(4), dp(8), dp(4));
        addModule(mTextContent);
    }


    private TextModule buildContentTextModule() {
        TextModule module = new TextModule();
        module.setTextSize(sp(14));
        module.setTextColor(0xff222222);
        return module;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasureDimension(getMeasuredWidth(), mTextContent.getHeight() + mTextContent.getModuleParams().getMarginTop() + mTextContent.getModuleParams().getMarginBottom());
    }

    public void bindModel(SocialModel model) {
        if (model != null)
            mTextContent.setText(model.getContent());
        else
            mTextContent.setText(null);
        requestLayout();
    }
}
