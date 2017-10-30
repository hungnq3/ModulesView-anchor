package vn.com.vng.modulesview_sample.sample.adapter.holder;

import vn.com.vng.modulesview_sample.sample.adapter.view_item.ChatHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.chat_view.ChatHeaderView;

/**
 * Created by HungNQ on 23/10/2017.
 */

public class ChatHeaderViewHolder extends BaseViewHolder<ChatHeaderViewItem> {

    ChatHeaderView mView;

    public ChatHeaderViewHolder(ChatHeaderView itemView) {
        super(itemView);
            mView = itemView;
    }

    @Override
    public void onBind(ChatHeaderViewItem item) {
        super.onBind(item);
        if (mView != null && item != null)
            mView.bindData(item.getModel());

    }
}
