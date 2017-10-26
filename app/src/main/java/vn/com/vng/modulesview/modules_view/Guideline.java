package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 25/10/2017.
 */

public class Guideline {
    private int dX, dY;
    private float mXPercent;
    private float mYPercent;

    private Module mModule;

    public Guideline(Module module) {
        mModule = module;
    }

    public Module getModule() {
        return mModule;
    }

    public Guideline setModule(Module module) {
        mModule = module;
        return this;
    }

    public int getdX() {
        return dX;
    }

    public Guideline setdX(int dX) {
        this.dX = dX;
        return this;
    }

    public int getdY() {
        return dY;
    }

    public Guideline setdY(int dY) {
        this.dY = dY;
        return this;
    }

    public float getXPercent() {
        return mXPercent;
    }

    public Guideline setXPercent(float bias) {
        if (bias < 0)
            mXPercent = 0;
        else if (bias > 1)
            mXPercent = 1;
        else
            mXPercent = bias;
        return this;
    }

    public float getYPercent() {
        return mYPercent;
    }

    public Guideline setYPercent(float bias) {
        if (bias < 0f)
            mYPercent = 0f;
        else if (bias > 1f)
            mYPercent = 1f;
        else
            mYPercent = bias;
        return this;
    }

    public int getXValue() {
        int value = dX;
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentWidth() * mXPercent;
        }
        return value;
    }

    public int getYValue() {
        int value = dY;
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentHeight() * mYPercent;
        }
        return value;
    }

}
