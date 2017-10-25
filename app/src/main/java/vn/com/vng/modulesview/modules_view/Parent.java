package vn.com.vng.modulesview.modules_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import java.util.List;

/**
 * Created by HungNQ on 24/10/2017.
 */

interface Parent {
    Context getContext();

    int getChildCoordinateX();

    int getChildCoordinateY();

    int getPaddingLeft();

    int getPaddingTop();

    int getPaddingRight();

    int getPaddingBottom();


    void addModule(@NonNull Module module);

    void addModules(@NonNull List<? extends Module> modules);

    void clearModules();

    void removeModule(Module module);

    Module removeModule(int position);


    List<Module> getModules();

    int getModulesCount();

    Module getModule(int position);

    void invalidate();

    void requestLayout();


    void cancelTouchEvent();

    int getWidthMeasureMode();

    int getWidthMeasureSize();

    int getHeightMeasureMode();

    int getHeightMeasureSize();

    int getCurrentWidth();

    int getCurrentHeight();

}
