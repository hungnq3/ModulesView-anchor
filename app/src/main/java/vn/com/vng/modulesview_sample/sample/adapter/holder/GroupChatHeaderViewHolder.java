package vn.com.vng.modulesview_sample.sample.adapter.holder;

import vn.com.vng.modulesview_sample.sample.adapter.view_item.GroupChatHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.chat_view.GroupChatHeaderView;

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
