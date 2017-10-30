package vn.com.vng.modulesview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HungNQ on 08/09/2017.
 */

public class ModulesView extends View implements Parent {

    public ModulesView(Context context) {
        this(context, null);
    }

    public ModulesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModulesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ModulesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    //stuff
    private List<Module> mModules = new LinkedList<>();
    private Module mTouchFocusModule;

    private int mContentWidth, mContentHeight;
    private int mContentLeft, mContentTop, mContentRight, mContentBottom;
    private int mGravity;
    private int dX, dY;


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


    public void setSize(int width, int height) {
        ViewGroup.LayoutParams lParams = getLayoutParams();
        if (lParams == null) {
            lParams = new ViewGroup.LayoutParams(width, height);
            setLayoutParams(lParams);
        } else {
            lParams.width = width;
            lParams.height = height;
        }
    }

    public void setWidth(int width) {
        ViewGroup.LayoutParams lParams = getLayoutParams();
        if (lParams == null) {
            lParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutParams(lParams);
        } else {
            lParams.width = width;
        }
    }

    public void setHeight(int height) {
        ViewGroup.LayoutParams lParams = getLayoutParams();
        if (lParams == null) {
            lParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
            setLayoutParams(lParams);
        } else
            lParams.height = height;
    }


    int mWidthMeasureSize;
    int mWidthMeasureMode;
    int mHeightMeasureSize;
    int mHeightMeasureMode;
    int mCurrentWidth;
    int mCurrentHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        mWidthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        mHeightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        mHeightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        mCurrentWidth = mWidthMeasureMode == MeasureSpec.EXACTLY ? mWidthMeasureSize : 0;
        mCurrentHeight = mHeightMeasureMode == MeasureSpec.EXACTLY ? mHeightMeasureSize : 0;

        mContentWidth = 0;
        mContentHeight = 0;

        int boundLeft = Integer.MAX_VALUE;
        int boundTop = Integer.MAX_VALUE;
        int boundRight = 0;
        int boundBottom = 0;
        for (Module module : mModules) {
            if (module == null)
                continue;
            module.measure(mWidthMeasureSize, mWidthMeasureMode, mHeightMeasureSize, mHeightMeasureMode);
            module.onPostMeasured();

            if (module.getLeft() != Module.BOUND_UNSPECIFIED && module.getLeft() != Module.BOUND_UNKNOWN)
                boundLeft = Math.min(boundLeft, module.getLeft() - module.getLayoutParams().getMarginLeft());
            if (module.getTop() != Module.BOUND_UNSPECIFIED && module.getTop() != Module.BOUND_UNKNOWN)
                boundTop = Math.min(boundTop, module.getTop() - module.getLayoutParams().getMarginTop());
            if (module.getRight() != Module.BOUND_UNSPECIFIED && module.getRight() != Module.BOUND_UNKNOWN)
                boundRight = Math.max(boundRight, module.getRight() + module.getLayoutParams().getMarginRight());
            if (module.getBottom() != Module.BOUND_UNSPECIFIED && module.getBottom() != Module.BOUND_UNKNOWN)
                boundBottom = Math.max(boundBottom, module.getBottom() + module.getLayoutParams().getMarginBottom());

            //resolve current dimensions
            if (module.getWidth() >= 0) {
                if (mWidthMeasureMode != MeasureSpec.EXACTLY) {
                    int width = boundRight + getPaddingLeft() + getPaddingRight();
                    mCurrentWidth = Math.max(mCurrentWidth, width);
                    if (mWidthMeasureMode == MeasureSpec.AT_MOST) {
                        mCurrentWidth = Math.min(mCurrentWidth, mWidthMeasureSize);
                    }
                }
            }

            if (module.getHeight() >= 0) {
                if (mHeightMeasureMode != MeasureSpec.EXACTLY) {
                    int height = boundBottom + getPaddingTop() + getPaddingBottom();
                    mCurrentHeight = Math.max(mCurrentHeight, height);
                    if (mHeightMeasureMode == MeasureSpec.AT_MOST)
                        mCurrentHeight = Math.min(mCurrentHeight, mHeightMeasureSize);
                }
            }
        }

        setContentBounds(boundLeft, boundTop, boundRight, boundBottom);

        setMeasuredDimension(mCurrentWidth, mCurrentHeight);
        onPostMeasureChildren(mCurrentWidth, mCurrentHeight);

    }


    protected final void setContentBounds(int left, int top, int right, int bottom) {
        mContentLeft = left;
        mContentTop = top;
        mContentRight = right;
        mContentBottom = bottom;

        mContentWidth = Math.max(right - left, 0);
        mContentHeight = Math.max(bottom - top, 0);
    }

    protected void onPostMeasureChildren(int width, int height) {

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        for (Module module : mModules) {
            if (module == null)
                continue;
            module.layout(module.getLeft(), module.getTop(), module.getRight(), module.getBottom());
        }
        configGravityPosition();
    }


    private void configGravityPosition() {
        if (getWidth() <= 0 || getHeight() <= 0)
            return;

        dX = 0;
        dY = 0;
        int gravity = getGravity();

        if (!GravityCompat.isHorizontalNone(gravity)) {
            if (GravityCompat.isHorizontalLeft(gravity)) {
                dX = -mContentLeft;
            } else if (GravityCompat.isHorizontalRight(gravity)) {
                int dWidth = getWidth() - (mContentWidth + getPaddingLeft() + getPaddingRight());
                dX = dWidth - mContentLeft;
            } else if (GravityCompat.isHorizontalCenter(gravity)) {
                int dWidth = getWidth() - (mContentWidth + getPaddingLeft() + getPaddingRight());
                dX = dWidth / 2 - mContentLeft;
            }
        }

        if (!GravityCompat.isVerticalNone(gravity)) {
            if (GravityCompat.isVerticalTop(gravity)) {
                dY = -mContentTop;
            } else if (GravityCompat.isVerticalBottom(gravity)) {
                int dHeight = getHeight() - (mContentHeight + getPaddingTop() + getPaddingBottom());
                dY = dHeight - mContentTop;
            } else if (GravityCompat.isVerticalCenter(gravity)) {
                int dHeight = getHeight() - (mContentHeight + getPaddingTop() + getPaddingBottom());
                dY = dHeight / 2 - mContentTop;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0 || mContentWidth <= 0 || mContentHeight <= 0)
            return;
        int countToRestore = canvas.save();

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = left + getWidth() - getPaddingLeft() - getPaddingRight();
        int bottom = top + getHeight() - getPaddingTop() - getPaddingBottom();
        canvas.clipRect(left, top, right, bottom);
        canvas.translate(dX + getPaddingLeft(), dY + getPaddingTop());

        for (Module module : mModules) {
            if (module.getLayoutParams().getVisibility() == LayoutParams.VISIBLE)
                module.draw(canvas);
        }

        canvas.restoreToCount(countToRestore);
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

    @Override
    public void cancelTouchEvent() {
        onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0, 0, 0));
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
    public int getChildCoordinateX() {
        return getPaddingLeft() + dX;
    }

    @Override
    public int getChildCoordinateY() {
        return getPaddingTop() + dY;
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


    public int sp(float sp) {
        return (int) (getContext().getResources().getDisplayMetrics().scaledDensity * sp);

    }

    public int dp(float dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp);
    }


    public int getGravity() {
        return mGravity;
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
    }
}