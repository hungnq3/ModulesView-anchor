package vn.com.vng.modulesview.modules_view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by HungNQ on 17/10/2017.
 */

public class ModuleParams {

    //if a bound (left, top, right, bottom) can not determine , let put BOUND_WRAP_CONTENT.
    //Depend on each module, bounds will be defined when the module call configModule()
    public static final int WRAP_CONTENT = -1;
    public static final int MATCH_PARENT = -2;


    int mX, mY;
    int mWidthDimension, mHeightDimension;
    private Module mAnchorLeft, mAnchorTop, mAnchorRight, mAnchorBottom;
    private boolean mAnchorParentLeft, mAnchorParentTop, mAnchorParentRight, mAnchorParentBottom;

    int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    Drawable mBackgroundDrawable;


    //-----------builder---------------------------------------
    public ModuleParams setX(int x) {
        mX = x;
        return this;
    }

    public ModuleParams setY(int y) {
        mY = y;
        return this;
    }

    public ModuleParams setWidthDimension(int widthDimension) {
        mWidthDimension = widthDimension;
        return this;
    }

    public ModuleParams setHeightDimension(int heightDimension) {
        mHeightDimension = heightDimension;
        return this;
    }

    public ModuleParams setDimensions(int widthDimension, int heightDimension){
        mWidthDimension = widthDimension;
        mHeightDimension = heightDimension;
        return this;
    }

    public ModuleParams setPaddingLeft(int paddingLeft) {
        mPaddingLeft = paddingLeft;
        return this;

    }

    public ModuleParams setPaddingTop(int paddingTop) {
        mPaddingTop = paddingTop;
        return this;
    }

    public ModuleParams setPaddingRight(int paddingRight) {
        mPaddingRight = paddingRight;
        return this;

    }

    public ModuleParams setPaddingBottom(int paddingBottom) {
        mPaddingBottom = paddingBottom;
        return this;

    }

    public ModuleParams setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
        return this;
    }

    public ModuleParams setPadding(int padding) {
        mPaddingLeft = mPaddingTop = mPaddingRight = mPaddingBottom = padding;
        return this;
    }

    public ModuleParams setMarginLeft(int marginLeft) {
        mMarginLeft = marginLeft;
        return this;
    }

    public ModuleParams setMarginTop(int marginTop) {
        mMarginTop = marginTop;
        return this;
    }

    public ModuleParams setMarginRight(int marginRight) {
        mMarginRight = marginRight;
        return this;

    }

    public ModuleParams setMarginBottom(int marginBottom) {
        mMarginBottom = marginBottom;
        return this;
    }

    public ModuleParams setMargin(int margin) {
        mMarginLeft = mMarginTop = mMarginRight = mMarginBottom = margin;
        return this;
    }

    public ModuleParams setMargin(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginRight = right;
        mMarginTop = top;
        mMarginBottom = bottom;
        return this;
    }


    public ModuleParams setBackgroundColor(int backgroundColor) {
        clearBackground();
        mBackgroundDrawable = new ColorDrawable(backgroundColor);
        return this;
    }


    public ModuleParams setBackgroundDrawable(Drawable backgroundDrawable) {
        clearBackground();
        mBackgroundDrawable = backgroundDrawable;
        return this;
    }

    public ModuleParams setBackgroundBitmap(Bitmap backgroundBitmap) {
        clearBackground();
        mBackgroundDrawable = new BitmapDrawable(null, backgroundBitmap);
        return this;
    }

    public ModuleParams clearBackground() {
        mBackgroundDrawable = null;
        return this;

    }


    public ModuleParams anchorLeftToParent(Boolean anchorParent) {
        mAnchorParentLeft = anchorParent;
        if (anchorParent)
            mAnchorLeft = null;
        return this;
    }

    public ModuleParams anchorTopToParent(Boolean anchorParent) {
        mAnchorParentTop = anchorParent;
        if (anchorParent)
            mAnchorTop = null;
        return this;
    }

    public ModuleParams anchorRightToParent(Boolean anchorParent) {
        mAnchorParentRight = anchorParent;
        if (anchorParent)
            mAnchorRight = null;
        return this;
    }

    public ModuleParams anchorLeftTo(Module module) {
        mAnchorLeft = module;
        if (module != null)
            mAnchorParentLeft = false;
        return this;
    }

    public ModuleParams anchorTopTo(Module module) {
        mAnchorTop = module;
        if (module != null)
            mAnchorParentTop = false;
        return this;
    }

    public ModuleParams anchorRightTo(Module module) {
        mAnchorRight = module;
        if (module != null)
            mAnchorParentRight = false;
        return this;
    }

    public ModuleParams anchorBottomTo(Module module) {
        mAnchorBottom = module;
        if (module != null)
            mAnchorParentBottom = false;
        return this;
    }


    public ModuleParams anchorBottomToParent(Boolean anchorParent) {
        mAnchorParentBottom = anchorParent;
        if (anchorParent)
            mAnchorBottom = null;
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


    public Drawable getBackgroundDrawable() {
        return mBackgroundDrawable;
    }


    public Module getAnchorLeft() {
        return mAnchorLeft;
    }

    public Module getAnchorTop() {
        return mAnchorTop;
    }

    public Module getAnchorRight() {
        return mAnchorRight;
    }

    public Module getAnchorBottom() {
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
        return mAnchorLeft != null || mAnchorParentLeft;
    }

    public boolean hasAnchorRight() {
        return mAnchorRight != null || mAnchorParentRight || mWidthDimension == ModuleParams.MATCH_PARENT;
    }

    public boolean hasAnchorTop() {
        return mAnchorTop != null || mAnchorParentTop;

    }

    public boolean hasAnchorBottom() {
        return mAnchorBottom != null || mAnchorParentBottom || mHeightDimension == ModuleParams.MATCH_PARENT;
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
            if (getAnchorLeft() != null) {
                ModuleParams anchorParams = getAnchorLeft().getModuleParams();
                left = getAnchorLeft().getRight() + anchorParams.mMarginRight + mMarginLeft;
            } else //anchor parent
                left = mMarginLeft;
        } else { //default
            left = mX + mMarginLeft;
        }

        if (hasAnchorRight) {
            if (getAnchorRight() != null) {
                ModuleParams anchorParams = getAnchorRight().getModuleParams();
                right = getAnchorRight().getLeft() - anchorParams.mMarginLeft - mMarginRight;
            } else //anchor parent
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
            if (getAnchorTop() != null) {
                ModuleParams anchorParams = getAnchorTop().getModuleParams();
                top = getAnchorTop().getBottom() + anchorParams.mMarginBottom + mMarginTop;
            } else
                top = mMarginTop;
        } else { //default
            top = mY + mMarginTop;
        }

        if (hasAnchorBottom) {
            if (getAnchorBottom() != null) {
                ModuleParams anchorParams = getAnchorBottom().getModuleParams();
                bottom = getAnchorBottom().getTop() - anchorParams.mMarginTop - mMarginBottom;
            } else
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


