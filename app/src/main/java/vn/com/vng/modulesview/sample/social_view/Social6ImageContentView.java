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

public class Social6ImageContentView extends SocialImageContentView {
    public Social6ImageContentView(Context context) {
        this(context, null);
    }

    public Social6ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social6ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social6ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getImagesContentCount() {
        return 6;
    }

    @Override
    protected void configModules(List<ImageModule> imageModules) {
        super.configModules(imageModules);
        int widthSize = mScreenWidth;

        ImageModule img1 = imageModules.get(0);
        ImageModule img2 = imageModules.get(1);
        ImageModule img3 = imageModules.get(2);
        ImageModule img4 = imageModules.get(3);

        ImageModule img5 = imageModules.get(4);
        ImageModule img6 = imageModules.get(5);
//
        int temp1 = (int) (widthSize * 2f / 3);
        int temp2 = widthSize / 3;

        img1.getModuleParams()
                .setDimensions(temp1, temp1)
                .setPadding(0, 0, dp(1), dp(1));
        img2.getModuleParams()
                .setDimensions(temp2,temp2)
                .anchorLeftTo(img1)
                .setPadding(dp(1),0,0,dp(1));
        img3.getModuleParams()
                .setDimensions(temp2,temp2)
                .anchorLeftTo(img1)
                .anchorTopTo(img2)
                .setPadding(dp(1),dp(1),0,dp(1));

        img4.getModuleParams()
                .setDimensions(temp2,temp2)
                .anchorTopTo(img1)
                .setPadding(0,dp(1),dp(1), 0);

        img5.getModuleParams()
                .setDimensions(temp2,temp2)
                .anchorTopTo(img1)
                .anchorLeftTo(img4)
                .setPadding(dp(1),dp(1),dp(1), 0);

        img6.getModuleParams()
                .setDimensions(temp2,temp2)
                .anchorTopTo(img3)
                .anchorLeftTo(img5)
                .setPadding(dp(1),dp(1),0, 0);

    }
}
