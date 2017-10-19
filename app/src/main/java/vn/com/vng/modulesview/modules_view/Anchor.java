package vn.com.vng.modulesview.modules_view;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by HungNQ on 19/10/2017.
 */

public class Anchor {
    public static final int ANCHOR_LEFT = 1;
    public static final int ANCHOR_TOP = 2;
    public static final int ANCHOR_RIGHT = 3;
    public static final int ANCHOR_BOTTOM = 4;


    @IntDef({ANCHOR_LEFT, ANCHOR_TOP, ANCHOR_RIGHT, ANCHOR_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnchorType{};

    private Module mModule;
    private int mAnchorType;

    public Anchor(Module module, @AnchorType int anchorType) {
        mModule = module;
        mAnchorType = anchorType;
    }

    public Module getModule() {
        return mModule;
    }

    public void setModule(Module module) {
        mModule = module;
    }

    @AnchorType
    public int getAnchorType() {
        return mAnchorType;
    }

    public void setAnchorType(@AnchorType int anchorType) {
        mAnchorType = anchorType;
    }

    public int getAnchorValue(){
        if(!isValid())
            return 0;
        switch (mAnchorType){
            case ANCHOR_LEFT:
                return mModule.getLeft();
            case ANCHOR_TOP:
                return mModule.getTop();
            case ANCHOR_RIGHT:
                return mModule.getRight();
            case ANCHOR_BOTTOM:
                return mModule.getBottom();
        }

        return 0;
    }

    public boolean isValid(){
        return  mModule != null && mModule.getLayoutParams().getVisibility() != LayoutParams.GONE;
    }







}
