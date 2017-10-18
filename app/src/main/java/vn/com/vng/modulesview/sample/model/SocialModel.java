package vn.com.vng.modulesview.sample.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by HungNQ on 12/09/2017.
 */

public class SocialModel {
    private String mAvatar;
    private String mName;
    private String mTime;
    private String mContent;
    private int mCommentCount;
    private int mLikeCount;
    private List<String> mImages;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public void setLikeCount(int likeCount) {
        mLikeCount = likeCount;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }
}
