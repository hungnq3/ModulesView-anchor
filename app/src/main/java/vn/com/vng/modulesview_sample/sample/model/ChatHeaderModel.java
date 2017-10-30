package vn.com.vng.modulesview_sample.sample.model;

/**
 * Created by HungNQ on 23/10/2017.
 */

public class ChatHeaderModel extends BaseModel {
    private String mAvatarUrl;
    private String mName;
    private String mMessage;
    private String mTime;
    private int mNewCount;
    private boolean mNotificationOff;

    public ChatHeaderModel(String avatarUrl, String name, String message, String time, int newCount, boolean notificationOff) {
        mAvatarUrl = avatarUrl;
        mName = name;
        mMessage = message;
        mTime = time;
        mNewCount = newCount;
        mNotificationOff = notificationOff;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public int getNewCount() {
        return mNewCount;
    }

    public void setNewCount(int newCount) {
        mNewCount = newCount;
    }

    public boolean isNotificationOff() {
        return mNotificationOff;
    }

    public void setNotificationOff(boolean notificationOff) {
        mNotificationOff = notificationOff;
    }
}
