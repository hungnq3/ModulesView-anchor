package vn.com.vng.modulesview.sample.social_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.TextModule;
import vn.com.vng.modulesview.sample.model.SocialModel;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialHeaderView extends ModulesView {

    public SocialHeaderView(Context context) {
        this(context, null);
    }

    public SocialHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SocialHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SocialHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //module's properties
    private int mAvaSize = dp(40);

    private int mAvaMargin = dp(8);
    private int mHeaderHeight = mAvaSize + mAvaMargin * 2;

    private int mNameTextSize = sp(16);
    private static int mNameTextColor = 0xff050505;
    private int mTimeTextSize = sp(12);
    private static int mTimeTextColor = 0xffaaaaaa;


    //modules
    ImageModule mAvaImgModule;
    TextModule mNameTextModule;
    TextModule mTimeTextModule;
    private Module mTopLine;

    //properties
    Module.OnClickListener mOnAvaClickListener;
    Module.OnClickListener mOnNameClickListener;
    Module.OnClickListener mOnTimeClickListener;
    Module.OnLongClickListener mOnAvaLongClickListener;
    Module.OnLongClickListener mOnNameLongClickListener;
    Module.OnLongClickListener mOnTimeLongClickListener;

    private void init() {
        mAvaImgModule = buildAvaModule();
        mAvaImgModule.getLayoutParams()
                .setMargin(mAvaMargin)
                .setWidthDimension(mAvaSize)
                .setHeightDimension(mAvaSize);


        mNameTextModule = buildNameModule();
        mNameTextModule.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToRight(mAvaImgModule)
                .setMarginLeft(dp(8))
                .anchorTopToTop(mAvaImgModule);

        mTimeTextModule = buildTimeModule();
        mTimeTextModule.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToLeft(mNameTextModule)
                .anchorTopToBottom(mNameTextModule);

        mTopLine = new Module(getContext());
        mTopLine.setBackgroundColor(0xffcccccc);
        mTopLine.getLayoutParams()
                .setWidthDimension(LayoutParams.MATCH_PARENT)
                .setHeightDimension(1);

        addModule(mAvaImgModule);
        addModule(mNameTextModule);
        addModule(mTimeTextModule);
        addModule(mTopLine);
    }


    private ImageModule buildAvaModule() {
        ImageModule module = new ImageModule(getContext());
        module.setScaleType(ImageModule.CENTER_CROP);
        module.setRoundCorner(ImageModule.ROUND_CIRCLE);

        return module;
    }

    private TextModule buildNameModule() {
        TextModule module = new TextModule(getContext());
        module.setTextSize(mNameTextSize);
        module.setTextColor(mNameTextColor);

        return module;
    }

    private TextModule buildTimeModule() {
        TextModule module = new TextModule(getContext());
        module.setTextSize(mTimeTextSize);
        module.setTextColor(mTimeTextColor);
        return module;
    }

//    @Override
//    protected void onPreMeasureChildren(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(getMeasuredWidth(), mHeaderHeight +1);
//
//    }

    //-----------------listener------------

    public void setOnAvaClickListener(Module.OnClickListener onAvaClickListener) {
        mOnAvaClickListener = onAvaClickListener;
        mAvaImgModule.setOnClickListener(mOnAvaClickListener);
    }


    public void setOnNameClickListener(Module.OnClickListener onNameClickListener) {
        mOnNameClickListener = onNameClickListener;
        mNameTextModule.setOnClickListener(mOnNameClickListener);
    }

    public void setOnTimeClickListener(Module.OnClickListener onTimeClickListener) {
        mOnTimeClickListener = onTimeClickListener;
        mTimeTextModule.setOnClickListener(mOnTimeClickListener);
    }

    public void setOnAvaLongClickListener(Module.OnLongClickListener onAvaLongClickListener) {
        mOnAvaLongClickListener = onAvaLongClickListener;
        mAvaImgModule.setOnLongClickListener(mOnAvaLongClickListener);
    }

    public void setOnNameLongClickListener(Module.OnLongClickListener onNameLongClickListener) {
        mOnNameLongClickListener = onNameLongClickListener;
        mNameTextModule.setOnLongClickListener(mOnNameLongClickListener);
    }

    public void setOnTimeLongClickListener(Module.OnLongClickListener onTimeLongClickListener) {
        mOnTimeLongClickListener = onTimeLongClickListener;
        mTimeTextModule.setOnLongClickListener(mOnTimeLongClickListener);
    }


    //--------------------bind data--------------------------


    public void bindHeader(final SocialModel model) {
        if (model != null) {
            if (mAvaImgModule.getWidth() > 0 || mAvaImgModule.getHeight() > 0)
                mAvaImgModule.loadImage(model.getAvatar(), R.drawable.img_place_holder, R.drawable.img_error);
            else
                post(new Runnable() {
                    @Override
                    public void run() {
                        mAvaImgModule.loadImage(model.getAvatar(), R.drawable.img_place_holder, R.drawable.img_error);
                    }
                });

            mNameTextModule.setText(model.getName());
            mTimeTextModule.setText(model.getTime());
        } else {
            mAvaImgModule.setBitmap(null);
            mNameTextModule.setText(null);
            mTimeTextModule.setText(null);
        }
    }

}
