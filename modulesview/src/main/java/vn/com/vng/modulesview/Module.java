package vn.com.vng.modulesview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HungNQ on 08/09/2017.
 */

public class Module {
    private static final long LONG_CLICK_TIME = 500;

    public static final int DIMENSION_MODE_EXACTLY = View.MeasureSpec.EXACTLY;
    public static final int DIMENSION_MODE_AT_MOST = View.MeasureSpec.AT_MOST;
    public static final int DIMENSION_MODE_UNSPECIFIED = View.MeasureSpec.UNSPECIFIED;

    public static final int DIMENSION_UNSPECIFIED = Integer.MIN_VALUE;
    public static final int DIMENSION_UNKNOWN = Integer.MIN_VALUE + 1;
    public static final int BOUND_UNSPECIFIED = Integer.MIN_VALUE;
    public static final int BOUND_UNKNOWN = Integer.MIN_VALUE + 1;

    private static final int[] STATE_PRESS_ARRAYS = {android.R.attr.state_pressed};
    //stuff
    protected Context mContext;
    private Parent mParent;
    private LayoutParams mParams;

    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;
    private int mContentWidth, mContentHeight;
    protected int mContentLeft, mContentTop, mContentRight, mContentBottom;
    protected int dX, dY;

    private int mCoordinateX, mCoordinateY;

    private Drawable mBackgroundDrawable;

    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private OnTouchListener mOnTouchListener;

    public Module(Context context) {
        mContext = context;
        mParams = new LayoutParams(this);
    }


    public Parent getParent() {
        return mParent;
    }

    void setParent(Parent parent) {
        mParent = parent;
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


    public void setBackgroundResId(int id) {
        setBackgroundDrawable(ContextCompat.getDrawable(getContext(), id));
    }

    public Drawable getBackgroundDrawable() {
        return mBackgroundDrawable;
    }


    public void clearBackground() {
        mBackgroundDrawable = null;
        invalidate();
    }


    private boolean applyStateListBackground() {
        return mBackgroundDrawable instanceof StateListDrawable && (clickable() || longClickable());
    }

    //-----------------listener-------------------------------------
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    public OnTouchListener getOnTouchListener() {
        return mOnTouchListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }


    //--------------config region-------------


    void setBounds(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
        if (mLeft == BOUND_UNKNOWN || mRight == BOUND_UNKNOWN)
            mWidth = DIMENSION_UNKNOWN;
        else
            mWidth = (mLeft != BOUND_UNSPECIFIED && mRight != BOUND_UNSPECIFIED) ? mRight - mLeft : DIMENSION_UNSPECIFIED;

        if (mTop == BOUND_UNKNOWN || mBottom == BOUND_UNKNOWN)
            mHeight = DIMENSION_UNKNOWN;
        else
            mHeight = (mTop != BOUND_UNSPECIFIED && mBottom != BOUND_UNSPECIFIED) ? mBottom - mTop : DIMENSION_UNSPECIFIED;
    }


    final void measure(int parentWidth, int parentWidthMode, int parentHeight, int parentHeightMode) {

//        //step 0: check cached measure dimension
//        long key = (long)View.MeasureSpec.makeMeasureSpec(parentWidth, parentWidthMode) << 32 | (View.MeasureSpec.makeMeasureSpec(parentHeight,parentHeightMode) & 0xffffffffL);
//        if (mMeasureCache == null)
//            mMeasureCache = new LongSparseArray<>(2);
//        else {
//            long dimensions = mMeasureCache.get(key, -1L);
//            if(dimensions != -1){
//                int width = (int) (dimensions >> 32);
//                int height = (int) ((dimensions << 32) >> 32);
//            }
//        }


        //step 1: external measure
        mParams.onMeasureAnchors();

        //step 2: check dimensions' state
        boolean unknownDimensions = mWidth == DIMENSION_UNKNOWN || mHeight == DIMENSION_UNKNOWN;
        if (unknownDimensions)
            return;

        boolean needToUpdateDimensions = mWidth == DIMENSION_UNSPECIFIED || mHeight == DIMENSION_UNSPECIFIED;

        //step 3: internal measure
        if (mParams.getVisibility() == LayoutParams.GONE) {
            goneModule();
        } else {
            int width, widthMode;
            int height, heightMode;

            if (mWidth == DIMENSION_UNSPECIFIED) {
                width = getRemainWidth(parentWidth);
                widthMode = parentWidthMode == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_AT_MOST;
            } else {
                width = mWidth - mParams.getPaddingLeft() - mParams.getPaddingRight();
                widthMode = DIMENSION_MODE_EXACTLY;
            }

            if (mHeight == DIMENSION_UNSPECIFIED) {
                height = getRemainHeight(parentHeight);
                heightMode = parentHeightMode == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_AT_MOST;
            } else {
                height = mHeight - mParams.getPaddingTop() - mParams.getPaddingBottom();
                heightMode = DIMENSION_MODE_EXACTLY;
            }

            onMeasureContent(width, widthMode, height, heightMode);

            //step 4: re update dimensions if needed
            if (needToUpdateDimensions)
                updateUnspecifiedBounds(mContentWidth, mContentHeight);

            //step 5: resolve center
            if (mParams.isCenterInHorizontal()) {
                if (mWidth != DIMENSION_UNSPECIFIED && parentWidthMode == DIMENSION_MODE_EXACTLY) {
                    int dx = (parentWidth- mParams.getMarginLeft() - mParams.getMarginRight() - mWidth) / 2;
                    mLeft += dx;
                    mRight += dx;
                }
            }
            if (mParams.isCenterInVertical()) {
                if (mHeight != DIMENSION_UNSPECIFIED && parentHeightMode == DIMENSION_MODE_EXACTLY) {
                    int dy = (parentHeight - mParams.getMarginTop() - mParams.getMarginBottom() - mHeight) / 2;
                    mTop += dy;
                    mBottom += dy;
                }
            }
        }
    }

    private void goneModule() {
        mWidth = 0;
        mHeight = 0;

        if (mLeft == BOUND_UNSPECIFIED && mRight == BOUND_UNSPECIFIED) {
            mLeft = mParams.getX() - mParams.getMarginLeft();
            mRight = mLeft;
        } else if (mLeft == BOUND_UNSPECIFIED) {
            mRight -= mParams.getMarginRight();
            mLeft = mRight;
        } else if (mRight == BOUND_UNSPECIFIED) {
            mLeft -= mParams.getMarginLeft();
            mRight = mLeft;

        }

        if (mTop == BOUND_UNSPECIFIED && mBottom == BOUND_UNSPECIFIED) {
            mTop = mParams.getY() - mParams.getMarginTop();
            mBottom = mTop;
        } else if (mTop == BOUND_UNSPECIFIED) {
            mBottom -= mParams.getMarginBottom();
            mTop = mBottom;
        } else if (mBottom == BOUND_UNSPECIFIED) {
            mTop -= mParams.getMarginTop();
            mBottom = mTop;
        }
    }


    private int getRemainWidth(int parentWidth) {
        int right, left;
        if (mRight == BOUND_UNSPECIFIED) {
            right = parentWidth - mParams.getMarginRight();
        } else {
            right = mRight;
        }

        if (mLeft == BOUND_UNSPECIFIED) {
            left = mParams.getMarginLeft();
        } else {
            left = mLeft;
        }
        int width = right - left- mParams.getPaddingRight() - mParams.getPaddingLeft();

        return width > 0 ? width : 0;
    }

    private int getRemainHeight(int parentHeight) {

        int bottom, top;
        if (mBottom == BOUND_UNSPECIFIED) {
            bottom = parentHeight - mParams.getMarginBottom();
        } else {
            bottom = mBottom;
        }
        if (mTop == BOUND_UNSPECIFIED) {
            top = mParams.getMarginTop();
        } else {
            top = mTop;
        }
        int height = bottom - top - mParams.getPaddingTop() - mParams.getPaddingBottom();
        return height > 0 ? height : 0;
    }

    public void onMeasureContent(int width, int widthMode, int height, int heightMode) {
        setContentDimensions(widthMode == Module.DIMENSION_MODE_EXACTLY ? width : 0, heightMode == Module.DIMENSION_MODE_EXACTLY ? height : 0);
    }

    protected void setContentDimensions(int contentWidth, int contentHeigh) {
        setContentBounds(0, 0, contentWidth, contentHeigh);
    }


    protected final void setContentBounds(int left, int top, int right, int bottom) {
        mContentLeft = left;
        mContentTop = top;
        mContentRight = right;
        mContentBottom = bottom;

        mContentWidth = Math.max(right - left, 0);
        mContentHeight = Math.max(bottom - top, 0);
    }

    private final void updateUnspecifiedBounds(int width, int height) {
        if (mWidth == DIMENSION_UNSPECIFIED) {
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
        }

        if (mHeight == DIMENSION_UNSPECIFIED) {
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
    }


    public int getCoordinateX() {
        return mCoordinateX;
    }


    public int getCoordinateY() {
        return mCoordinateY;
    }


    final void layout(int left, int top, int right, int bottom) {

//        int oldWidth = mWidth;
//        int oldHeight = mHeight;
//        setBounds(left, top, right, bottom);
//        boolean changed = oldWidth != mWidth && oldHeight != mHeight;

        //reconfig center if needed
        reconfigCenterInParentIfNeeded();

        configGravityPosition();

        int parentCooX = getParent() != null ? getParent().getChildCoordinateX() : 0;
        int parentCooY = getParent() != null ? getParent().getChildCoordinateY() : 0;
        mCoordinateX = parentCooX + mLeft;
        mCoordinateY = parentCooY + mTop;

        onLayout(mLeft, mTop, mRight, mBottom);

        configModule();
    }

    private void reconfigCenterInParentIfNeeded() {
        if (mParams.isCenterInHorizontal() && mParent.getWidthMeasureMode() != Module.DIMENSION_MODE_EXACTLY) {
            if (mWidth != DIMENSION_UNSPECIFIED) {
                int newLeft = (mParent.getCurrentWidth() - mParent.getPaddingLeft() - mParent.getPaddingRight() - mParams.getMarginLeft() - mParams.getMarginRight() - mWidth) / 2 + mParams.getMarginLeft();
                mRight = mRight + newLeft - mLeft;
                mLeft = newLeft;
            }
        }
        if (mParams.isCenterInVertical() && mParent.getHeightMeasureMode() != Module.DIMENSION_MODE_EXACTLY) {
            if (mHeight != DIMENSION_UNSPECIFIED) {
                int newTop = (mParent.getCurrentHeight() - mParent.getPaddingTop() - mParent.getPaddingBottom() - mParams.getMarginTop() - mParams.getMarginBottom() - mHeight) / 2 + mParams.getMarginTop();
                mBottom = mBottom + newTop - mTop;
                mTop = newTop;
            }
        }
    }


    void onLayout(int left, int top, int right, int bottom) {

    }

    public void configModule() {

    }

    private void configGravityPosition() {
        if (mWidth <= 0 || mHeight <= 0 || mContentWidth <= 0 || mContentHeight <= 0)
            return;

        dX = 0;
        dY = 0;

        int gravity = mParams.getGravity();

        if (!GravityCompat.isHorizontalNone(gravity)) {
            if (GravityCompat.isHorizontalLeft(gravity)) {
                dX = -mContentLeft;
            } else if (GravityCompat.isHorizontalRight(gravity)) {
                int dWidth = mWidth - (mContentWidth + getLayoutParams().getPaddingLeft() + getLayoutParams().getPaddingRight());
                dX = dWidth - mContentLeft;
            } else if (GravityCompat.isHorizontalCenter(gravity)) {
                int dWidth = mWidth - (mContentWidth + getLayoutParams().getPaddingLeft() + getLayoutParams().getPaddingRight());
                dX = dWidth / 2 - mContentLeft;
            }
        }

        if (!GravityCompat.isVerticalNone(gravity)) {
            if (GravityCompat.isVerticalTop(gravity)) {
                dY = -mContentTop;
            } else if (GravityCompat.isVerticalBottom(gravity)) {
                int dHeight = mHeight - (mContentHeight + getLayoutParams().getPaddingTop() + getLayoutParams().getPaddingBottom());
                dY = dHeight - mContentTop;
            } else if (GravityCompat.isVerticalCenter(gravity)) {
                int dHeight = mHeight - (mContentHeight + getLayoutParams().getPaddingTop() + getLayoutParams().getPaddingBottom());
                dY = dHeight / 2 - mContentTop;
            }
        }
    }

    //this method was called in ModuleView parent to let module draw on the canvas of parent view.
    final void draw(Canvas canvas) {
        if (getWidth() <= 0 || getHeight() <= 0)
            return;
        drawBackground(canvas);

//        int saveToRestore = canvas.save();
//        int left = getCoordinateX() + getLayoutParams().getPaddingLeft();
//        int top = getCoordinateY() + getLayoutParams().getPaddingTop();
//        int right = left + mWidth - getLayoutParams().getPaddingLeft() - getLayoutParams().getPaddingRight();
//        int bottom = top + mHeight - getLayoutParams().getPaddingTop() - getLayoutParams().getPaddingBottom();
//        canvas.clipRect(left, top, right, bottom);

        onDraw(canvas);
        dispatchDraw(canvas);

//        canvas.restoreToCount(saveToRestore);
    }


    void drawBackground(Canvas canvas) {
        if (canvas != null && mBackgroundDrawable != null) {
            if (mWidth > 0 && mHeight > 0) {
//                int left = getCoordinateX();
//                int top = getCoordinateY();
//                int right = left + mWidth;
//                int bottom = top + mHeight;
//                mBackgroundDrawable.setBounds(left, top, right, bottom);
                mBackgroundDrawable.setBounds(mLeft, mTop, mRight, mBottom);
                mBackgroundDrawable.draw(canvas);
            }
        }
    }


    protected void onDraw(Canvas canvas) {

    }

    protected void dispatchDraw(Canvas canvas) {

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
                if (applyStateListBackground())
                    updateBackgroundState(STATE_PRESS_ARRAYS);

                handled = clickable() || longClickable();
                if (longClickable())
                    startWaitingLongClick();
                break;
            }

            case MotionEvent.ACTION_UP: {
                if (applyStateListBackground())
                    updateBackgroundState();

                cancelLongClickWaiting();
                handled = clickable();
                if (clickable())
                    performClick();
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                if (applyStateListBackground())
                    updateBackgroundState();

                cancelLongClickWaiting();
                break;
            }
        }
        return handleTouchEvent(e) || handled;
    }

    private void updateBackgroundState(int... states) {
        mBackgroundDrawable.setState(states);
        invalidate();
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
                mParent.cancelTouchEvent();
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

    //--------------endregion--------------------------


}
