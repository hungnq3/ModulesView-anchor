package vn.com.vng.modulesview;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HungNQ on 24/10/2017.
 */

public class GroupModule extends Module implements Parent {


    //stuff
    private List<Module> mModules = new LinkedList<>();
    private Module mTouchFocusModule;
    private int mWidthMeasureSize;
    private int mWidthMeasureMode;
    private int mHeightMeasureSize;
    private int mHeightMeasureMode;
    private int mCurrentWidth;
    private int mCurrentHeight;

    public GroupModule(Context context) {
        super(context);
    }

    @Override
    public void addModules(@NonNull List<? extends Module> modules) {
        for (Module module : modules) {
            if (module != null && module.getParent() == null) {
                mModules.add(module);
                module.setParent(this);
            }
        }
    }

    @Override
    public int getChildCoordinateX() {
        return getCoordinateX() + dX + getPaddingLeft();
    }

    @Override
    public int getChildCoordinateY() {
        return getCoordinateY() + dY + getPaddingTop();
    }

    @Override
    public int getPaddingLeft() {
        return getLayoutParams().getPaddingLeft();
    }

    @Override
    public int getPaddingTop() {
        return getLayoutParams().getPaddingTop();
    }

    @Override
    public int getPaddingRight() {
        return getLayoutParams().getPaddingRight();
    }

    @Override
    public int getPaddingBottom() {
        return getLayoutParams().getPaddingBottom();
    }


    @Override
    public void addModule(@NonNull Module module) {
        if (module.getParent() != null)
            return;
        mModules.add(module);
        module.setParent(this);
    }

    @Override
    public void clearModules() {
        for (Module module : mModules) {
            module.setParent(null);
        }
        mModules.clear();
    }

    @Override

    public void removeModule(Module module) {
        if (module != null) {
            mModules.remove(module);
            module.setParent(null);
        }
    }

    @Override
    public Module removeModule(int position) {
        if (position >= 0 && position < mModules.size()) {
            Module module = mModules.remove(position);
            module.setParent(null);
            return module;
        }
        return null;
    }

    @Override
    public List<Module> getModules() {
        return mModules;
    }

    @Override
    public int getModulesCount() {
        return mModules.size();
    }

    @Override
    public Module getModule(int position) {
        if (position >= 0 && position < mModules.size())
            return mModules.get(position);
        return null;
    }


    @Override
    public void cancelTouchEvent() {
        onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, getLeft(), getTop(), 0));
        if (getParent() != null)
            getParent().cancelTouchEvent();
    }

    @Override
    public int getWidthMeasureMode() {
        return mWidthMeasureMode;
    }

    @Override
    public int getWidthMeasureSize() {
        return mWidthMeasureSize;
    }

    @Override
    public int getHeightMeasureMode() {
        return mHeightMeasureMode;
    }

    @Override
    public int getHeightMeasureSize() {
        return mHeightMeasureSize;
    }

    @Override
    public int getCurrentWidth() {
        return mCurrentWidth;
    }

    @Override
    public int getCurrentHeight() {
        return mCurrentHeight;
    }


    @Override
    public void onMeasureContent(int width, int widthMode, int height, int heightMode) {
        mWidthMeasureSize = width;
        mWidthMeasureMode = widthMode;
        mHeightMeasureSize = height;
        mHeightMeasureMode = heightMode;

        mCurrentWidth = mWidthMeasureMode == View.MeasureSpec.EXACTLY ? mWidthMeasureSize : 0;
        mCurrentHeight = mHeightMeasureMode == View.MeasureSpec.EXACTLY ? mHeightMeasureSize : 0;

        int boundLeft = Integer.MAX_VALUE;
        int boundTop = Integer.MAX_VALUE;
        int boundRight = 0;
        int boundBottom = 0;

        int remainWidth = mWidthMeasureSize - getPaddingLeft() - getPaddingRight();
        int remainHeight = mHeightMeasureMode - getPaddingTop() - getPaddingBottom();
        for (Module module : mModules) {
            if (module == null)
                continue;
            module.measure(remainWidth, mWidthMeasureMode, remainHeight, mHeightMeasureMode);


            //resolve current dimensions
            if (module.getLeft() != Module.BOUND_UNSPECIFIED && module.getLeft() != Module.BOUND_UNKNOWN) {
                int temp = module.getLeft() - module.getLayoutParams().getMarginLeft();
                if (temp < boundLeft)
                    boundLeft = temp;
            }
            if (module.getTop() != Module.BOUND_UNSPECIFIED && module.getTop() != Module.BOUND_UNKNOWN) {
                int temp = module.getTop() - module.getLayoutParams().getMarginTop();
                if (temp < boundTop)
                    boundTop = temp;
            }
            if (module.getRight() != Module.BOUND_UNSPECIFIED && module.getRight() != Module.BOUND_UNKNOWN) {
                int temp = module.getRight() + module.getLayoutParams().getMarginRight();
                if (temp > boundRight) {
                    boundRight = temp;
                    //resolve current dimensions
                    if (mWidthMeasureMode != View.MeasureSpec.EXACTLY) {
                        int tempWidth = boundRight + getPaddingLeft() + getPaddingRight();
                        if (mWidthMeasureMode == View.MeasureSpec.AT_MOST && tempWidth > mWidthMeasureSize)
                            mCurrentWidth = mWidthMeasureSize;
                        else
                            mCurrentWidth = tempWidth;
                    }
                }
            }
            if (module.getBottom() != Module.BOUND_UNSPECIFIED && module.getBottom() != Module.BOUND_UNKNOWN) {
                int temp = module.getBottom() + module.getLayoutParams().getMarginBottom();
                if (temp > boundBottom) {
                    boundBottom = temp;
                    //resolve current dimensions
                    if (mHeightMeasureMode != View.MeasureSpec.EXACTLY) {
                        int tempHeight = boundBottom + getPaddingTop() + getPaddingBottom();
                        if (mHeightMeasureMode == View.MeasureSpec.AT_MOST && tempHeight > mHeightMeasureSize)
                            mCurrentHeight = mHeightMeasureSize;
                        else
                            mCurrentHeight = tempHeight;

                    }
                }
            }
//
//            //resolve current dimensions
//            if (mWidthMeasureMode != View.MeasureSpec.EXACTLY && module.getWidth() >= 0) {
//                int tempWidth = boundRight + getPaddingLeft() + getPaddingRight();
//                if (tempWidth > mCurrentWidth)
//                    mCurrentWidth = tempWidth;
//                if (mWidthMeasureMode == View.MeasureSpec.AT_MOST && mCurrentWidth > mWidthMeasureSize) {
//                    mCurrentWidth = mWidthMeasureSize;
//                }
//            }
//
//            if (mHeightMeasureMode != View.MeasureSpec.EXACTLY && module.getHeight() >= 0) {
//                int tempHeight = boundBottom + getPaddingTop() + getPaddingBottom();
//                if (tempHeight > mCurrentHeight)
//                    mCurrentHeight = tempHeight;
//                if (mHeightMeasureMode == View.MeasureSpec.AT_MOST && mCurrentHeight > mHeightMeasureSize)
//                    mCurrentHeight = mHeightMeasureSize;
//            }
        }

        setContentBounds(boundLeft, boundTop, boundRight, boundBottom);
    }



    @Override
    protected void onLayout(int left, int top, int right, int bottom) {
        super.onLayout(left, top, right, bottom);
        for (Module module : mModules) {
            if (module == null)
                continue;
            module.layout(module.getLeft(), module.getTop(), module.getRight(), module.getBottom());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        int saveToRestore = canvas.save();
//        int left = getCoordinateX() + getLayoutParams().getPaddingLeft();
//        int top = getCoordinateY() + getLayoutParams().getPaddingTop();
        int left = getLeft() + getLayoutParams().getPaddingLeft();
        int top = getTop() + getLayoutParams().getPaddingTop();
        int right = left + getWidth() - getLayoutParams().getPaddingLeft() - getLayoutParams().getPaddingRight();
        int bottom = top + getHeight() - getLayoutParams().getPaddingTop() - getLayoutParams().getPaddingBottom();
        canvas.clipRect(left, top, right, bottom);
        canvas.translate(left + dX, top + dY);

        for (Module module : mModules) {
            if (module.getLayoutParams().getVisibility() == LayoutParams.VISIBLE)
                module.draw(canvas);
        }

        canvas.restoreToCount(saveToRestore);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handle = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                for (Module module : mModules) {
                    if (checkEventRegion(module, event)) {
                        handle = module.onTouchEvent(event);
                        if (handle) {
                            mTouchFocusModule = module;
                            break;
                        }
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP: {
                if (mTouchFocusModule != null) {
                    if (checkEventRegion(mTouchFocusModule, event)) {
                        handle = mTouchFocusModule.onTouchEvent(event);
                    }
                    mTouchFocusModule = null;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (mTouchFocusModule != null) {
                    if (checkEventRegion(mTouchFocusModule, event)) {
                        handle = mTouchFocusModule.onTouchEvent(event);
                    } else {
                        MotionEvent e = MotionEvent.obtain(event);
                        e.setAction(MotionEvent.ACTION_CANCEL);
                        handle = mTouchFocusModule.onTouchEvent(e);
                        mTouchFocusModule = null;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                if (mTouchFocusModule != null) {
                    MotionEvent e = MotionEvent.obtain(event);
                    e.setAction(MotionEvent.ACTION_CANCEL);
                    handle = mTouchFocusModule.onTouchEvent(e);
                    mTouchFocusModule = null;
                }
                break;
            }
        }
        return handle || super.onTouchEvent(event);
    }


    /**
     * @param module
     * @param event
     * @return true if event occur in module's bounds
     */

    private boolean checkEventRegion(Module module, MotionEvent event) {
        int x = (int) (event.getX() - getChildCoordinateX());
        int y = (int) (event.getY() - getChildCoordinateY());
        return x < module.getRight() && x > module.getLeft()
                && y > module.getTop() && y < module.getBottom();
    }

}
