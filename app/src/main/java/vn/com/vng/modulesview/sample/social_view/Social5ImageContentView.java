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

public class Social5ImageContentView extends SocialImageContentView {
    public Social5ImageContentView(Context context) {
        this(context, null);
    }

    public Social5ImageContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Social5ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Social5ImageContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getImagesContentCount() {
        return 5;
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

        //2 above  - 3 bellow
        int temp1 = (int) (widthSize * 2f / 3);
        int temp2= widthSize /2;
        int temp3 =  widthSize / 3;

        img1.getLayoutParams()
                .setDimensions(temp2, temp1)
                .setPadding(0,0,dp(1), dp(1));
        img2.getLayoutParams()
                .setHeightDimension(temp1)
                .anchorLeftToRight(img1)
                .anchorRightToParent(true)
                .setPadding(dp(1),0,0,dp(1));

        img3.getLayoutParams()
                .setDimensions(temp3, temp3)
                .anchorTopToBottom(img1)
                .setPadding(0,0,dp(1), 0);
        img4.getLayoutParams()
                .setDimensions(temp3, temp3)
                .anchorTopToBottom(img1)
                .anchorLeftToRight(img3)
                .setPadding(dp(1),0,dp(1), 0);

        img5.getLayoutParams()
                .setDimensions(temp3, temp3)
                .anchorTopToBottom(img1)
                .anchorLeftToRight(img4)
                .setPadding(dp(1),0,0,0);

    }
}
