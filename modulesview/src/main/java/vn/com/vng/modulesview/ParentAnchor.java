package vn.com.vng.modulesview;

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
        if (mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
//        return mModule.getParent().getPaddingLeft();
        return 0;
    }

    @Override
    public int getTop() {
        if (mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
//        return mModule.getParent().getPaddingTop();
        return 0;
    }

    @Override
    public int getRight() {
        if (mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        Parent parent = mModule.getParent();

        return parent.getCurrentWidth();
    }

    @Override
    public int getBottom() {
        if (mModule == null || mModule.getParent() == null)
            return BOUND_UNKNOWN;
        Parent parent = mModule.getParent();

        return parent.getCurrentHeight();
    }
}
