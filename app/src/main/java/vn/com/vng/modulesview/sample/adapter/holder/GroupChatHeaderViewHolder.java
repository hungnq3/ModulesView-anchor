package vn.com.vng.modulesview.sample.adapter.holder;

import vn.com.vng.modulesview.sample.adapter.view_item.ChatHeaderViewItem;
import vn.com.vng.modulesview.sample.adapter.view_item.GroupChatHeaderViewItem;
import vn.com.vng.modulesview.sample.chat_view.ChatHeaderView;
import vn.com.vng.modulesview.sample.chat_view.GroupChatHeaderView;
import vn.com.vng.modulesview.sample.model.GroupChatHeaderModel;

/**
 * Created by HungNQ on 23/10/2017.
 */

public class GroupChatHeaderViewHolder extends BaseViewHolder<GroupChatHeaderViewItem> {

    GroupChatHeaderView mView;

    public GroupChatHeaderViewHolder(GroupChatHeaderView itemView) {
        super(itemView);
            mView = itemView;
    }

    @Override
    public void onBind(GroupChatHeaderViewItem item) {
        super.onBind(item);
        if (mView != null && item != null)
            mView.bindData(item.getModel());

    }
}
