package vn.com.vng.modulesview.sample.adapter.holder;

import android.view.View;

import vn.com.vng.modulesview.sample.adapter.view_item.ChatHeaderViewItem;
import vn.com.vng.modulesview.sample.chat_view.ChatHeaderView;

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
