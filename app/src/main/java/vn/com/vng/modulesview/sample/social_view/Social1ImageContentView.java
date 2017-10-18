package vn.com.vng.modulesview.sample.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.List;

import vn.com.vng.modulesview.modules_view.ImageModule;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class Social1ImageContentView extends SocialImageContentView {
    public Social1ImageContentView(Context context) {
        this(context, null);
    }

    public Social1ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social1ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social1ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getImagesContentCount() {
        return 1;
    }

    @Override
    protected void configModules(List<ImageModule> imageModules) {
        super.configModules(imageModules);
        int widthSize = getMeasuredWidth();

        ImageModule img1 = imageModules.get(0);
        img1.getModuleParams()
        .setDimensions(mScreenWidth, mScreenWidth);

    }
}
