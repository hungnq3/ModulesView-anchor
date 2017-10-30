package vn.com.vng.modulesview_sample.sample.custom_view.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.List;

import vn.com.vng.modulesview.widget.ImageModule;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class Social3ImageContentView extends SocialImageContentView {
    public Social3ImageContentView(Context context) {
        this(context, null);
    }

    public Social3ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social3ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social3ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public int getImagesContentCount() {
        return 3;
    }

    @Override
    protected void configModules(List<ImageModule> imageModules) {
        super.configModules(imageModules);
        int widthSize = mScreenWidth;

        ImageModule img1 = imageModules.get(0);
        ImageModule img2 = imageModules.get(1);
        ImageModule img3 = imageModules.get(2);

        //1 left - 2 right
        int temp1 = (int) (widthSize * 2f / 3);
        int temp2 = (int) (widthSize / 2f);
        img1.getLayoutParams()
                .setDimensions(temp1, widthSize)
                .setPaddingRight(dp(1));
        img2.getLayoutParams()
                .setHeightDimension(temp2)
                .setToRightOf(img1)
                .setAlignParentRight(true)
                .setPadding(dp(1), 0, 0, dp(1));

        img3.getLayoutParams()
                .setHeightDimension(temp2)
                .setToRightOf(img1)
                .setAlignParentRight(true)
                .setBellowOf(img2)
                .setPadding(dp(1),dp(1),0,0);

    }
}
