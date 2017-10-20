package vn.com.vng.modulesview.modules_view;

import android.view.View;

/**
 * Created by HungNQ on 20/10/2017.
 */

public class ParentAnchor extends Anchor {

    Module mModule;
    public ParentAnchor(Module module, @AnchorType int anchorType) {
        super(anchorType);
        mModule = module;
    }

    public Module getModule() {
        return mModule;
    }

    public void setModule(Module module) {
        mModule = module;
    }

    @Override
    public int getLeft() {
        if(mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        return mModule.getParent().getPaddingLeft();
    }

    @Override
    public int getTop() {
        if(mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        return mModule.getParent().getPaddingTop();
    }

    @Override
    public int getRight() {
        if(mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        ModulesView parent = mModule.getParent();
        if(parent.mWidthMeasureMode == View.MeasureSpec.UNSPECIFIED)
            return BOUND_UNSPECIFIED;
        return Math.max(parent.mWidthMeasureSize - mModule.getParent().getPaddingRight(), 0);
    }

    @Override
    public int getBottom() {
        if(mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        ModulesView parent = mModule.getParent();
        if(parent.mWidthMeasureMode == View.MeasureSpec.UNSPECIFIED)
            return BOUND_UNSPECIFIED;
        return Math.max(parent.mHeightMeasureSize - mModule.getParent().getPaddingBottom() , 0);
    }
}
