package vn.com.vng.modulesview.sample.chat_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.LinkedList;
import java.util.List;

import vn.com.vng.modulesview.modules_view.GravityCompat;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.widget.TextModule;

/**
 * Created by HungNQ on 20/10/2017.
 */

public class ChatHeaderView extends ModulesView {
    public ChatHeaderView(Context context) {
        super(context);
        init();
    }

    public ChatHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChatHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private final int mImageSize = dp(100);
    private final int mImageMarginH = dp(16);
    private final int mImageMarginV = dp(8);

    private final int mTitleTextSize = sp(14);
    private final int mTitleTextPadding = dp(4);
    private static final int mTitleTextColor = 0xff050505;

    private final int mMessageTextSize = sp(12);
    private final int mMessageTextPadding = dp(4);


    private final int mTimeTextSize = sp(10);
    private final int mMessagePadding = dp(4);

    private final int mCircularIndexSize = dp(16);
    private final int mCircularIndexTextSize = sp(8);
    private static final int mCircularIndexTextColor = 0xfff5f5f5;
    private static final int mCircularIndexBackgroundColor = 0xfff50000;

    private final int mHeight = mImageSize + mImageMarginV*2;


    ImageModule mHeaderImage;
    TextModule mCircularIndexText;
    TextModule mTitleText;
    TextModule mMessageText;
    TextModule mTimeText;




    private void init() {
        mHeaderImage = new ImageModule();
        mHeaderImage.setScaleType(ImageModule.CENTER_CROP);
        mHeaderImage.setRoundCorner(ImageModule.ROUND_CIRCLE);
        mHeaderImage.getLayoutParams()
                .setMargin(mImageMarginH, mImageMarginV, mImageMarginH, mImageMarginV)
                .setDimensions(mImageSize, mImageSize);

        mCircularIndexText = new TextModule();
        mCircularIndexText.setTextSize(mCircularIndexTextSize);
        mCircularIndexText.setTextColor(mCircularIndexTextColor);
        mCircularIndexText.setBackgroundColor(mCircularIndexBackgroundColor);
        mCircularIndexText.getLayoutParams()
                .setDimensions(mCircularIndexSize, mCircularIndexSize)
                .setGravity(GravityCompat.CENTER)
                .anchorRightToRight(mHeaderImage)
                .anchorTopToTop(mHeaderImage);

        mTitleText = new TextModule();
        mTitleText.setTextColor(mTitleTextColor);
        mTitleText.setTextSize(mTitleTextSize);
        mTitleText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setPadding(mTitleTextPadding)
                .anchorLeftToRight(mHeaderImage)
                .anchorTopToTop(mHeaderImage);


        mMessageText = new TextModule();
        mMessageText.setTextSize(mMessageTextSize);
        mMessageText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setPadding(mTitleTextPadding)
                .anchorLeftToLeft(mTitleText)
                .anchorTopToBottom(mTitleText);

        mTimeText = new TextModule();
        mTimeText.setTextSize(mTimeTextSize);
        mTimeText.getLayoutParams()
                .anchorRightToParent(true)
                .anchorTopToTop(mTitleText)
                .setMarginRight(dp(16));

    }




}
