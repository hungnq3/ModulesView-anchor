package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 25/10/2017.
 */

public class Guideline {
    private int dX, dY;
    private float mXBias;
    private float mYBias;

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

    public float getXBias() {
        return mXBias;
    }

    public Guideline setXBias(float bias) {
        if (bias < 0)
            mXBias = 0;
        else if (bias > 1)
            mXBias = 1;
        else
            mXBias = bias;
        return this;
    }

    public float getYBias() {
        return mYBias;
    }

    public Guideline setYBias(float bias) {
        if (bias < 0f)
            mYBias = 0f;
        else if (bias > 1f)
            mYBias = 1f;
        else
            mYBias = bias;
        return this;
    }

    public int getXValue() {
        int value = dX;
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentWidth() * mXBias;
        }
        return value;
    }

    public int getYValue() {
        int value = dY;
        if (mModule != null && mModule.getParent() != null) {
            Parent parent = mModule.getParent();
            value += parent.getCurrentHeight() * mYBias;
        }
        return value;
    }

}
