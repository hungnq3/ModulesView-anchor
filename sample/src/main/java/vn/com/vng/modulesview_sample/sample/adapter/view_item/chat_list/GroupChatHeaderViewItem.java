package vn.com.vng.modulesview_sample.sample.adapter.view_item.chat_list;

import vn.com.vng.modulesview_sample.sample.adapter.ViewType;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.model.GroupChatHeaderModel;

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
