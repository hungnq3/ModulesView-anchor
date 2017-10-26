package vn.com.vng.modulesview.sample.model;

import java.util.List;

/**
 * Created by HungNQ on 26/10/2017.
 */

public class GroupChatHeaderModel extends BaseModel {
    private List<String> mAvatars ;
    private int mMembersCount;
    private String mName;
    private String mMessage;
    private String mTime;
    private int mNewCount;
    private boolean mNotificationOff;

    public GroupChatHeaderModel(List<String> avatars, int membersCount, String name, String message, String time, int newCount, boolean notificationOff) {
        mAvatars = avatars;
        mMembersCount = membersCount;
        mName = name;
        mMessage = message;
        mTime = time;
        mNewCount = newCount;
        mNotificationOff = notificationOff;
    }

    public List<String> getAvatars() {
        return mAvatars;
    }

    public void setAvatars(List<String> avatars) {
        mAvatars = avatars;
    }

    public int getMembersCount() {
        return mMembersCount;
    }

    public void setMembersCount(int membersCount) {
        mMembersCount = membersCount;
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
