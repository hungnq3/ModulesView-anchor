package vn.com.vng.modulesview.sample.model;

/**
 * Created by HungNQ on 27/10/2017.
 */

public class FriendModel extends BaseModel {
    private String mAvatar;
    private String mName;
    private String mStatus;
    private boolean mOnline;

    public FriendModel(String avatar, String name, String status, boolean online) {
        mAvatar = avatar;
        mName = name;
        mStatus = status;
        mOnline = online;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public void setOnline(boolean online) {
        mOnline = online;
    }
}
