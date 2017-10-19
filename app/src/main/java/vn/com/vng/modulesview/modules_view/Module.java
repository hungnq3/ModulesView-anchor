package vn.com.vng.modulesview.modules_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HungNQ on 08/09/2017.
 */

public class Module {
    private static final long LONG_CLICK_TIME = 500;

    public static final int DIMENSION_MODE_FIXED = 1;
    public static final int DIMENSION_MODE_MAX = 2;
    public static final int DIMENSION_MODE_UNSPECIFIED = 3;

    public static final int DIMENSION_UNSPECIFIED = Integer.MIN_VALUE;
    public static final int BOUND_UNSPECIFIED = Integer.MIN_VALUE;

    //stuff
    protected Context mContext;
    private ModulesView mParent;
    private LayoutParams mParams;

    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;
    private int mContentWidth, mContentHeight;
    private int mContentLeft, mContentTop, mContentRight, mContentBottom;

    Drawable mBackgroundDrawable;

    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private OnTouchListener mOnTouchListener;

    public Module() {
        mParams = new LayoutParams();
    }

    public Module(LayoutParams params) {
        mParams = params;
        if (mParams == null)
            mParams = new LayoutParams();
    }

    public ModulesView getParent() {
        return mParent;
    }

    void setParent(ModulesView parent) {
        mParent = parent;
        mContext = mParent != null ? mParent.getContext() : null;
    }

    public Context getContext() {
        return mContext;
    }

    public LayoutParams getLayoutParams() {
        return mParams;
    }

    //-------------------getter & setter----------


    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getContentWidth() {
        return mContentWidth;
    }

    public int getContentHeight() {
        return mContentHeight;
    }

    protected int getLeft() {
        return mLeft;
    }

    protected int getTop() {
        return mTop;
    }

    protected int getRight() {
        return mRight;
    }

    protected int getBottom() {
        return mBottom;
    }


    public void setBackgroundColor(int backgroundColor) {
        clearBackground();
        mBackgroundDrawable = new ColorDrawable(backgroundColor);
    }


    public void setBackgroundDrawable(Drawable backgroundDrawable) {
        clearBackground();
        mBackgroundDrawable = backgroundDrawable;
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap) {
        clearBackground();
        mBackgroundDrawable = new BitmapDrawable(null, backgroundBitmap);
    }

    public Drawable getBackgroundDrawable() {
        return mBackgroundDrawable;
    }


    public void clearBackground() {
        mBackgroundDrawable = null;
    }


    protected void setBounds(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
        mWidth = (mLeft != BOUND_UNSPECIFIED && mRight != BOUND_UNSPECIFIED) ? mRight - mLeft : DIMENSION_UNSPECIFIED;
        mHeight = (mTop != BOUND_UNSPECIFIED && mBottom != BOUND_UNSPECIFIED) ? mBottom - mTop : DIMENSION_UNSPECIFIED;
    }

    //-----------------listener-------------------------------------
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }


    //--------------config region-------------

    final void measure(int parentMeasureWidthSpec, int parentMeasureHeightSpec) {
        //clear layout state
        setBounds(BOUND_UNSPECIFIED, BOUND_UNSPECIFIED, BOUND_UNSPECIFIED, BOUND_UNSPECIFIED);
        setContentDimensions(0, 0);

        //step 1: external measure
        mParams.externalMeasure(this, parentMeasureWidthSpec, parentMeasureHeightSpec);


        //step 2: internal measure
        boolean needToUpdateDimensions = mWidth == DIMENSION_UNSPECIFIED || mHeight == DIMENSION_UNSPECIFIED;

        int width, widthMode;
        int height, heightMode;

        if (mWidth == DIMENSION_UNSPECIFIED) {
            width = getRemainWidth(parentMeasureWidthSpec);
            widthMode = View.MeasureSpec.getMode(parentMeasureWidthSpec) == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_MAX;
        } else {
            width = mWidth - mParams.getPaddingLeft() - mParams.getPaddingRight();
            widthMode = DIMENSION_MODE_FIXED;
        }

        if (mHeight == DIMENSION_UNSPECIFIED) {
            height = getRemainHeight(parentMeasureHeightSpec);
            heightMode = View.MeasureSpec.getMode(parentMeasureHeightSpec) == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_MAX;
        } else {
            height = mHeight - mParams.getPaddingTop() - mParams.getPaddingBottom();
            heightMode = DIMENSION_MODE_FIXED;
        }

        onMeasureContent(width, widthMode, height, heightMode);

        //step 3: re update dimensions if needed
        if (needToUpdateDimensions)
            updateUnspecifiedBounds(mContentWidth, mContentHeight);
    }


    private int getRemainWidth(int parentMeasureWidthSpec) {
        int size = mRight != BOUND_UNSPECIFIED ? mRight : View.MeasureSpec.getSize(parentMeasureWidthSpec) - mParams.getMarginRight();
        size -= mLeft != BOUND_UNSPECIFIED ? mLeft : mParams.getMarginLeft();
        return size - mParams.getPaddingRight() - mParams.getPaddingLeft();
    }

    private int getRemainHeight(int parentMeasureHeightSpec) {
        int size = (mBottom != BOUND_UNSPECIFIED) ? mBottom : View.MeasureSpec.getSize(parentMeasureHeightSpec) - mParams.getMarginBottom();
        size -= (mTop != BOUND_UNSPECIFIED) ? mTop : mParams.getMarginTop();
        return size - mParams.getPaddingTop() - mParams.getPaddingTop();
    }

    public void onMeasureContent(int width, int widthMode, int height, int heightMode) {
        setContentDimensions(width, height);
    }

    protected final void setContentDimensions(int contentWidth, int contentHeight) {
        mContentWidth = contentWidth < 0 ? 0 : contentWidth;
        mContentHeight = contentHeight < 0 ? 0 : contentHeight;
    }

    private final void updateUnspecifiedBounds(int width, int height) {
        if (mLeft == BOUND_UNSPECIFIED && mRight == BOUND_UNSPECIFIED) {
            mWidth = width + mParams.getPaddingLeft() + mParams.getPaddingRight();
            mLeft = mParams.getMarginLeft() + mParams.getX();
            mRight = mLeft + mWidth;
        } else if (mLeft == BOUND_UNSPECIFIED) {
            mWidth = width + mParams.getPaddingLeft() + mParams.getPaddingRight();
            mLeft = mRight - mWidth;
        } else if (mRight == BOUND_UNSPECIFIED) {
            mWidth = width + mParams.getPaddingLeft() + mParams.getPaddingRight();
            mRight = mLeft + mWidth;
        }

        if (mTop == BOUND_UNSPECIFIED && mBottom == BOUND_UNSPECIFIED) {
            mHeight = height + mParams.getPaddingTop() + mParams.getPaddingBottom();
            mTop = mParams.getMarginTop() + mParams.getY();
            mBottom = mTop + mHeight;
        } else if (mTop == BOUND_UNSPECIFIED) {
            mHeight = height + mParams.getPaddingTop() + mParams.getPaddingBottom();
            mTop = mBottom - mHeight;
        } else if (mBottom == BOUND_UNSPECIFIED) {
            mHeight = height + mParams.getPaddingTop() + mParams.getPaddingBottom();
            mBottom = mTop + mHeight;
        }
    }


    public void onPostMeasured() {
    }




    final void layout(int left, int top, int right, int bottom) {
        int oldWidth = mWidth;
        int oldHeight = mHeight;
        setBounds(left, top, right, bottom);
        boolean changed = oldWidth != mWidth && oldHeight != mHeight;
        onLayout(changed, left, top, right, bottom);

        configContentRegion();
        configModule();
    }

    void onLayout(boolean layoutChanged, int left, int top, int right, int bottom) {

    }

    public void configModule() {

    }

    private void configContentRegion() {
        if (mWidth <= 0 || mHeight <= 0)
            return;
        mContentLeft = mLeft + mParams.getPaddingLeft();
        mContentTop = mTop + mParams.getPaddingTop();
        mContentRight = mContentLeft + mContentWidth;
        mContentBottom = mContentTop + mContentHeight;

        int gravity = mParams.getGravity();
        if (GravityCompat.isNone(gravity) || (GravityCompat.isHorizontalLeft(gravity) && GravityCompat.isVerticalTop(gravity)))
            return;
        int dWidth = mWidth - mContentWidth;
        int dHeight = mHeight - mContentHeight;
        if (dWidth != 0) {
            if (GravityCompat.isHorizontalRight(gravity)) {
                mContentLeft += dWidth;
                mContentRight += dHeight;
            } else if (GravityCompat.isHorizontalCenter(gravity)) {
                int temp = dWidth / 2;
                mContentLeft += temp;
                mContentRight += temp;
            }
        }
        if (dHeight != 0) {
            if (GravityCompat.isVerticalBottom(gravity)) {
                mContentTop += dHeight;
                mContentBottom += dHeight;
            } else if (GravityCompat.isVerticalCenter(gravity)) {
                int temp = dHeight / 2;
                mContentTop += temp;
                mContentBottom += temp;
            }
        }
    }

    //this method was called in ModuleView parent to let module draw on the canvas of parent view.
    final void draw(Canvas canvas) {
        if (getWidth() < 0 || getHeight() <= 0)
            return;
        drawBackground(canvas);
        onDraw(canvas, mContentLeft,  mContentTop, mContentRight, mContentBottom);
    }

    void drawBackground(Canvas canvas) {
        if (canvas != null && mBackgroundDrawable != null) {
            if (mLeft >= 0 && mTop >= 0 && mRight > 0 && mBottom > 0) {
                mBackgroundDrawable.setBounds(mLeft, mTop, mRight, mBottom);
                mBackgroundDrawable.draw(canvas);
            }
        }
    }


    protected void onDraw(Canvas canvas, int contentLeft, int contentTop, int contentRight, int contentBottom) {

    }


    //handle event
    public boolean clickable() {
        return mOnClickListener != null;
    }

    public boolean longClickable() {
        return mOnLongClickListener != null;
    }

    protected boolean onTouchEvent(MotionEvent e) {
        boolean handled = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                handled = clickable() || longClickable();

                if (longClickable())
                    startWaitingLongClick();
                break;
            }

            case MotionEvent.ACTION_UP: {
                cancelLongClickWaiting();
                handled = clickable();
                if (clickable())
                    performClick();
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                cancelLongClickWaiting();
                break;
            }
        }
        return handleTouchEvent(e) || handled;
    }


    protected boolean handleTouchEvent(MotionEvent event) {
        if (mOnTouchListener != null)
            return mOnTouchListener.onTouch(this, event);
        return false;
    }

    private Handler mLongClickTriggerHandler;
    private Runnable mLongClickTriggerRunnable = new Runnable() {
        @Override
        public void run() {
            performLongClick();
            //trigger a cancel touch event to parent view after long click
            if (mParent != null)
                mParent.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, getLeft(), getTop(), 0));
        }
    };


    private void startWaitingLongClick() {
        if (mLongClickTriggerHandler == null && mContext == null)
            return;
        cancelLongClickWaiting();
        if (mLongClickTriggerHandler == null)
            mLongClickTriggerHandler = new Handler();
        mLongClickTriggerHandler.removeCallbacks(mLongClickTriggerRunnable);
        mLongClickTriggerHandler.postDelayed(mLongClickTriggerRunnable, LONG_CLICK_TIME);
    }

    private void cancelLongClickWaiting() {
        if (mLongClickTriggerHandler != null)
            mLongClickTriggerHandler.removeCallbacks(mLongClickTriggerRunnable);
    }


    public void performLongClick() {
        if (mOnLongClickListener != null)
            mOnLongClickListener.onLongClick(this);
    }

    public void performClick() {
        if (mOnClickListener != null)
            mOnClickListener.onClick(this);
    }


    public void invalidate() {
        if (mParent != null)
//            mParent.invalidateChild(this);
            mParent.invalidate();
    }

    public void requestLayout() {
        if (mParent != null)
            mParent.requestLayout();
    }

    //-------------interface region--------------------
    public interface OnClickListener {
        void onClick(Module module);
    }

    public interface OnLongClickListener {
        void onLongClick(Module module);
    }

    public interface OnTouchListener {
        boolean onTouch(Module module, MotionEvent event);
    }

    public OnTouchListener getOnTouchListener() {
        return mOnTouchListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    //--------------endregion--------------------------


}
