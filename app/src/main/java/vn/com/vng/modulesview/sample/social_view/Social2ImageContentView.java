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

public class Social2ImageContentView extends SocialImageContentView {
    public Social2ImageContentView(Context context) {
        this(context, null);
    }

    public Social2ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social2ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social2ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getImagesContentCount() {
        return 2;
    }

    @Override
    protected void configModules(List<ImageModule> imageModules) {
        super.configModules(imageModules);
        int widthSize = mScreenWidth;
        ImageModule img1 = imageModules.get(0);
        ImageModule img2 = imageModules.get(1);

        //1 left - 1 right
        int temp = (int) (widthSize / 2f);
        img1.getModuleParams()
                .setPaddingRight(dp(1))
                .setDimensions(temp, widthSize);

        img2.getModuleParams()
                .setHeightDimension(widthSize)
                .anchorLeftTo(img1)
                .anchorRightToParent(true)
                .setPaddingLeft(dp(1));

    }
}
