package vn.com.vng.modulesview.sample.social_view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.LinkedList;
import java.util.List;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModulesView;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialImageContentView extends ModulesView {
    private List<ImageModule> mImageModules;

    public SocialImageContentView(Context context) {
        this(context, null);
    }

    public SocialImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SocialImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SocialImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected int mScreenWidth;

    Module.OnClickListener mOnImageClickListener;
    Module.OnLongClickListener mOnImageLongClickListener;

    private void init() {
        mScreenWidth =getScreenWidth();
        mImageModules = buildImageModules(getImagesContentCount());
        addModules(mImageModules);

        configModules(mImageModules);
    }

    protected void configModules(List<ImageModule> imageModules) {

    }

    private int getScreenWidth() {
        Point point = new Point();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }


    protected List<ImageModule> buildImageModules(int n) {
        List<ImageModule> list;
        list = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            ImageModule imgModule = new ImageModule();
            imgModule.setScaleType(ImageModule.CENTER_CROP);
            list.add(imgModule);
        }
        return list;
    }

    public int getImagesContentCount() {
        return 0;
    }

    @Override
    protected void onPreMeasureChildren(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredWidth(), mScreenWidth);

    }


    public void bindImage(int position, Bitmap bitmap) {
        if (position < 0 || position >= getImagesContentCount())
            return;
        ImageModule imageModule = mImageModules.get(position);
        imageModule.setBitmap(bitmap);
        imageModule.configModule();
        imageModule.invalidate();
    }

    public void loadImage(int position, final String url) {
        if (position < 0 || position >= getImagesContentCount())
            return;
        final ImageModule imageModule = mImageModules.get(position);
        if (imageModule.getWidth() > 0 || imageModule.getHeight() > 0)
            imageModule.loadImage(url, R.drawable.img_place_holder, R.drawable.img_error);
        else
            post(new Runnable() {
                @Override
                public void run() {
                    imageModule.loadImage(url, R.drawable.img_place_holder, R.drawable.img_error);
                }
            });
    }

    //-----------------listener------------

    public void setOnImageClickListener(Module.OnClickListener onImageClickListener) {
        mOnImageClickListener = onImageClickListener;
        for (ImageModule imageModule : mImageModules) {
            imageModule.setOnClickListener(mOnImageClickListener);
        }
    }

    public void setOnImageLongClickListener(Module.OnLongClickListener onImageLongClickListener) {
        mOnImageLongClickListener = onImageLongClickListener;
        for (ImageModule imageModule : mImageModules) {
            imageModule.setOnLongClickListener(mOnImageLongClickListener);
        }
    }
}
