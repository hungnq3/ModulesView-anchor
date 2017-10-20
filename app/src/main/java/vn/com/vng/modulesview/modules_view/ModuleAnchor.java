package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 20/10/2017.
 */

public class ModuleAnchor extends Anchor {
    private Module mModule;

    public ModuleAnchor(Module module, @AnchorType int anchorType) {
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
        if (mModule == null)
            return BOUND_UNKNOWN;
        int left = mModule.getLeft();
        if (left == Module.BOUND_UNKNOWN || left == Module.BOUND_UNSPECIFIED)
            return BOUND_UNKNOWN;
        if (mModule.getLayoutParams().getVisibility() == LayoutParams.GONE) {
            left -= mModule.getLayoutParams().mMarginLeft;
        }
        return left;
    }

    @Override
    public int getTop() {
        if (mModule == null || mModule.getLayoutParams().getVisibility() == LayoutParams.GONE)
            return BOUND_UNKNOWN;
        int top = mModule.getTop();
        if (top == Module.BOUND_UNKNOWN || top == Module.BOUND_UNSPECIFIED)
            return BOUND_UNKNOWN;

        if (mModule.getLayoutParams().getVisibility() == LayoutParams.GONE)
            top -= mModule.getLayoutParams().mMarginTop;

        return top;
    }

    @Override
    public int getRight() {
        if (mModule == null)
            return BOUND_UNKNOWN;
        int right;
        if (mModule.getLayoutParams().getVisibility() == LayoutParams.GONE) {
            right = mModule.getLeft();
            if (right != Module.BOUND_UNKNOWN && right != Module.BOUND_UNSPECIFIED)
                right -= mModule.getLayoutParams().mMarginLeft;
        } else
            right = mModule.getRight();

        return right == Module.BOUND_UNKNOWN || right == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : right;
    }

    @Override
    public int getBottom() {
        if (mModule == null || mModule.getLayoutParams().getVisibility() == LayoutParams.GONE)
            return BOUND_UNKNOWN;

        int bottom;
        if (mModule.getLayoutParams().getVisibility() == LayoutParams.GONE) {
            bottom = mModule.getTop();
            if (mModule.getLayoutParams().getVisibility() == LayoutParams.GONE) {
                bottom = mModule.getTop();
                if (bottom != Module.BOUND_UNKNOWN && bottom != Module.BOUND_UNSPECIFIED)
                    bottom -= mModule.getLayoutParams().mMarginTop;
            }
        } else
            bottom = mModule.getBottom();
        return bottom == Module.BOUND_UNKNOWN || bottom == Module.BOUND_UNSPECIFIED ? BOUND_UNKNOWN : bottom;
    }
}
