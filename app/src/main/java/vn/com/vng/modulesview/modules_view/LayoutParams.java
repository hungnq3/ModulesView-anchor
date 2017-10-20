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


    private Module mModule;

    int mX, mY;
    int mWidthDimension, mHeightDimension;
    private Anchor mAnchorLeft, mAnchorTop, mAnchorRight, mAnchorBottom;

    int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;


    int mGravity;
    int mVisibility;

    private boolean mDirty;

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


    public boolean hasAnchorLeft() {
        return (mAnchorLeft != null);
    }

    public boolean hasAnchorRight() {
        return (mAnchorRight != null);
    }

    public boolean hasAnchorTop() {
        return (mAnchorTop != null);

    }

    public boolean hasAnchorBottom() {
        return (mAnchorBottom != null);
    }


    int getAnchorLeft() {
        int left = Module.BOUND_UNSPECIFIED;
        if (hasAnchorLeft()) {
            left = mAnchorLeft.getAnchorValue();
            if (left == Anchor.BOUND_UNKNOWN)
                left = Module.BOUND_UNKNOWN;
            else
                left += mMarginLeft;
        } else if (mWidthDimension == MATCH_PARENT || mX > 0) {
            left = mX + mMarginLeft;
        }

        return left;
    }

    int getAnchorRight() {
        int right = Module.BOUND_UNSPECIFIED;
        if (hasAnchorRight()) {
            right = mAnchorRight.getAnchorValue();
            if (right == Anchor.BOUND_UNKNOWN)
                right = Module.BOUND_UNKNOWN;
            else
                right -= mMarginRight;
        } else if (mWidthDimension == MATCH_PARENT) {
            ModulesView parent = mModule.getParent();
            if (parent != null) {
                if (parent.mWidthMeasureMode == View.MeasureSpec.UNSPECIFIED)
                    right = Module.BOUND_UNSPECIFIED;
                else
                    right = Math.max(parent.mWidthMeasureSize - mModule.getParent().getPaddingRight(), 0) - mMarginLeft;
            }
        }
        return right;
    }

    int getAnchorTop() {

        int top = Module.BOUND_UNSPECIFIED;
        if (hasAnchorTop()) {
            top = mAnchorTop.getAnchorValue();
            if (top == Anchor.BOUND_UNKNOWN)
                top = Module.BOUND_UNKNOWN;
            else
                top += mMarginTop;
        } else if (mWidthDimension == MATCH_PARENT || mY > 0) {
            top = mY + mMarginTop;
        }

        return top;
    }


    int getAnchorBottom() {
        int bottom = Module.BOUND_UNSPECIFIED;
        if (hasAnchorBottom()) {
            bottom = mAnchorBottom.getAnchorValue();
            if (bottom == Anchor.BOUND_UNKNOWN)
                bottom = Module.BOUND_UNKNOWN;
            else
                bottom -= mMarginBottom;
        } else if (mHeightDimension == MATCH_PARENT) {
            ModulesView parent = mModule.getParent();
            if (parent != null) {
                if (parent.mWidthMeasureMode == View.MeasureSpec.UNSPECIFIED)
                    bottom = Module.BOUND_UNSPECIFIED;
                else
                    bottom = Math.max(parent.mHeightMeasureSize - mModule.getParent().getPaddingBottom(), 0) - mMarginBottom;
            }
        }
        return bottom;
    }


    protected void externalMeasure() {
        //measure horizontal
        int left = getAnchorLeft();
        int right = getAnchorRight();

        if (mWidthDimension >= 0) {
            if (left == Module.BOUND_UNSPECIFIED && right != Module.BOUND_UNSPECIFIED) {
                left = right - mWidthDimension;
            } else if (left != Module.BOUND_UNSPECIFIED && right == Module.BOUND_UNSPECIFIED) {
                right = left + mWidthDimension;
            } else if (left == Module.BOUND_UNSPECIFIED && right == Module.BOUND_UNSPECIFIED) {
                left = mMarginLeft;
                right = left + mWidthDimension;
            }
        }

        //measure vertical
        int top = getAnchorTop();
        int bottom = getAnchorBottom();

        if (mHeightDimension >= 0) {
            if (top == Module.BOUND_UNSPECIFIED && bottom != Module.BOUND_UNSPECIFIED) {
                    top = bottom - mHeightDimension;
            } else if (top != Module.BOUND_UNSPECIFIED && bottom == Module.BOUND_UNSPECIFIED) {
                bottom = top + mHeightDimension;
            } else if (top == Module.BOUND_UNSPECIFIED && bottom == Module.BOUND_UNSPECIFIED) {
                top = mMarginTop;
                bottom = top + mHeightDimension;
            }
        }

        mModule.setBounds(left, top, right, bottom);
    }
}


