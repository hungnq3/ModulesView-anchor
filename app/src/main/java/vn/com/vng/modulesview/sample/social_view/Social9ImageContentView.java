package vn.com.vng.modulesview.sample.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.List;

import vn.com.vng.modulesview.modules_view.widget.ImageModule;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class Social9ImageContentView extends SocialImageContentView {
    public Social9ImageContentView(Context context) {
        this(context, null);
    }

    public Social9ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social9ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social9ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getImagesContentCount() {
        return 9;
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
        ImageModule img7 = imageModules.get(6);
        ImageModule img8 = imageModules.get(7);
        ImageModule img9 = imageModules.get(8);


        int temp = widthSize / 3;

        img1.getLayoutParams()
                .setDimensions(temp, temp)
                .setPadding(0, 0, dp(1), dp(1));

        img2.getLayoutParams()
                .setDimensions(temp, temp)
                .anchorLeftTo(img1)
                .setPadding(dp(1), 0, dp(1), dp(1));

        img3.getLayoutParams()
                .setDimensions(temp, temp)
                .anchorLeftTo(img2)
                .setPadding(dp(1), 0, 0, dp(1));

        img4.getLayoutParams()
                .setDimensions(temp, temp)
                .anchorTopTo(img1)
                .setPadding(0, dp(1), dp(1), dp(1));

        img5.getLayoutParams()
                .setDimensions(temp,temp)
                .anchorTopTo(img2)
                .anchorLeftTo(img4)
                .setPadding(dp(1));

        img6.getLayoutParams()
                .setDimensions(temp,temp)
                .anchorLeftTo(img5)
                .anchorTopTo(img3)
                .setPadding(dp(1),dp(1),0,dp(1));

        img7.getLayoutParams()
                .setDimensions(temp,temp)
                .anchorTopTo(img4)
                .setPadding(0,dp(1),dp(1), 0);

        img8.getLayoutParams()
                .setDimensions(temp,temp)
                .anchorTopTo(img5)
                .anchorLeftTo(img7)
                .setPadding(dp(1),dp(1),dp(1), 0);

        img9.getLayoutParams()
                .setDimensions(temp,temp)
                .anchorTopTo(img6)
                .anchorLeftTo(img8)
                .setPadding(dp(1),dp(1),0, 0);
        
    }
}
