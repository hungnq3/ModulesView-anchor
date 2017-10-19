package vn.com.vng.modulesview.modules_view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
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

    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 1;
    public static final int GONE = 2;

//    @IntDef({GravityCompat.LEFT, GravityCompat.RIGHT, GravityCompat.TOP, GravityCompat.BOTTOM, GravityCompat.CENTER, GravityCompat.CENTER_HORIZONTAL, GravityCompat.CENTER_VERTICAL})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface Gravity {
//    }

    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    private boolean mDirty;


    int mX, mY;
    int mWidthDimension, mHeightDimension;
    private Anchor mAnchorLeft, mAnchorTop, mAnchorRight, mAnchorBottom;
    private boolean mAnchorParentLeft, mAnchorParentTop, mAnchorParentRight, mAnchorParentBottom;

    int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;


    int mGravity;
    int mVisibility;

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
            mDirty = true;
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

    public LayoutParams anchorLeftToParent(Boolean anchorParent) {
        mAnchorParentLeft = anchorParent;
        if (anchorParent)
            mAnchorLeft = null;
        return this;
    }

    public LayoutParams anchorTopToParent(Boolean anchorParent) {
        mAnchorParentTop = anchorParent;
        if (anchorParent)
            mAnchorTop = null;
        return this;
    }

    public LayoutParams anchorRightToParent(Boolean anchorParent) {
        mAnchorParentRight = anchorParent;
        if (anchorParent)
            mAnchorRight = null;
        return this;
    }


    public LayoutParams anchorBottomToParent(Boolean anchorParent) {
        mAnchorParentBottom = anchorParent;
        if (anchorParent)
            mAnchorBottom = null;
        return this;
    }

    public LayoutParams anchorLeftToLeft(Module module) {
        if (module == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft == null) {
            mAnchorLeft = new Anchor(module, Anchor.ANCHOR_LEFT);
        } else {
            mAnchorLeft.setModule(module);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_LEFT);
        }

        mAnchorParentLeft = false;
        return this;
    }

    public LayoutParams anchorLeftToRight(Module module) {
        if (module == null) {
            mAnchorLeft = null;
            return this;
        }

        if (mAnchorLeft == null) {
            mAnchorLeft = new Anchor(module, Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorLeft.setModule(module);
            mAnchorLeft.setAnchorType(Anchor.ANCHOR_RIGHT);
        }

        mAnchorParentLeft = false;
        return this;
    }

    public LayoutParams anchorTopToTop(Module module) {
        if (module == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop == null) {
            mAnchorTop = new Anchor(module, Anchor.ANCHOR_TOP);
        } else {
            mAnchorTop.setModule(module);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_TOP);
        }

        mAnchorParentTop = false;
        return this;
    }

    public LayoutParams anchorTopToBottom(Module module) {
        if (module == null) {
            mAnchorTop = null;
            return this;
        }

        if (mAnchorTop == null) {
            mAnchorTop = new Anchor(module, Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorTop.setModule(module);
            mAnchorTop.setAnchorType(Anchor.ANCHOR_BOTTOM);
        }

        mAnchorParentTop = false;
        return this;
    }

    public LayoutParams anchorRightToRight(Module module) {
        if (module == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight == null) {
            mAnchorRight = new Anchor(module, Anchor.ANCHOR_RIGHT);
        } else {
            mAnchorRight.setModule(module);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_RIGHT);
        }

        mAnchorParentRight = false;
        return this;
    }

    public LayoutParams anchorRightToLeft(Module module) {
        if (module == null) {
            mAnchorRight = null;
            return this;
        }

        if (mAnchorRight == null) {
            mAnchorRight = new Anchor(module, Anchor.ANCHOR_LEFT);
        } else {
            mAnchorRight.setModule(module);
            mAnchorRight.setAnchorType(Anchor.ANCHOR_LEFT);
        }

        mAnchorParentRight = false;
        return this;
    }

    public LayoutParams anchorBottomToBottom(Module module) {
        if (module == null) {
            mAnchorBottom = null;
            return this;
        }

        if (mAnchorBottom == null) {
            mAnchorBottom = new Anchor(module, Anchor.ANCHOR_BOTTOM);
        } else {
            mAnchorBottom.setModule(module);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_BOTTOM);
        }

        mAnchorParentBottom = false;
        return this;
    }


    public LayoutParams anchorBottomToTop(Module module) {
        if (module == null) {
            mAnchorBottom = null;
            return this;
        }

        if (mAnchorBottom == null) {
            mAnchorBottom = new Anchor(module, Anchor.ANCHOR_TOP);
        } else {
            mAnchorBottom.setModule(module);
            mAnchorBottom.setAnchorType(Anchor.ANCHOR_TOP);
        }

        mAnchorParentBottom = false;
        return this;
    }



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


    Anchor getAnchorLeft() {
        return mAnchorLeft;
    }

    Anchor getAnchorTop() {
        return mAnchorTop;
    }

    Anchor getAnchorRight() {
        return mAnchorRight;
    }

    Anchor getAnchorBottom() {
        return mAnchorBottom;
    }

    public Boolean isAnchorParentLeft() {
        return mAnchorParentLeft;
    }

    public Boolean isAnchorParentTop() {
        return mAnchorParentTop;
    }


    public Boolean isAnchorParentRight() {
        return mAnchorParentRight;
    }


    public Boolean isAnchorParentBottom() {
        return mAnchorParentBottom;
    }


    public boolean hasAnchorLeft() {
        return (mAnchorLeft != null && mAnchorLeft.isValid()) || mAnchorParentLeft;
    }

    public boolean hasAnchorRight() {
        return (mAnchorRight != null && mAnchorRight.isValid()) || mAnchorParentRight;
    }

    public boolean hasAnchorTop() {
        return (mAnchorTop != null && mAnchorTop.isValid()) || mAnchorParentTop;

    }

    public boolean hasAnchorBottom() {
        return (mAnchorBottom != null && mAnchorBottom.isValid())|| mAnchorParentBottom;
    }


    private Anchor getAnchor(@Anchor.AnchorType int type) {
        switch (type) {
            case Anchor.ANCHOR_LEFT:
                return mAnchorLeft;
            case Anchor.ANCHOR_TOP:
                return mAnchorTop;
            case Anchor.ANCHOR_RIGHT:
                return mAnchorRight;
            case Anchor.ANCHOR_BOTTOM:
                return mAnchorBottom;
        }
        return null;
    }

    protected void externalMeasure(Module module, int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        //measure horizontal
        int left = Module.BOUND_UNSPECIFIED;
        int right = Module.BOUND_UNSPECIFIED;
        boolean hasAnchorLeft = hasAnchorLeft();
        boolean hasAnchorRight = hasAnchorRight();
        if (hasAnchorLeft) {
            if (getAnchorLeft() != null && getAnchorLeft().isValid()) {
                left = getAnchorLeft().getAnchorValue() + mMarginLeft;
            } else //anchor parent
                left = mMarginLeft;
        } else if (mWidthDimension == MATCH_PARENT) {
            left = mMarginLeft;
        }

        if (hasAnchorRight) {
            if (getAnchorRight() != null && getAnchorRight().isValid()) {
                right = getAnchorRight().getAnchorValue() - mMarginRight;
            } else //anchor parent
                right = parentWidth - mMarginRight;
        } else if (mWidthDimension == MATCH_PARENT) {
            right = parentWidth - mMarginRight;
        }

        if (mWidthDimension >= 0) {
            if (!hasAnchorLeft && hasAnchorRight) {
                if (mX == 0)
                    left = right - mWidthDimension;
            } else if (hasAnchorLeft && !hasAnchorRight) {
                right = left + mWidthDimension;
            } else if (!hasAnchorLeft && !hasAnchorRight) {
                left = mX + mMarginLeft;
                right = left + mWidthDimension;
            }
        }

        //measure vertical
        int top = Module.BOUND_UNSPECIFIED;
        int bottom = Module.BOUND_UNSPECIFIED;

        boolean hasAnchorTop = hasAnchorTop();
        boolean hasAnchorBottom = hasAnchorBottom();
        if (hasAnchorTop) {
            if (getAnchorTop() != null && getAnchorTop().isValid()) {
                top = getAnchorTop().getAnchorValue() + mMarginTop;
            } else
                top = mMarginTop;
        } else if (mHeightDimension == MATCH_PARENT) {
            top = mMarginTop;
        }

        if (hasAnchorBottom) {
            if (getAnchorBottom() != null && getAnchorBottom().isValid()) {
                bottom = getAnchorBottom().getAnchorValue() - mMarginBottom;
            } else
                bottom = parentHeight - mMarginBottom;
        } else if (mHeightDimension == MATCH_PARENT) {
            bottom = parentHeight - mMarginBottom;
        }


        if (mHeightDimension >= 0) {
            if (!hasAnchorTop && hasAnchorBottom) {
                if (mY == 0)
                    top = bottom - mHeightDimension;
            } else if (hasAnchorTop && !hasAnchorBottom) {
                bottom = top + mHeightDimension;
            } else if (!hasAnchorTop && !hasAnchorBottom) {
                top = mY + mMarginTop;
                bottom = top + mHeightDimension;
            }
        }

        module.setBounds(left, top, right, bottom);
    }
}


