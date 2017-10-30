package vn.com.vng.modulesview;

/**
 * Created by HungNQ on 25/10/2017.
 */

class GuideLineAnchor extends Anchor {

    private Module mModule;
    private Guideline mGuideline;

    public GuideLineAnchor(Module module, Guideline guideline, @AnchorType int anchorType) {
        super(anchorType);
        mModule = module;
        mGuideline = guideline;
    }

    public Module getModule() {
        return mModule;
    }

    public void setModule(Module module) {
        mModule = module;
    }

    public Guideline getGuideline() {
        return mGuideline;
    }

    public void setGuideline(Guideline guideline) {
        mGuideline = guideline;
    }



    public int getXValue() {
        if(mGuideline == null || mModule == null || mModule.getParent() == null)
            return  0;
        int value = mGuideline.getdX();
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentWidth() * mGuideline.getXPercent();
        }
        return value;
    }

    public int getYValue() {
        if(mGuideline == null || mModule == null || mModule.getParent() == null)
            return  0;
        int value = mGuideline.getdY();
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentHeight() * mGuideline.getYPercent();
        }
        return value;
    }


    @Override
    public int getLeft() {
        return getXValue();
    }

    @Override
    public int getTop() {
        return getYValue();
    }

    @Override
    public int getRight() {
        return getXValue();
    }

    @Override
    public int getBottom() {
        return getYValue();
    }
}
