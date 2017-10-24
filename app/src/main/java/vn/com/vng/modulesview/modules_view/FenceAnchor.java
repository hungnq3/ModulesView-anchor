package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 24/10/2017.
 */

class FenceAnchor extends Anchor {

    private Fence mFence;

    public FenceAnchor(Fence fence, @AnchorType int anchorType) {
        super(anchorType);
        mFence = fence;
    }

    public Fence getFence() {
        return mFence;
    }

    public void setFence(Fence fence) {
        mFence = fence;
    }

    @Override
    public int getLeft() {
        return mFence == null ? BOUND_UNSPECIFIED : mFence.getLeft();
    }

    @Override
    public int getTop() {
        return mFence == null ? BOUND_UNSPECIFIED : mFence.getTop();
    }

    @Override
    public int getRight() {
        return mFence == null ? BOUND_UNSPECIFIED : mFence.getRight();
    }

    @Override
    public int getBottom() {
        return mFence == null ? BOUND_UNSPECIFIED : mFence.getBottom();
    }
}
