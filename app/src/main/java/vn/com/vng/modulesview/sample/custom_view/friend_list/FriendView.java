package vn.com.vng.modulesview.sample.custom_view.friend_list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;

import vn.com.vng.modulesview.R;
import vn.com.vng.modulesview.modules_view.Fence;
import vn.com.vng.modulesview.modules_view.GravityCompat;
import vn.com.vng.modulesview.modules_view.GroupModule;
import vn.com.vng.modulesview.modules_view.LayoutParams;
import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.modules_view.ModulesView;
import vn.com.vng.modulesview.modules_view.widget.ImageModule;
import vn.com.vng.modulesview.modules_view.widget.TextModule;
import vn.com.vng.modulesview.sample.model.FriendModel;

/**
 * Created by HungNQ on 27/10/2017.
 */

public class FriendView extends ModulesView {
    public FriendView(Context context) {
        super(context);
        init();
    }

    private final int mAvatarSize = dp(44);

    private final int mNameTextSize = sp(14);
    private final int mNameTextMargin = dp(4);
    private static final int mNameTextColor = 0xff050505;

    private final int mStatusTextSize = sp(12);
    private final int mStatusTextColor = 0xffaaaaaa;


    private final int mOnlinePointSize = dp(12);

    private final int mCallButtonWidth = dp(40);
    private final int mVoiceCallImgPadding = dp(11);
    private final int mVideoCallImgPadding = dp(10);


    ImageModule mAvatar;
    Module mOnlinePoint;
    GroupModule mContentGroup;
    TextModule mNameText;
    TextModule mStatusText;
    ImageModule mVoiceCallImg;
    ImageModule mVideoCallImg;


    private void init() {
        setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundResource(R.drawable.button_background);
        setClickable(true);

        mAvatar = new ImageModule(getContext());
        mAvatar.setScaleType(ImageModule.CENTER_CROP);
        mAvatar.setRoundCorner(ImageModule.ROUND_CIRCLE);
        mAvatar.getLayoutParams()
                .setMargin(dp(12), dp(4), dp(8), dp(4))
                .setDimensions(mAvatarSize, mAvatarSize);

        mOnlinePoint = new TextModule(getContext());
        mOnlinePoint.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.online_point_drawable));
        mOnlinePoint.getLayoutParams()
                .setDimensions(mOnlinePointSize, mOnlinePointSize)
                .anchorRightToRight(mAvatar)
                .anchorBottomToBottom(mAvatar);


        mVideoCallImg = new ImageModule(getContext());
        mVideoCallImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_video_call));
        mVideoCallImg.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.corner_button_background));
        mVideoCallImg.setOnClickListener(new Module.OnClickListener() {
            @Override
            public void onClick(Module module) {

            }
        });
        mVideoCallImg.getLayoutParams()
                .setDimensions(mCallButtonWidth, LayoutParams.MATCH_PARENT)
                .anchorRightToParent(true)
                .anchorTopToParent(true)
                .anchorBottomToParent(true)
                .setPadding(mVideoCallImgPadding);

        mVoiceCallImg = new ImageModule(getContext());
        mVoiceCallImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_phone_call));
        mVoiceCallImg.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.corner_button_background));
        mVoiceCallImg.setOnClickListener(new Module.OnClickListener() {
            @Override
            public void onClick(Module module) {

            }
        });
        mVoiceCallImg.getLayoutParams()
                .setDimensions(mCallButtonWidth, LayoutParams.MATCH_PARENT)
                .anchorRightToLeft(mVideoCallImg)
                .anchorTopToParent(true)
                .anchorBottomToParent(true)
                .setPadding(mVoiceCallImgPadding);


        mContentGroup = new GroupModule(getContext());
//        mContentGroup.setBackgroundColor(0xffdddddd);
        mContentGroup.getLayoutParams()
                .setDimensions(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                .setGravity(GravityCompat.CENTER_VERTICAL)
                .anchorLeftToRight(mAvatar)
                .anchorRightToLeft(mVoiceCallImg)
                .setCenterInVertical(true)
                .setPadding(dp(16), 0, dp(8), 0);

        mNameText = new TextModule(getContext());
        mNameText.setTextColor(mNameTextColor);
        mNameText.setTextSize(mNameTextSize);
        mNameText.setEllipsize(TextUtils.TruncateAt.END);
        mNameText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mStatusText = new TextModule(getContext());
        mStatusText.setTextSize(mStatusTextSize);
        mStatusText.setTextColor(mStatusTextColor);
        mStatusText.setMaxLines(1);
        mStatusText.setEllipsize(TextUtils.TruncateAt.END);
        mStatusText.getLayoutParams()
                .setDimensions(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .anchorLeftToLeft(mNameText)
                .anchorTopToBottom(mNameText);

        mContentGroup.addModule(mNameText);
        mContentGroup.addModule(mStatusText);

        addModule(mAvatar);
        addModule(mOnlinePoint);
        addModule(mVideoCallImg);
        addModule(mVoiceCallImg);
        addModule(mContentGroup);
    }


    public void bindData(FriendModel model) {
        if (model != null) {
            mAvatar.loadImage(model.getAvatar());
            mOnlinePoint.getLayoutParams().setVisibility(model.isOnline() ? LayoutParams.VISIBLE : LayoutParams.GONE);
            mNameText.setText(model.getName());
            mStatusText.setText(model.getStatus());
            mStatusText.getLayoutParams().setVisibility(TextUtils.isEmpty(model.getStatus()) ? LayoutParams.GONE : LayoutParams.VISIBLE);
        } else {
            mAvatar.setImageDrawable(null);
            mOnlinePoint.getLayoutParams().setVisibility(LayoutParams.GONE);
            mNameText.setText(null);
            mStatusText.setText(null);
            mStatusText.getLayoutParams().setVisibility(LayoutParams.GONE);
        }
        invalidate();
    }

}
