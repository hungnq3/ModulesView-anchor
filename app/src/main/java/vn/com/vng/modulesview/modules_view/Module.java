package vn.com.vng.modulesview.modules_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
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
    private ModuleParams mParams;

    private int mWidth, mHeight;
    private int mLeft, mTop, mRight, mBottom;

    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private OnTouchListener mOnTouchListener;

    public Module() {
        mParams = new ModuleParams();
    }

    public Module(ModuleParams params) {
        mParams = params;
        if (mParams == null)
            mParams = new ModuleParams();
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

    public ModuleParams getModuleParams() {
        return mParams;
    }

    //-------------------getter & setter----------


    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
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

        //step 1
        mParams.externalMeasure(this, parentMeasureWidthSpec, parentMeasureHeightSpec);



        //step 2
        if (mLeft == BOUND_UNSPECIFIED)
            mLeft = mParams.mMarginLeft;
        if (mTop == BOUND_UNSPECIFIED)
            mTop = mParams.mMarginTop;

        if (mWidth == DIMENSION_UNSPECIFIED || mHeight == DIMENSION_UNSPECIFIED) {
            int width, widthMode;
            int height, heightMode;
            if (mWidth == DIMENSION_UNSPECIFIED) {
                width = View.MeasureSpec.getSize(parentMeasureWidthSpec) - mLeft - mParams.mMarginRight;
                widthMode = View.MeasureSpec.getMode(parentMeasureWidthSpec) == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_MAX;
            } else {
                width = mWidth;
                widthMode = DIMENSION_MODE_FIXED;
            }

            if (mHeight == DIMENSION_UNSPECIFIED) {
                height = View.MeasureSpec.getSize(parentMeasureHeightSpec) - mTop - mParams.mMarginBottom;
                heightMode = View.MeasureSpec.getMode(parentMeasureHeightSpec) == View.MeasureSpec.UNSPECIFIED ? DIMENSION_MODE_UNSPECIFIED : DIMENSION_MODE_MAX;
            } else {
                height = mHeight;
                heightMode = DIMENSION_MODE_FIXED;
            }
            internalMeasure(width, widthMode, height, heightMode);
        }
    }


    public void internalMeasure(int width, int widthMode, int height, int heightMode) {

    }


    public void updateMeasureDimension(int width, int height){
        if (mLeft == BOUND_UNSPECIFIED && mRight == BOUND_UNSPECIFIED) {
            mLeft = mParams.getMarginLeft() + mParams.getX();
            mRight = mLeft + width;
        } else if (mLeft == BOUND_UNSPECIFIED) {
            mLeft = mRight - width;
        } else {
            mRight = mLeft + width;
        }

        if (mTop == BOUND_UNSPECIFIED && mBottom == BOUND_UNSPECIFIED) {
            mTop = mParams.getMarginTop() + mParams.getY();
            mBottom = mTop + height;
        } else if (mTop == BOUND_UNSPECIFIED) {
            mTop = mBottom - height;
        } else {
            mBottom = mTop + height;
        }
        mWidth = width;
        mHeight = height;
    }

    public void onPostMeasured() {

    }


    final void layout(int left, int top, int right, int bottom) {
        int oldWidth = mWidth;
        int oldHeight = mHeight;
        setBounds(left, top, right, bottom);
        boolean changed = oldWidth != mWidth && oldHeight != mHeight;
        onLayout(changed, left, top, right, bottom);
    }

    void onLayout(boolean layoutChanged, int left, int top, int right, int bottom) {
        configModule();
    }

    public void configModule(){

    }

    //this method was called in ModuleView parent to let module draw on the canvas of parent view.
    final void draw(Canvas canvas) {
        if(getWidth() <0 || getHeight() <=0)
            return;

        drawBackground(canvas);
        onDraw(canvas);
    }

    void drawBackground(Canvas canvas) {
        if (canvas != null && mParams.mBackgroundDrawable != null) {
            if (mLeft >= 0 && mTop >= 0 && mRight > 0 && mBottom > 0) {
                mParams.mBackgroundDrawable.setBounds(mLeft, mTop, mRight, mBottom);
                mParams.mBackgroundDrawable.draw(canvas);
            }
        }
    }


    protected void onDraw(Canvas canvas) {

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

    public void requestLayout(){
        if(mParent != null)
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
