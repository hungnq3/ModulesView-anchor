package vn.com.vng.modulesview.modules_view;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by HungNQ on 19/10/2017.
 */

abstract class Anchor {
    public static final int ANCHOR_LEFT = 1;
    public static final int ANCHOR_TOP = 2;
    public static final int ANCHOR_RIGHT = 3;
    public static final int ANCHOR_BOTTOM = 4;

    public static final int BOUND_UNKNOWN = Module.BOUND_UNKNOWN;
    public static final int BOUND_UNSPECIFIED = Module.BOUND_UNSPECIFIED;


    @IntDef({ANCHOR_LEFT, ANCHOR_TOP, ANCHOR_RIGHT, ANCHOR_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnchorType{};

    private int mAnchorType;

    public Anchor(@AnchorType int anchorType) {
        mAnchorType = anchorType;
    }

    @AnchorType
    public int getAnchorType() {
        return mAnchorType;
    }

    public void setAnchorType(@AnchorType int anchorType) {
        mAnchorType = anchorType;
    }

    public final int getAnchorValue(){
        switch (mAnchorType){
            case ANCHOR_LEFT:
                return getLeft();
            case ANCHOR_TOP:
                return getTop();
            case ANCHOR_RIGHT:
                return getRight();
            case ANCHOR_BOTTOM:
                return getBottom();
        }
        return BOUND_UNKNOWN;
    }


    public abstract int getLeft();
    public abstract int getTop();
    public abstract int getRight();
    public abstract int getBottom();

}
