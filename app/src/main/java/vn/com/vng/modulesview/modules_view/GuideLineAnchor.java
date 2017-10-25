package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 25/10/2017.
 */

class GuideLineAnchor extends Anchor {

    private Guideline mGuideline;
    public GuideLineAnchor(Guideline guideline, @AnchorType int anchorType) {
        super(anchorType);
        mGuideline = guideline;
    }

    public Guideline getGuideline() {
        return mGuideline;
    }

    public void setGuideline(Guideline guideline) {
        mGuideline = guideline;
    }

    @Override
    public int getLeft() {
        return mGuideline != null ? mGuideline.getXValue() : 0;
    }

    @Override
    public int getTop() {
        return mGuideline != null ? mGuideline.getYValue() : 0;
    }

    @Override
    public int getRight() {
        return mGuideline != null ? mGuideline.getXValue() : 0;
    }

    @Override
    public int getBottom() {
        return mGuideline != null ? mGuideline.getYValue() : 0;
    }
}
