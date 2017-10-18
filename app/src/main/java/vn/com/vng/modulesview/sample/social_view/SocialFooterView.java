package vn.com.vng.modulesview.sample.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.ImageModule;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModuleParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.TextModule;
import vn.com.vng.modulesview.sample.model.SocialModel;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialFooterView extends ModulesView {


    public SocialFooterView(Context context) {
        this(context, null);
    }

    public SocialFooterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SocialFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SocialFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    //modules' properties
    private int mFooterMarginTop = dp(12);

    private int mIconSize = dp(24);

    private int mContentHeight = mIconSize + dp(8);

    private int mLikeTextWidth = dp(56);
    private int mCommentTextWidth = dp(56);

    private int mLeftMargin = dp(16);
    private int mRightMargin = dp(16);

    private int mBottomSeparatorSize = dp(12);

    private int mFooterHeight = mFooterMarginTop + 1 + mContentHeight + mBottomSeparatorSize;


    //modules
    private ImageModule mImgLike;
    private TextModule mTextLike;
    private TextModule mTextComment;
    private ImageModule mImgComment;
    private ImageModule mImgMore;
    private Module mTopLine;
    private Module mBottomLine;
    private Module mBottomSeparator;

    private Module.OnClickListener mOnLikeClickListener;
    private Module.OnClickListener mOnCommentClickListener;
    private Module.OnClickListener mOnMoreClickListener;

    private Module.OnLongClickListener mOnLikeLongClickListener;
    private Module.OnLongClickListener mOnCommentLongClickListener;
    private Module.OnLongClickListener mOnMoreLongClickListener;

    private void init() {
        //build modules


        mTopLine = new Module();
        mTopLine.getModuleParams()
                .setBackgroundColor(0xffcccccc)
                .setDimensions(ModuleParams.MATCH_PARENT, 1)
                .setMargin(mLeftMargin, mFooterMarginTop, mRightMargin, 0);


        mImgLike = buildLikeImgModule();
        mImgLike.getModuleParams()
                .anchorTopTo(mTopLine)
                .setMarginLeft(mLeftMargin)
                .setPadding(dp(4))
                .setDimensions(mIconSize, mIconSize);

        mTextLike = buildLikeTextModule();
        mTextLike.getModuleParams()
                .setDimensions(mLikeTextWidth, ModuleParams.WRAP_CONTENT)
                .anchorTopTo(mTopLine)
                .anchorLeftTo(mImgLike);

        mImgComment = buildCommentImgModule();
        mImgComment.getModuleParams()
                .setDimensions(mIconSize, mIconSize)
                .anchorTopTo(mTopLine)
                .anchorLeftTo(mTextLike)
                .setPadding(dp(4));

        mTextComment = buildCommentTextModule();
        mTextComment.getModuleParams()
                .setDimensions(mCommentTextWidth, ModuleParams.WRAP_CONTENT)
                .anchorTopTo(mTopLine)
                .anchorLeftTo(mImgComment);


        mImgMore = buildMoreImgModule();
        mImgMore.getModuleParams()
                .setDimensions(mIconSize, mIconSize)
                .anchorTopTo(mTopLine)
                .anchorRightToParent(true)
                .setMarginRight(mRightMargin)
                .setPadding(dp(4));


        mBottomLine = new Module();
        mBottomLine.getModuleParams()
                .setDimensions(ModuleParams.MATCH_PARENT, 1)
                .setBackgroundColor(0xffcccccc)
                .setMarginTop(dp(8))
                .anchorTopTo(mImgLike);

        mBottomSeparator = new Module();
        mBottomSeparator.getModuleParams()
                .anchorTopTo(mBottomLine)
                .setDimensions(ModuleParams.MATCH_PARENT, mBottomSeparatorSize)
                .setBackgroundColor(0xffe4e5e5);
        //add modules
        addModule(mTopLine);
        addModule(mImgLike);
        addModule(mTextLike);
        addModule(mImgComment);
        addModule(mTextComment);
        addModule(mImgMore);
        addModule(mBottomLine);
        addModule(mBottomSeparator);

    }


    private ImageModule buildLikeImgModule() {
        ImageModule module = new ImageModule();
        module.setScaleType(ImageModule.FIT_CENTER);
        module.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_heart));
        return module;
    }

    private TextModule buildLikeTextModule() {
        TextModule module = new TextModule();
        module.setTextSize(sp(14));
        module.setTextColor(0xff222222);
        return module;
    }

    private ImageModule buildCommentImgModule() {
        ImageModule module;
        module = new ImageModule();
        module.setScaleType(ImageModule.FIT_CENTER);
        module.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_comment));
        return module;
    }

    private TextModule buildCommentTextModule() {
        TextModule module = new TextModule();
        module.setTextSize(sp(14));
        module.setTextColor(0xff222222);
        return module;
    }


    private ImageModule buildMoreImgModule() {
        ImageModule module = new ImageModule();
        module.setScaleType(ImageModule.FIT_CENTER);
        module.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_more));
        return module;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       setMeasureDimension(getMeasuredWidth(), mFooterHeight);
    }



    //-------------------bind data-----------------------------------

    public void bindModel(SocialModel model) {
        if (model != null) {
            mTextLike.setText(String.valueOf(model.getLikeCount()));
            mTextComment.setText(String.valueOf(model.getCommentCount()));
        } else {
            mTextLike.setText("0");
            mTextComment.setText("0");
        }
        mTextLike.configModule();
        mTextComment.configModule();
    }


    //-----------------listener------------

    public void setOnLikeClickListener(Module.OnClickListener onLikeClickListener) {
        mOnLikeClickListener = onLikeClickListener;
        mImgLike.setOnClickListener(mOnLikeClickListener);
        mTextLike.setOnClickListener(mOnLikeClickListener);
    }

    public void setOnCommentClickListener(Module.OnClickListener onCommentClickListener) {
        mOnCommentClickListener = onCommentClickListener;
        mImgComment.setOnClickListener(mOnCommentClickListener);
        mTextComment.setOnClickListener(mOnCommentClickListener);
    }

    public void setOnMoreClickListener(Module.OnClickListener onMoreClickListener) {
        mOnMoreClickListener = onMoreClickListener;
        mImgMore.setOnClickListener(mOnMoreClickListener);
    }

    public void setOnLikeLongClickListener(Module.OnLongClickListener onLikeLongClickListener) {
        mOnLikeLongClickListener = onLikeLongClickListener;
        mImgLike.setOnLongClickListener(mOnLikeLongClickListener);
        mTextLike.setOnLongClickListener(mOnLikeLongClickListener);
    }

    public void setOnCommentLongClickListener(Module.OnLongClickListener onCommentLongClickListener) {
        mOnCommentLongClickListener = onCommentLongClickListener;
        mImgComment.setOnLongClickListener(mOnCommentLongClickListener);
        mTextComment.setOnLongClickListener(mOnCommentLongClickListener);
    }

    public void setOnMoreLongClickListener(Module.OnLongClickListener onMoreLongClickListener) {
        mOnMoreLongClickListener = onMoreLongClickListener;
        mImgMore.setOnLongClickListener(mOnMoreLongClickListener);
    }
}
