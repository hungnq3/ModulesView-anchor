package vn.com.vng.modulesview.modules_view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import vn.com.vng.modulesview.sample.chat_view.ChatHeaderView;

/**
 * Created by HungNQ on 08/09/2017.
 */

public class ModulesView extends View {

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


    public void addModules(@NonNull List<? extends Module> modules) {
        for (Module module : modules) {
            if (module != null && module.getParent() == null) {
                mModules.add(module);
                module.setParent(this);
            }
        }
    }


    public void addModule(@NonNull Module module) {
        if (module.getParent() != null)
            return;
        mModules.add(module);
        module.setParent(this);
    }


    public void clearModules() {
        for (Module module : mModules) {
            module.setParent(null);
        }
        mModules.clear();
    }

    public void removeModule(Module module) {
        if (module != null) {
            mModules.remove(module);
            module.setParent(null);
        }
    }

    public Module removeModule(int position) {
        if (position >= 0 && position < mModules.size()) {
            Module module = mModules.remove(position);
            module.setParent(null);
            return module;
        }
        return null;
    }

    public List<Module> getModules() {
        return mModules;
    }

    public int getModulesCount() {
        return mModules.size();
    }

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

        mCurrentWidth = mWidthMeasureMode == MeasureSpec.EXACTLY ?  mWidthMeasureSize : 0;
        mCurrentHeight = mHeightMeasureMode == MeasureSpec.EXACTLY ? mHeightMeasureSize : 0;


        for (Module module : mModules) {
            if (module == null)
                continue;
            module.measure(widthMeasureSpec, heightMeasureSpec);
            module.onPostMeasured();

            //resolve current dimensions
            if (mWidthMeasureMode != MeasureSpec.EXACTLY) {
                if(module.getWidth() >=0) {
                    int width = module.getRight()
                            + module.getLayoutParams().getMarginRight()
                            + getPaddingRight();
                    mCurrentWidth = Math.max(mCurrentWidth, width);
                    if (mWidthMeasureMode == MeasureSpec.AT_MOST)
                        mCurrentWidth = Math.min(mCurrentWidth, mWidthMeasureSize);
                }
            }

            if (mHeightMeasureMode != MeasureSpec.EXACTLY) {
                if(module.getHeight() >=0) {
                    int height = module.getBottom()
                            + module.getLayoutParams().getMarginBottom()
                            + getPaddingBottom();
                    mCurrentHeight = Math.max(mCurrentHeight, height);
                    if (mHeightMeasureMode == MeasureSpec.AT_MOST)
                        mCurrentHeight = Math.min(mCurrentHeight, mHeightMeasureSize);
                }
            }

//            if(this instanceof ChatHeaderView)
//                if (mModules.indexOf(module) == 5)
//                    Log.d("onMeasureContent", " width = [" + module.getWidth()  + "] contentW = [" +module.getContentWidth() +"]" );
        }

//
//        //Measure this view
//        int width, height;
//        if (mWidthMeasureMode == MeasureSpec.EXACTLY)
//            width = mWidthMeasureSize;
//        else {
//            width = getMaxRightBound();
//            if (mWidthMeasureMode == MeasureSpec.AT_MOST)
//                width = Math.min(width, mWidthMeasureSize);
//        }
//
//
//        if (mHeightMeasureMode == MeasureSpec.EXACTLY)
//            height = mHeightMeasureSize;
//        else {
//            height = getMaxBottomBound();
//            if (mHeightMeasureMode == MeasureSpec.AT_MOST)
//                height = Math.min(height, mHeightMeasureSize);
//        }
//
//        setMeasuredDimension(width, height);
//        onPostMeasureChildren(width, height);

        setMeasuredDimension(mCurrentWidth, mCurrentHeight);
        onPostMeasureChildren(mCurrentWidth, mCurrentHeight);

    }


    private int getMaxRightBound() {
        int max = 0;
        for (Module module : mModules) {
            if (module == null || module.getRight() == Module.BOUND_UNSPECIFIED || module.getRight() == Module.BOUND_UNKNOWN)
                continue;
            max = Math.max(max, module.getRight() + module.getLayoutParams().mMarginRight);
        }
        if(getParent() != null)
            max+= getPaddingRight();

        return max;
    }

    private int getMaxBottomBound() {
        int max = 0;
        for (Module module : mModules) {
            if (module == null || module.getBottom() == Module.BOUND_UNSPECIFIED || module.getBottom() == Module.BOUND_UNKNOWN)
                continue;
            max = Math.max(max, module.getBottom() + module.getLayoutParams().mMarginBottom);
        }

        if(getParent() != null)
            max+= getPaddingBottom();

        return max;
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Module module : mModules) {
            if (module.getLayoutParams().getVisibility() == LayoutParams.VISIBLE)
                module.draw(canvas);
        }
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
                    if (checkEventRegion(mTouchFocusModule, event))
                        handle = mTouchFocusModule.onTouchEvent(event);
                    mTouchFocusModule = null;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (mTouchFocusModule != null) {
                    if (checkEventRegion(mTouchFocusModule, event))
                        handle = mTouchFocusModule.onTouchEvent(event);
                    else {
                        MotionEvent eventCancel = MotionEvent.obtain(event);
                        eventCancel.setAction(MotionEvent.ACTION_CANCEL);
                        handle = mTouchFocusModule.onTouchEvent(eventCancel);
                        mTouchFocusModule = null;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                if (mTouchFocusModule != null) {
                    handle = mTouchFocusModule.onTouchEvent(event);
                    mTouchFocusModule = null;
                }
                break;
            }
        }
        return handle || super.onTouchEvent(event);
    }


//    void invalidateChild(Module child){
//        if(child != null){
//            invalidate(child.getRealLeft(), child.getRealTop(), child.getRealRight(), child.getRealBottom());
//        }
//    }

    /**
     * @param module
     * @param event
     * @return true if event occur in module's bounds
     */

    private boolean checkEventRegion(Module module, MotionEvent event) {
        int x = (int) (event.getX() - getX());
        int y = (int) (event.getY() - getX());

        return x < module.getRight() && x > module.getLeft()
                && y > module.getTop() && y < module.getBottom();
    }

//
//    @Override
//    public boolean isInTouchMode() {
//        return true;
//    }

    public int sp(float sp) {
        return (int) (getContext().getResources().getDisplayMetrics().scaledDensity * sp);

    }

    public int dp(float dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp);
    }


}
