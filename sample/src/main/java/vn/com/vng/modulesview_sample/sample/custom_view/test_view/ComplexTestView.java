package vn.com.vng.modulesview_sample.sample.custom_view.test_view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;

import vn.com.vng.modulesview.GravityCompat;
import vn.com.vng.modulesview.GroupModule;
import vn.com.vng.modulesview.Guideline;
import vn.com.vng.modulesview.LayoutParams;
import vn.com.vng.modulesview.ModulesView;
import vn.com.vng.modulesview.widget.ImageModule;
import vn.com.vng.modulesview.widget.TextModule;
import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 10/11/2017.
 */

public class ComplexTestView extends ModulesView {
    public ComplexTestView(Context context) {
        super(context);
        init();
    }

    public ComplexTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComplexTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ComplexTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    GroupModule mGroup;
    ImageModule mImageAvatar;
    TextModule mTextName;
    TextModule mTextStatus;

    GroupModule mImageGroup;
    ImageModule mImage1, mImage2, mImage3;
    ImageModule mImagePlayButton;
    TextModule mTextMore;

    TextModule mTextTime;

    private final int mGroupHorizontalMargin = dp(40);
    private final int mGroupPadding = dp(12);

    private final int mAvaSize = dp(60);
    private final int mTextNameMarginLeft = dp(12);
    private final int mStatusTextSize = dp(12f);
    private final int mStatusTextMarginTop = dp(2);
    private final int mTextMoreSize = dp(18);
    private final int mTextTimeSize = sp(11f);
    private final int mTextTimePaddingHorizontal = dp(8);
    private final int mTextTimePaddingVertical = dp(2);
    private final int mTextTimeMarginTop = dp(8);
    private final int mImageGroupMarginTop = dp(12);

    private static final int mTextNameColor = 0xff000000;
    private static final int mTextMoreColor = 0xffffffff;
    private static final int mTextTimeColor = 0xffffffff;
    private static final int mImagePlayButtonBackgroundColor = 0x22000000;
    private static final int mTextMoreBackground = 0x55000000;

    private static final int mCornersLayoutBackgroundResId = R.drawable.bg_corners;
    private static final int mChipBackgroundResId = R.drawable.chip_background;

    private static final int mAvaResId = R.drawable.img;
    private static final int mImgResId1 = R.drawable.img5;
    private static final int mImgResId2 = R.drawable.img7;
    private static final int mImgResId3 = R.drawable.img6;
    private static final int mPlayButtonResId = R.drawable.ic_play_button;


    private void init() {
        setSize(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setPadding(0, dp(4), 0, dp(4));

        mGroup = new GroupModule(getContext());
        mGroup.setBackgroundResId(mCornersLayoutBackgroundResId);
        mGroup.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setMargin(mGroupHorizontalMargin, 0, mGroupHorizontalMargin, 0)
                .setPadding(mGroupPadding)
                .setCenterInHorizontal(true) ;

        mImageAvatar = new ImageModule(getContext());
        mImageAvatar.setScaleType(ImageModule.CENTER_CROP);
        mImageAvatar.setRoundCorner(ImageModule.ROUND_CIRCLE);
        mImageAvatar.setImageResource(mAvaResId);
        mImageAvatar.getLayoutParams()
                .setDimensions(mAvaSize, mAvaSize);

        mTextName = new TextModule(getContext());
        mTextName.setTextStyle(TextModule.TEXT_STYLE_BOLD);
        mTextName.setTextColor(mTextNameColor);
        mTextName.setText("Name");
        mTextName.setMaxLines(1);
        mTextName.getLayoutParams()
                .setMarginLeft(mTextNameMarginLeft)
                .setToRightOf(mImageAvatar);

        mTextStatus = new TextModule(getContext());
        mTextStatus.setTextSize(mStatusTextSize);
        mTextStatus.setText("Let's start this conversation with great stories!");
        mTextStatus.setMaxLines(2);
        mTextStatus.getLayoutParams()
                .setBelowOf(mTextName)
                .setAlignLeft(mTextName)
                .setMarginTop(mStatusTextMarginTop);


        mImageGroup = new GroupModule(getContext());
        mImageGroup.getLayoutParams()
                .setBelowOf(mImageAvatar)
                .setMarginTop(mImageGroupMarginTop)
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        Guideline guideline1= new Guideline(1/3f, 0f);
        Guideline guideline2= new Guideline(2/3f, 0f);

        mImage1 = new ImageModule(getContext());
        mImage1.setScaleType(ImageModule.CENTER_CROP);
        mImage1.setImageResource(mImgResId1);
        mImage1.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 0)
                .setAspectRatioHeight(1f)
                .setMarginRight(1)
                .setToLeftOf(guideline1);

        mImage2 = new ImageModule(getContext());
        mImage2.setScaleType(ImageModule.CENTER_CROP);
        mImage2.setImageResource(mImgResId2);
        mImage2.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 0)
                .setAspectRatioHeight(1f)
                .setMargin(1,0,1,0)
                .setToRightOf(guideline1)
                .setToLeftOf(guideline2);

        mImage3 = new ImageModule(getContext());
        mImage3.setScaleType(ImageModule.CENTER_CROP);
        mImage3.setImageResource(mImgResId3);
        mImage3.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 0)
                .setAspectRatioHeight(1f)
                .setMarginLeft(1)
                .setToRightOf(guideline2);

        mImagePlayButton = new ImageModule(getContext());
        mImagePlayButton.setBackgroundColor(mImagePlayButtonBackgroundColor);
        mImagePlayButton.setScaleType(ImageModule.CENTER_INSIDE);
        mImagePlayButton.setImageResource(mPlayButtonResId);
        mImagePlayButton.getLayoutParams()
                .setAlignLeft(mImage2)
                .setAlignTop(mImage2)
                .setAlignRight(mImage2)
                .setAlignBottom(mImage2);

        mTextMore = new TextModule(getContext());
        mTextMore.setTextSize(mTextMoreSize);
        mTextMore.setTextColor(mTextMoreColor);
        mTextMore.setText("+8");
        mTextMore.setBackgroundColor(mTextMoreBackground);
        mTextMore.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setAlignLeft(mImage3)
                .setAlignTop(mImage3)
                .setAlignRight(mImage3)
                .setAlignBottom(mImage3);


        mImageGroup.addModule(mImage1);
        mImageGroup.addModule(mImage2);
        mImageGroup.addModule(mImage3);
        mImageGroup.addModule(mImagePlayButton);
        mImageGroup.addModule(mTextMore);


        mTextTime  = new TextModule(getContext());
        mTextTime.setText( "9:08, 10/11/2017");
        mTextTime.setTextColor(mTextTimeColor);
        mTextTime.setBackgroundResId(mChipBackgroundResId);
        mTextTime.setTextSize(mTextTimeSize);
        mTextTime.getLayoutParams()
                .setPadding(mTextTimePaddingHorizontal, mTextTimePaddingVertical, mTextTimePaddingHorizontal, mTextTimePaddingVertical)
                .setMarginTop(mTextTimeMarginTop)
                .setCenterInHorizontal(true)
                .setBelowOf(mGroup);


        mGroup.addModule(mImageAvatar);
        mGroup.addModule(mTextName);
        mGroup.addModule(mTextStatus);
        mGroup.addModule(mImageGroup);


        addModule(mGroup);
        addModule(mTextTime);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long startTime = System.nanoTime();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        long endTime = System.nanoTime();
        Log.w("Measure time","[ModulesView]:    " + String.valueOf((endTime - startTime)/1000000f));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        long startTime = System.nanoTime();
        super.onLayout(changed, l, t, r, b);
        long endTime = System.nanoTime();
        Log.w("Layout time","[ModulesView]:    " + String.valueOf((endTime - startTime)/1000000f));

    }
}
