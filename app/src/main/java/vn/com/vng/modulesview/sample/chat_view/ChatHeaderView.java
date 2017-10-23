package vn.com.vng.modulesview.sample.chat_view;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.GravityCompat;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.widget.TextModule;
import vn.com.vng.modulesview.sample.model.ChatHeaderModel;

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


    private final int mImageSize = dp(60);

    private final int mTitleTextSize = sp(14);
    private final int mTitleTextMargin = dp(4);
    private static final int mTitleTextColor = 0xff050505;

    private final int mMessageTextSize = sp(14);
    private final int mMessageTextPadding = dp(4);


    private final int mTimeTextSize = sp(12);
    private final int mMessagePadding = dp(4);

    private final int mCircularIndexSize = dp(16);
    private final int mCircularIndexTextSize = sp(12);
    private static final int mCircularIndexTextColor = 0xfff5f5f5;


    ImageModule mHeaderImage;
    TextModule mCircularNewCountText;
    TextModule mTitleText;
    TextModule mMessageText;
    TextModule mTimeText;
    ImageModule mNotificationOff;
    Module mBottomLine;




    private void init() {

        setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mHeaderImage = new ImageModule();
        mHeaderImage.setScaleType(ImageModule.CENTER_CROP);
        mHeaderImage.setRoundCorner(ImageModule.ROUND_CIRCLE);
        mHeaderImage.getLayoutParams()
                .setDimensions(mImageSize, mImageSize)
                .setMargin(dp(12),dp(4),dp(8),dp(4));

        mCircularNewCountText = new TextModule();
        mCircularNewCountText.setTextSize(mCircularIndexTextSize);
        mCircularNewCountText.setTextColor(mCircularIndexTextColor);
        mCircularNewCountText.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.header_circle_point_background));
        mCircularNewCountText.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mCircularNewCountText.setTypeFace(Typeface.DEFAULT_BOLD);
        mCircularNewCountText.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setDimensions(mCircularIndexSize, mCircularIndexSize)
                .anchorRightToRight(mHeaderImage)
                .anchorTopToTop(mHeaderImage);

        mTitleText = new TextModule();
        mTitleText.setTextColor(mTitleTextColor);
        mTitleText.setTextSize(mTitleTextSize);
        mTitleText.setEllipsize(TextUtils.TruncateAt.END);
        mTitleText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setMargin(dp(12),dp(4),dp(4),dp(4))
                .anchorLeftToRight(mHeaderImage)
                .anchorRightToLeft(mTimeText)
                .anchorTopToTop(mHeaderImage);



        mTimeText = new TextModule();
        mTimeText.setTextSize(mTimeTextSize);
        mTimeText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setMargin(0, 0, dp(12),0)
                .anchorTopToTop(mTitleText)
                .anchorRightToParent(true);


        mNotificationOff = new ImageModule();
        mNotificationOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_off));
        mNotificationOff.getLayoutParams()
                .setDimensions(dp(18), dp(18))
                .anchorRightToRight(mTimeText)
                .anchorTopToBottom(mTimeText)
                .setMarginTop(dp(4));

        mMessageText = new TextModule();
        mMessageText.setTextSize(mMessageTextSize);
        mMessageText.setMaxLines(1);
        mMessageText.setEllipsize(TextUtils.TruncateAt.END);
        mMessageText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToLeft(mTitleText)
                .anchorRightToLeft(mNotificationOff)
                .anchorTopToBottom(mTitleText);
//                .setMarginRight(dp(4));

        mBottomLine = new Module();
        mBottomLine.setBackgroundColor(0xffcccccc);
        mBottomLine.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 1)
                .anchorLeftToLeft(mTitleText)
                .anchorBottomToParent(true);

        addModule(mHeaderImage);
        addModule(mCircularNewCountText);
        addModule(mTitleText);
        addModule(mTimeText);
        addModule(mNotificationOff);
        addModule(mMessageText);
        addModule(mBottomLine);

    }


    public void bindData(ChatHeaderModel model){
        if(model != null){
            mHeaderImage.loadImage(model.getAvatarUrl());
            mCircularNewCountText.getLayoutParams().setVisibility(model.getNewCount()>0?LayoutParams.VISIBLE : LayoutParams.GONE);
            mCircularNewCountText.setText(model.getNewCount()<6 ? String.valueOf(model.getNewCount()) : "5+");
            mTitleText.setText(model.getName());
            mTimeText.setText(model.getTime());
            mMessageText.setText(model.getMessage());
            mNotificationOff.getLayoutParams().setVisibility(model.isNotificationOff() ? LayoutParams.VISIBLE : LayoutParams.GONE);

        }else{
            mHeaderImage.setImageDrawable(null);
            mCircularNewCountText.getLayoutParams().setVisibility(LayoutParams.GONE);
            mTitleText.setText(null);
            mTimeText.setText(null);
            mMessageText.setText(null);
            mNotificationOff.getLayoutParams().setVisibility(LayoutParams.GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
