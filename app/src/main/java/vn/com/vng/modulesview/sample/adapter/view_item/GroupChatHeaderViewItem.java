package vn.com.vng.modulesview.sample.adapter.view_item;

import vn.com.vng.modulesview.sample.adapter.ViewType;
import vn.com.vng.modulesview.sample.model.ChatHeaderModel;
import vn.com.vng.modulesview.sample.model.GroupChatHeaderModel;

/**
 * Created by HungNQ on 23/10/2017.
 */

public class GroupChatHeaderViewItem extends BaseViewItem {

    private GroupChatHeaderModel mModel;

    public GroupChatHeaderViewItem(GroupChatHeaderModel model) {
        mModel = model;
    }

    public GroupChatHeaderModel getModel() {
        return mModel;
    }

    public void setModel(GroupChatHeaderModel model) {
        mModel = model;
    }

    @Override
    public int getViewType() {
        return ViewType.GROUP_CHAT_HEADER;
    }
}
