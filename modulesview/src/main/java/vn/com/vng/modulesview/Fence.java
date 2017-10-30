package vn.com.vng.modulesview;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HungNQ on 24/10/2017.
 */

public class Fence {

    //stuff
    private List<Module> mModules = new LinkedList<>();


    public Fence(Module... modules) {
        if (modules != null)
            Collections.addAll(mModules, modules);
    }

    public void addModules(@NonNull List<? extends Module> modules) {
        for (Module module : modules) {
            if (module != null)
                mModules.add(module);
        }
    }


    public void addModule(@NonNull Module module) {
        if (module != null)
            mModules.add(module);
    }

    public void clearModules() {
        mModules.clear();
    }

    public void removeModule(Module module) {
        if (module != null)
            mModules.remove(module);
    }

    public void removeModule(int position) {
        if (position >= 0 && position < mModules.size()) {
            mModules.remove(position);
        }
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


    public int getLeft() {
        int left = Anchor.BOUND_UNSPECIFIED;
        for (Module module : mModules) {
            if (module.getLeft() != Module.BOUND_UNKNOWN || module.getLeft() != Module.BOUND_UNSPECIFIED)
                left = Math.min(module.getLeft(), left);
        }
        return left;
    }

    public int getTop() {
        int top = Anchor.BOUND_UNSPECIFIED;
        for (Module module : mModules) {
            if (module.getTop() != Module.BOUND_UNKNOWN || module.getTop() != Module.BOUND_UNSPECIFIED)
                top = Math.min(module.getTop(), top);
        }
        return top;
    }

    public int getRight() {
        int right = Anchor.BOUND_UNSPECIFIED;
        for (Module module : mModules) {
            if (module.getRight() != Module.BOUND_UNKNOWN || module.getRight() != Module.BOUND_UNSPECIFIED)
                right = Math.max(module.getRight(), right);
        }
        return right;
    }

    public int getBottom() {
        int bottom = Anchor.BOUND_UNSPECIFIED;
        for (Module module : mModules) {
            if (module.getBottom() != Module.BOUND_UNKNOWN || module.getBottom() != Module.BOUND_UNSPECIFIED)
                bottom = Math.max(module.getBottom(), bottom);
        }
        return bottom;
    }
}
