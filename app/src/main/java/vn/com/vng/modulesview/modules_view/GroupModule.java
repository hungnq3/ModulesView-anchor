package vn.com.vng.modulesview.modules_view;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.Log;
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
    public int getCoordinateX() {
        return getLeft();
    }

    @Override
    public int getCoordinateY() {
        return getTop();
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

        int boundLeft = 0;
        int boundTop = 0;
        int boundRight = 0;
        int boundBottom = 0;

        for (Module module : mModules) {
            if (module == null)
                continue;
            module.measure(width, widthMode, height, heightMode);
            module.onPostMeasured();

            //resolve current dimensions
            if (module.getLeft() != Module.BOUND_UNSPECIFIED && module.getLeft() != Module.BOUND_UNKNOWN)
                boundLeft = Math.min(boundLeft, module.getLeft());
            if (module.getTop() != Module.BOUND_UNSPECIFIED && module.getTop() != Module.BOUND_UNKNOWN)
                boundTop = Math.min(boundTop, module.getTop());
            if (module.getRight() != Module.BOUND_UNSPECIFIED && module.getRight() != Module.BOUND_UNKNOWN)
                boundRight = Math.max(boundRight, module.getRight());
            if (module.getBottom() != Module.BOUND_UNSPECIFIED && module.getBottom() != Module.BOUND_UNKNOWN)
                boundBottom = Math.max(boundBottom, module.getBottom());

            //resolve current dimensions
            if (module.getWidth() >= 0) {
                if (mWidthMeasureMode != View.MeasureSpec.EXACTLY) {
                    int tempWidth = module.getRight()
                            + module.getLayoutParams().getMarginRight()
                            + getPaddingRight();
                    mCurrentWidth = Math.max(mCurrentWidth, tempWidth);
                    if (mWidthMeasureMode == View.MeasureSpec.AT_MOST) {
                        mCurrentWidth = Math.min(mCurrentWidth, mWidthMeasureSize);
                    }
                }
            }

            if (module.getHeight() >= 0) {
                if (mHeightMeasureMode != View.MeasureSpec.EXACTLY) {
                    int tempHight = module.getBottom()
                            + module.getLayoutParams().getMarginBottom()
                            + getPaddingBottom();
                    mCurrentHeight = Math.max(mCurrentHeight, tempHight);
                    if (mHeightMeasureMode == View.MeasureSpec.AT_MOST)
                        mCurrentHeight = Math.min(mCurrentHeight, mHeightMeasureSize);
                }
            }
        }

        setContentBounds(boundLeft, boundTop, boundRight, boundBottom);
        onPostMeasureChildren(mCurrentWidth, mCurrentHeight);
    }


    void onPostMeasureChildren(int currentWidth, int currentHeight) {

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (Module module : mModules) {
            if (module == null)
                continue;
            module.layout(module.getLeft(), module.getTop(), module.getRight(), module.getBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int countToRestore = canvas.save();
        canvas.translate(getLeft(), getTop());
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
                        MotionEvent e = MotionEvent.obtain(event);
                        e.offsetLocation(-dX, -dY);
                        handle = module.onTouchEvent(e);
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
                        MotionEvent e = MotionEvent.obtain(event);
                        e.offsetLocation(-dX, -dY);
                        handle = mTouchFocusModule.onTouchEvent(e);
                    }
                    mTouchFocusModule = null;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (mTouchFocusModule != null) {
                    MotionEvent e = MotionEvent.obtain(event);
                    e.offsetLocation(-dX, -dY);
                    if (checkEventRegion(mTouchFocusModule, event)) {
                        handle = mTouchFocusModule.onTouchEvent(e);
                    }
                    else {
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
                    e.offsetLocation(-dX, -dY);
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

        int x = (int) (event.getX() - getLeft() - dX - getLayoutParams().getPaddingLeft());
        int y = (int) (event.getY() - getTop() - dY - getLayoutParams().getPaddingTop());
        Log.d("checkEventRegion: ", "x: " + x + "y: " + y);
        return x < module.getRight() && x > module.getLeft()
                && y > module.getTop() && y < module.getBottom();
    }

}
