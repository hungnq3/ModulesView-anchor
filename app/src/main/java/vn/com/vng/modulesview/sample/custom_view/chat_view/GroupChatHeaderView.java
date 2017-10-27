package vn.com.vng.modulesview.sample.custom_view.chat_view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.view.ViewGroup;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.GravityCompat;
import vn.com.vng.modulesview.modules_view.GroupModule;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.widget.TextModule;
import vn.com.vng.modulesview.sample.model.GroupChatHeaderModel;

/**
 * Created by HungNQ on 26/10/2017.
 */

public class GroupChatHeaderView extends ModulesView {
    public GroupChatHeaderView(Context context) {
        super(context);
        init();
    }

    private final int mImageHeaderSize = dp(60);

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


    GroupModule mGroupHeaderImages;
    ImageModule mHeaderImage1;
    ImageModule mHeaderImage2;
    ImageModule mHeaderImage3;
    ImageModule mHeaderImage4;
    TextModule mMoreMembersText;

    TextModule mCircularNewCountText;
    TextModule mTitleText;
    TextModule mMessageText;
    TextModule mTimeText;
    ImageModule mNotificationOff;
    Module mBottomLine;


    private void init() {
        setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mGroupHeaderImages = new GroupModule(getContext());
        mGroupHeaderImages.getLayoutParams()
                .setDimensions(mImageHeaderSize, mImageHeaderSize)
                .setMargin(dp(12), dp(4), dp(8), dp(4));

//        mHeaderImage = new ImageModule(getContext());
//        mHeaderImage.setScaleType(ImageModule.CENTER_CROP);
//        mHeaderImage.setRoundCorner(ImageModule.ROUND_CIRCLE);
//        mHeaderImage.getLayoutParams()
//                .setDimensions(mImageSize, mImageSize)
//                .setMargin(dp(12),dp(4),dp(8),dp(4));

        mCircularNewCountText = new TextModule(getContext());
        mCircularNewCountText.setTextSize(mCircularIndexTextSize);
        mCircularNewCountText.setTextColor(mCircularIndexTextColor);
        mCircularNewCountText.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.header_circle_point_background));
        mCircularNewCountText.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mCircularNewCountText.setTypeFace(Typeface.DEFAULT_BOLD);
        mCircularNewCountText.getLayoutParams()
                .setGravity(GravityCompat.CENTER)
                .setDimensions(mCircularIndexSize, mCircularIndexSize)
                .anchorRightToRight(mGroupHeaderImages)
                .anchorTopToTop(mGroupHeaderImages);

        mTitleText = new TextModule(getContext());
        mTitleText.setTextColor(mTitleTextColor);
        mTitleText.setTextSize(mTitleTextSize);
        mTitleText.setEllipsize(TextUtils.TruncateAt.END);
        mTitleText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setMargin(dp(12), dp(4), dp(4), dp(4))
                .anchorLeftToRight(mGroupHeaderImages)
                .anchorRightToLeft(mTimeText)
                .anchorTopToTop(mGroupHeaderImages);


        mTimeText = new TextModule(getContext());
        mTimeText.setTextSize(mTimeTextSize);
        mTimeText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .setMargin(0, 0, dp(12), 0)
                .anchorTopToTop(mTitleText)
                .anchorRightToParent(true);


        mNotificationOff = new ImageModule(getContext());
        mNotificationOff.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_off));
        mNotificationOff.getLayoutParams()
                .setDimensions(dp(18), dp(18))
                .anchorRightToRight(mTimeText)
                .anchorTopToBottom(mTimeText)
                .setMarginTop(dp(4));

        mMessageText = new TextModule(getContext());
        mMessageText.setTextSize(mMessageTextSize);
        mMessageText.setMaxLines(1);
        mMessageText.setEllipsize(TextUtils.TruncateAt.END);
        mMessageText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToLeft(mTitleText)
                .anchorRightToLeft(mNotificationOff)
                .anchorTopToBottom(mTitleText);
//                .setMarginRight(dp(4));

        mBottomLine = new Module(getContext());
        mBottomLine.setBackgroundColor(0xffcccccc);
        mBottomLine.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, 1)
                .anchorLeftToLeft(mTitleText)
                .anchorBottomToParent(true);

        addModule(mGroupHeaderImages);
        addModule(mCircularNewCountText);
        addModule(mTitleText);
        addModule(mTimeText);
        addModule(mNotificationOff);
        addModule(mMessageText);
        addModule(mBottomLine);
    }


    private void initImageHeaders(int membersCount) {
        int imageSize = mImageHeaderSize / 2 - dp(1);

        if(membersCount >=3 ) {
            if (mHeaderImage1 == null) {
                mHeaderImage1 = new ImageModule(getContext());
                mHeaderImage1.setScaleType(ImageModule.CENTER_CROP);
                mHeaderImage1.setRoundCorner(ImageModule.ROUND_CIRCLE);
            }
            if (mHeaderImage2 == null) {
                mHeaderImage2 = new ImageModule(getContext());
                mHeaderImage2.setScaleType(ImageModule.CENTER_CROP);
                mHeaderImage2.setRoundCorner(ImageModule.ROUND_CIRCLE);
            }
            if (mHeaderImage3 == null) {
                mHeaderImage3 = new ImageModule(getContext());
                mHeaderImage3.setScaleType(ImageModule.CENTER_CROP);
                mHeaderImage3.setRoundCorner(ImageModule.ROUND_CIRCLE);
            }
        }
        if (membersCount == 4) {
            if (mHeaderImage4 == null) {
                mHeaderImage4 = new ImageModule(getContext());
                mHeaderImage4.setScaleType(ImageModule.CENTER_CROP);
                mHeaderImage4.setRoundCorner(ImageModule.ROUND_CIRCLE);
                mHeaderImage4.getLayoutParams()
                        .setDimensions(imageSize, imageSize)
                        .anchorRightToParent(true)
                        .anchorBottomToParent(true);
            }
        } else if (membersCount > 4) {
            if (mMoreMembersText == null) {
                mMoreMembersText = new TextModule(getContext());
                mMoreMembersText.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_gray_background));
                mMoreMembersText.setTextColor(0xffffffff);
                mMoreMembersText.setAlignment(Layout.Alignment.ALIGN_CENTER);
                mMoreMembersText.getLayoutParams()
                        .setGravity(GravityCompat.CENTER)
                        .setDimensions(imageSize, imageSize)
                        .anchorRightToParent(true)
                        .anchorBottomToParent(true);
            }
        }

//        init
        mGroupHeaderImages.clearModules();
        mGroupHeaderImages.getLayoutParams().setPaddingBottom(0);
        if (membersCount < 3) {
            mGroupHeaderImages.clearModules();
        } else if (membersCount == 3) {
            mGroupHeaderImages.getLayoutParams().setPaddingBottom(dp(2));
            mHeaderImage1.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorRightToLeft((Module) null)
                    .anchorLeftToRight((Module) null)
                    .anchorTopToParent(true)
                    .setCenterInHorizontal(true);
            mHeaderImage2.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToParent(true)
                    .anchorRightToLeft((Module) null)
                    .anchorTopToParent(false)
                    .anchorBottomToParent(true);
            mHeaderImage3.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToRight((Module) null)
                    .anchorRightToParent(true)
                    .anchorBottomToParent(true);
            mGroupHeaderImages.addModule(mHeaderImage1);
            mGroupHeaderImages.addModule(mHeaderImage2);
            mGroupHeaderImages.addModule(mHeaderImage3);
        } else if (membersCount == 4) {
            mHeaderImage1.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToParent(true)
                    .anchorRightToLeft((Module) null)
                    .anchorTopToParent(true)
                    .setCenterInHorizontal(false);
            mHeaderImage2.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToRight((Module) null)
                    .anchorRightToParent(true)
                    .anchorTopToParent(true)
                    .anchorBottomToParent(false);
            mHeaderImage3.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToParent(true)
                    .anchorRightToLeft((Module) null)
                    .anchorBottomToParent(true);


            mGroupHeaderImages.addModule(mHeaderImage1);
            mGroupHeaderImages.addModule(mHeaderImage2);
            mGroupHeaderImages.addModule(mHeaderImage3);
            mGroupHeaderImages.addModule(mHeaderImage4);
        } else {
            mHeaderImage1.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToParent(true)
                    .anchorRightToLeft((Module) null)
                    .anchorTopToParent(true)
                    .setCenterInHorizontal(false);
            mHeaderImage2.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToRight((Module) null)
                    .anchorRightToParent(true)
                    .anchorTopToParent(true)
                    .anchorBottomToParent(false);
            mHeaderImage3.getLayoutParams()
                    .setDimensions(imageSize, imageSize)
                    .anchorLeftToParent(true)
                    .anchorRightToLeft((Module) null)
                    .anchorBottomToParent(true);

            mGroupHeaderImages.addModule(mHeaderImage1);
            mGroupHeaderImages.addModule(mHeaderImage2);
            mGroupHeaderImages.addModule(mHeaderImage3);
            mGroupHeaderImages.addModule(mMoreMembersText);
        }

    }


    public void bindData(GroupChatHeaderModel model) {
        if (model != null) {
            initImageHeaders(model.getMembersCount());
            if (model.getMembersCount() > 2) {
                mHeaderImage1.loadImage(model.getAvatars() != null && model.getAvatars().size() > 0 ? model.getAvatars().get(0) : null);
                mHeaderImage2.loadImage(model.getAvatars() != null && model.getAvatars().size() > 1 ? model.getAvatars().get(1) : null);
                mHeaderImage3.loadImage(model.getAvatars() != null && model.getAvatars().size() > 2 ? model.getAvatars().get(2) : null);
                if (model.getMembersCount() == 4)
                    mHeaderImage4.loadImage(model.getAvatars() != null && model.getAvatars().size() > 3 ? model.getAvatars().get(3) : null);
                else if (model.getMembersCount() > 4)
                    mMoreMembersText.setText(String.valueOf(model.getMembersCount() - 3));
            }

            mCircularNewCountText.getLayoutParams().setVisibility(model.getNewCount() > 0 ? LayoutParams.VISIBLE : LayoutParams.GONE);
            mCircularNewCountText.setText(model.getNewCount() < 6 ? String.valueOf(model.getNewCount()) : "5+");
            mTitleText.setText(model.getName());
            mTimeText.setText(model.getTime());
            mMessageText.setText(model.getMessage());
            mNotificationOff.getLayoutParams().setVisibility(model.isNotificationOff() ? LayoutParams.VISIBLE : LayoutParams.GONE);

        } else {
            initImageHeaders(0);
            mCircularNewCountText.getLayoutParams().setVisibility(LayoutParams.GONE);
            mTitleText.setText(null);
            mTimeText.setText(null);
            mMessageText.setText(null);
            mNotificationOff.getLayoutParams().setVisibility(LayoutParams.GONE);
        }
    }
}
