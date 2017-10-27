package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 20/10/2017.
 */

class ModuleAnchor extends Anchor {
    private Module mModule;
    boolean mIgnoreMargin;

    public ModuleAnchor(Module module, @AnchorType int anchorType, boolean ignoreMargin) {
        super(anchorType);
        mModule = module;
        mIgnoreMargin = ignoreMargin;
    }

    public Module getModule() {
        return mModule;
    }

    public void setModule(Module module, boolean ignoreMargin) {
        mModule = module;
        mIgnoreMargin = ignoreMargin;
    }


    @Override
    public int getLeft() {
        if (mModule == null)
            return BOUND_UNKNOWN;
        int left = mModule.getLeft();
        return left == Module.BOUND_UNKNOWN || left == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : left - (mIgnoreMargin ? 0 : mModule.getLayoutParams().getMarginLeft());
    }

    @Override
    public int getTop() {
        if (mModule == null || mModule.getLayoutParams().getVisibility() == LayoutParams.GONE)
            return BOUND_UNKNOWN;
        int top = mModule.getTop();
        return top == Module.BOUND_UNKNOWN || top == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : top - (mIgnoreMargin ? 0 : mModule.getLayoutParams().getMarginTop());
    }

    @Override
    public int getRight() {
        if (mModule == null)
            return BOUND_UNKNOWN;
        int right = mModule.getRight();

        return right == Module.BOUND_UNKNOWN || right == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : right + (mIgnoreMargin ? 0 : mModule.getLayoutParams().getMarginRight());
    }

    @Override
    public int getBottom() {
        if (mModule == null || mModule.getLayoutParams().getVisibility() == LayoutParams.GONE)
            return BOUND_UNKNOWN;
        int bottom = mModule.getBottom();
        return bottom == Module.BOUND_UNKNOWN || bottom == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : bottom  + (mIgnoreMargin ? 0 : mModule.getLayoutParams().getMarginBottom());
    }
}
