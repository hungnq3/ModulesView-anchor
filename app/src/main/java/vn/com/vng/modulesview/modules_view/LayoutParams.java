package vn.com.vng.modulesview.modules_view;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by HungNQ on 17/10/2017.
 */

public class LayoutParams {

    /**
     * Special value for the height or width requested by a Module.
     * MATCH_PARENT means that the m wants to be as big as its parent,
     * minus the parent's padding, if anyodule.
     */
    public static final int MATCH_PARENT = -1;

    /**
     * Special value for the height or width requested by a Module.
     * WRAP_CONTENT means that the module wants to be just large enough to fit
     * its own internal content, taking its own padding into account.
     */
    public static final int WRAP_CONTENT = -2;

    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.INVISIBLE;
    public static final int GONE = View.GONE;

    @IntDef({BiasMode.SPAN, BiasMode.COLLAPSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BiasMode {
        int SPAN = 0;
        int COLLAPSE = 1;
    }

    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }


    private Module mModule;

    int mX, mY;
    int mWidthDimension, mHeightDimension;
    private Anchor mAnchorLeft, mAnchorTop, mAnchorRight, mAnchorBottom;

    int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    int mHorizontalBiasMode, mVerticalBiasMode;
    float mHorizontalBias, mVerticalBias;


    int mGravity;
    int mVisibility;


    public LayoutParams(@NonNull Module module) {
        mModule = module;
    }

    //-----------builder---------------------------------------
    public LayoutParams setX(int x) {
        mX = x;
        return this;
    }

    public LayoutParams setY(int y) {
        mY = y;
        return this;
    }

    public LayoutParams setWidthDimension(int widthDimension) {
        mWidthDimension = widthDimension;
        return this;
    }

    public LayoutParams setHeightDimension(int heightDimension) {
        mHeightDimension = heightDimension;
        return this;
    }

    public LayoutParams setDimensions(int widthDimension, int heightDimension) {
        mWidthDimension = widthDimension;
        mHeightDimension = heightDimension;
        return this;
    }

    public LayoutParams setPaddingLeft(int paddingLeft) {
        mPaddingLeft = paddingLeft;
        return this;

    }

    public LayoutParams setPaddingTop(int paddingTop) {
        if (mPaddingTop != paddingTop) {
            mPaddingTop = paddingTop;
        }
        return this;
    }

    public LayoutParams setPaddingRight(int paddingRight) {
        mPaddingRight = paddingRight;
        return this;

    }

    public LayoutParams setPaddingBottom(int paddingBottom) {
        mPaddingBottom = paddingBottom;
        return this;

    }

    public LayoutParams setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
        return this;
    }

    public LayoutParams setPadding(int padding) {
        mPaddingLeft = mPaddingTop = mPaddingRight = mPaddingBottom = padding;
        return this;
    }

    public LayoutParams setMarginLeft(int marginLeft) {
        mMarginLeft = marginLeft;
        return this;
    }

    public LayoutParams setMarginTop(int marginTop) {
        mMarginTop = marginTop;
        return this;
    }

    public LayoutParams setMarginRight(int marginRight) {
        mMarginRight = marginRight;
        return this;

    }

    public LayoutParams setMarginBottom(int marginBottom) {
        mMarginBottom = marginBottom;
        return this;
    }

    public LayoutParams setMargin(int margin) {
        mMarginLeft = mMarginTop = mMarginRight = mMarginBottom = margin;
        return this;
    }

    public LayoutParams setMargin(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginRight = right;
        mMarginTop = top;
        mMarginBottom = bottom;
        return this;
    }


    public LayoutParams setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public LayoutParams setVisibility(@Visibility int visibility) {
        mVisibility = visibility;
        return this;
    }


    @BiasMode
    public int getHorizontalBiasMode() {
        return mHorizontalBiasMode;
    }

    public void setHorizontalBiasMode(@BiasMode int horizontalBiasMode) {
        mHorizontalBiasMode = horizontalBiasMode;
    }

    @BiasMode
    public int getVerticalBiasMode() {
        return mVerticalBiasMode;
    }

    public void setVerticalBiasMode(@BiasMode int verticalBiasMode) {
        mVerticalBiasMode = verticalBiasMode;
    }

    public float getHorizontalBias() {
        return mHorizontalBias;
    }

    public void setHorizontalBias(int bias) {
        if (bias > 1f)
            mHorizontalBias = 1f;
        else if (bias < 0f)
            mHorizontalBias = 0f;
        else
            mHorizontalBias = bias;
    }

    public float getVerticalBias() {
        return mVerticalBias;
    }

    public void setVerticalBias(int bias) {
        if (bias > 1f)
            mVerticalBias = 1f;
        else if (bias < 0f)
            mVerticalBias = 0f;
        else
            mVerticalBias = bias;
    }

    //------------anchor region------------------------------------
    public LayoutParams anchorLeftToLeft(Module module) {
        if (module == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorLeft).setModule(module);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_LEFT);
        } else {
            mAnchorLeft = new ModuleAnchor(module, Anchor.ANCHOR_LEFT);
        }

        return this;
    }

    public LayoutParams anchorLeftToRight(Module module) {
        if (module == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorLeft).setModule(module);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorLeft = new ModuleAnchor(module, Anchor.ANCHOR_RIGHT);
        }

        return this;

    }

    public LayoutParams anchorLeftToLeft(Fence fence) {
        if (fence == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorLeft).setFence(fence);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_LEFT);
        } else {
            mAnchorLeft = new FenceAnchor(fence, Anchor.ANCHOR_LEFT);
        }

        return this;
    }

    public LayoutParams anchorLeftToRight(Fence fence) {
        if (fence == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorLeft).setFence(fence);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorLeft = new FenceAnchor(fence, Anchor.ANCHOR_RIGHT);
        }

        return this;
    }

    public LayoutParams anchorLeftToParent(Boolean anchorParent) {
        if (anchorParent) {
            if (mModule == null) {
                mAnchorLeft = null;
                return this;
            }
            if (mAnchorLeft instanceof ParentAnchor) {
                ((ParentAnchor) mAnchorLeft).setModule(mModule);
                mAnchorLeft.setAnchorType(Anchor.ANCHOR_LEFT);
            } else {
                mAnchorLeft = new ParentAnchor(mModule, Anchor.ANCHOR_LEFT);
            }
        }
        return this;
    }

    public LayoutParams anchorLeftToGuideLine(Guideline guideline) {
        if (guideline == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft instanceof GuideLineAnchor) {
            ((GuideLineAnchor) mAnchorLeft).setGuideline(guideline);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_LEFT);
        } else {
            mAnchorLeft = new GuideLineAnchor(guideline, Anchor.ANCHOR_LEFT);
        }
        return this;
    }


    public LayoutParams anchorTopToTop(Module module) {
        if (module == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorTop).setModule(module);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_TOP);
        } else {
            mAnchorTop = new ModuleAnchor(module, Anchor.ANCHOR_TOP);
        }
        return this;
    }

    public LayoutParams anchorTopToBottom(Module module) {
        if (module == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorTop).setModule(module);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorTop = new ModuleAnchor(module, Anchor.ANCHOR_BOTTOM);
        }

        return this;
    }

    public LayoutParams anchorTopToTop(Fence fence) {
        if (fence == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorTop).setFence(fence);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_TOP);
        } else {
            mAnchorTop = new FenceAnchor(fence, Anchor.ANCHOR_TOP);
        }
        return this;
    }

    public LayoutParams anchorTopToBottom(Fence fence) {
        if (fence == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorTop).setFence(fence);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorTop = new FenceAnchor(fence, Anchor.ANCHOR_BOTTOM);
        }

        return this;
    }


    public LayoutParams anchorTopToParent(Boolean anchorParent) {
        if (anchorParent) {
            if (mModule == null) {
                mAnchorTop = null;
                return this;
            }

            if (mAnchorTop instanceof ParentAnchor) {
                ((ParentAnchor) mAnchorTop).setModule(mModule);
                mAnchorTop.setAnchorType(Anchor.ANCHOR_TOP);
            } else {
                mAnchorTop = new ParentAnchor(mModule, Anchor.ANCHOR_TOP);
            }
        }
        return this;
    }

    public LayoutParams anchorTopToGuideLine(Guideline guideline) {
        if (guideline == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop instanceof GuideLineAnchor) {
            ((GuideLineAnchor) mAnchorTop).setGuideline(guideline);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_TOP);
        } else {
            mAnchorTop = new GuideLineAnchor(guideline, Anchor.ANCHOR_TOP);
        }
        return this;
    }

    public LayoutParams anchorRightToRight(Module module) {
        if (module == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorRight).setModule(module);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorRight = new ModuleAnchor(module, Anchor.ANCHOR_RIGHT);
        }

        return this;
    }

    public LayoutParams anchorRightToLeft(Module module) {
        if (module == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorRight).setModule(module);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_LEFT);
        } else {
            mAnchorRight = new ModuleAnchor(module, Anchor.ANCHOR_LEFT);
        }

        return this;
    }

    public LayoutParams anchorRightToRight(Fence fence) {
        if (fence == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorRight).setFence(fence);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorRight = new FenceAnchor(fence, Anchor.ANCHOR_RIGHT);
        }

        return this;
    }

    public LayoutParams anchorRightToLeft(Fence fence) {
        if (fence == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorRight).setFence(fence);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_LEFT);
        } else {
            mAnchorRight = new FenceAnchor(fence, Anchor.ANCHOR_LEFT);
        }

        return this;
    }


    public LayoutParams anchorRightToParent(Boolean anchorParent) {
        if (anchorParent) {
            if (mModule == null) {
                mAnchorRight = null;
                return this;
            }

            if (mAnchorRight instanceof ParentAnchor) {
                ((ParentAnchor) mAnchorRight).setModule(mModule);
                mAnchorRight.setAnchorType(Anchor.ANCHOR_RIGHT);
            } else {
                mAnchorRight = new ParentAnchor(mModule, Anchor.ANCHOR_RIGHT);
            }
        }
        return this;
    }

    public LayoutParams anchorRightToGuideLine(Guideline guideline) {
        if (guideline == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight instanceof GuideLineAnchor) {
            ((GuideLineAnchor) mAnchorRight).setGuideline(guideline);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorRight = new GuideLineAnchor(guideline, Anchor.ANCHOR_RIGHT);
        }
        return this;
    }

    public LayoutParams anchorBottomToBottom(Module module) {
        if (module == null) {
            mAnchorBottom = null;
            return this;
        }


        if (mAnchorBottom instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorBottom).setModule(module);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorBottom = new ModuleAnchor(module, Anchor.ANCHOR_BOTTOM);
        }

        return this;
    }


    public LayoutParams anchorBottomToTop(Module module) {
        if (module == null) {
            mAnchorBottom = null;
            return this;
        }

        if (mAnchorBottom instanceof ModuleAnchor) {
            ((ModuleAnchor) mAnchorBottom).setModule(module);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_TOP);
        } else {
            mAnchorBottom = new ModuleAnchor(module, Anchor.ANCHOR_TOP);
        }

        return this;
    }

    public LayoutParams anchorBottomToBottom(Fence fence) {
        if (fence == null) {
            mAnchorBottom = null;
            return this;
        }


        if (mAnchorBottom instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorBottom).setFence(fence);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorBottom = new FenceAnchor(fence, Anchor.ANCHOR_BOTTOM);
        }

        return this;
    }


    public LayoutParams anchorBottomToTop(Fence fence) {
        if (fence == null) {
            mAnchorBottom = null;
            return this;
        }

        if (mAnchorBottom instanceof FenceAnchor) {
            ((FenceAnchor) mAnchorBottom).setFence(fence);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_TOP);
        } else {
            mAnchorBottom = new FenceAnchor(fence, Anchor.ANCHOR_TOP);
        }

        return this;
    }

    public LayoutParams anchorBottomToParent(Boolean anchorParent) {
        if (anchorParent) {
            if (mModule == null) {
                mAnchorBottom = null;
                return this;
            }

            if (mAnchorBottom instanceof ParentAnchor) {
                ((ParentAnchor) mAnchorBottom).setModule(mModule);
                mAnchorBottom.setAnchorType(Anchor.ANCHOR_BOTTOM);
            } else {
                mAnchorBottom = new ParentAnchor(mModule, Anchor.ANCHOR_BOTTOM);
            }

        }
        return this;
    }

    public LayoutParams anchorBottomToGuideLine(GuideLineAnchor anchor) {
        if (mModule == null) {
            mAnchorBottom = null;
            return this;
        }
        mAnchorBottom = anchor;
        return this;
    }


    public LayoutParams anchorBottomToGuideLine(Guideline guideline) {
        if (guideline == null) {
            mAnchorBottom = null;
            return this;
        }

        if (mAnchorBottom instanceof GuideLineAnchor) {
            ((GuideLineAnchor) mAnchorBottom).setGuideline(guideline);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorBottom = new GuideLineAnchor(guideline, Anchor.ANCHOR_BOTTOM);
        }
        return this;
    }


    //------------------------------------------------


//-----------------------------------------------------------------------------


    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }


    public int getWidthDimension() {
        return mWidthDimension;
    }


    public int getHeightDimension() {
        return mHeightDimension;
    }

    public int getPaddingLeft() {
        return mPaddingLeft;
    }


    public int getPaddingTop() {
        return mPaddingTop;
    }


    public int getPaddingRight() {
        return mPaddingRight;
    }


    public int getPaddingBottom() {
        return mPaddingBottom;
    }


    public int getMarginLeft() {
        return mMarginLeft;
    }


    public int getMarginTop() {
        return mMarginTop;
    }


    public int getMarginRight() {
        return mMarginRight;
    }


    public int getMarginBottom() {
        return mMarginBottom;
    }


    public int getGravity() {
        return mGravity;
    }

    public
    @Visibility
    int getVisibility() {
        return mVisibility;
    }


    private boolean hasAnchorLeft() {
        return (mAnchorLeft != null);
    }

    private boolean hasAnchorRight() {
        return (mAnchorRight != null);
    }

    private boolean hasAnchorTop() {
        return (mAnchorTop != null);

    }

    private boolean hasAnchorBottom() {
        return (mAnchorBottom != null);
    }


    private int getAnchorLeft() {
        int left = Module.BOUND_UNSPECIFIED;
        if (hasAnchorLeft()) {
            left = mAnchorLeft.getAnchorValue();
            if (left == Anchor.BOUND_UNKNOWN)
                left = Module.BOUND_UNKNOWN;
            else
                left += mMarginLeft;
        } else if (mWidthDimension == MATCH_PARENT || mX > 0) {
            left = mX + mMarginLeft;
            if (mX == 0 && mModule.getParent() != null)
                left += mModule.getParent().getPaddingLeft();
        }
        return left;
    }

    private int getAnchorRight() {
        int right = Module.BOUND_UNSPECIFIED;
        if (hasAnchorRight()) {
            right = mAnchorRight.getAnchorValue();
            if (right == Anchor.BOUND_UNKNOWN)
                right = Module.BOUND_UNKNOWN;
            else
                right -= mMarginRight;
        } else if (mWidthDimension == MATCH_PARENT) {
            Parent parent = mModule.getParent();
            if (parent != null) {
                if (parent.getWidthMeasureMode() == View.MeasureSpec.UNSPECIFIED)
                    right = Module.BOUND_UNSPECIFIED;
                else
                    right = Math.max(parent.getWidthMeasureSize() - mModule.getParent().getPaddingRight(), 0) - mMarginLeft;
            }
        }
        return right;
    }

    private int getAnchorTop() {
        int top = Module.BOUND_UNSPECIFIED;
        if (hasAnchorTop()) {
            top = mAnchorTop.getAnchorValue();
            if (top == Anchor.BOUND_UNKNOWN)
                top = Module.BOUND_UNKNOWN;
            else
                top += mMarginTop;
        } else if (mHeightDimension == MATCH_PARENT || mY > 0) {
            top = mY + mMarginTop;
            if (mY == 0 && mModule.getParent() != null)
                top += mModule.getParent().getPaddingTop();
        }

        return top;
    }


    private int getAnchorBottom() {
        int bottom = Module.BOUND_UNSPECIFIED;
        if (hasAnchorBottom()) {
            bottom = mAnchorBottom.getAnchorValue();
            if (bottom == Anchor.BOUND_UNKNOWN)
                bottom = Module.BOUND_UNKNOWN;
            else
                bottom -= mMarginBottom;
        } else if (mHeightDimension == MATCH_PARENT) {
            Parent parent = mModule.getParent();
            if (parent != null) {
                if (parent.getHeightMeasureMode() == View.MeasureSpec.UNSPECIFIED)
                    bottom = Module.BOUND_UNSPECIFIED;
                else
                    bottom = Math.max(parent.getHeightMeasureSize() - mModule.getParent().getPaddingBottom(), 0) - mMarginBottom;
            }
        }
        return bottom;
    }


    protected void externalMeasure() {
        //measure horizontal
        int left = getAnchorLeft();
        int right = getAnchorRight();

        if (mWidthDimension >= 0 && mVisibility != GONE) {
            if (left == Module.BOUND_UNSPECIFIED && right != Module.BOUND_UNSPECIFIED) {
                left = right - mWidthDimension;
            } else if (left != Module.BOUND_UNSPECIFIED && right == Module.BOUND_UNSPECIFIED) {
                right = left + mWidthDimension;
            } else if (left == Module.BOUND_UNSPECIFIED && right == Module.BOUND_UNSPECIFIED) {
                left = mMarginLeft + (mModule.getParent() != null ? mModule.getParent().getPaddingLeft() : 0);
                right = left + mWidthDimension;
            }
        }

        //measure vertical
        int top = getAnchorTop();
        int bottom = getAnchorBottom();

        if (mHeightDimension >= 0 && mVisibility != GONE)

        {
            if (top == Module.BOUND_UNSPECIFIED && bottom != Module.BOUND_UNSPECIFIED) {
                top = bottom - mHeightDimension;
            } else if (top != Module.BOUND_UNSPECIFIED && bottom == Module.BOUND_UNSPECIFIED) {
                bottom = top + mHeightDimension;
            } else if (top == Module.BOUND_UNSPECIFIED && bottom == Module.BOUND_UNSPECIFIED) {
                top = mMarginTop + (mModule.getParent() != null ? mModule.getParent().getPaddingTop() : 0);
                bottom = top + mHeightDimension;
            }
        }

        mModule.setBounds(left, top, right, bottom);
    }
}


