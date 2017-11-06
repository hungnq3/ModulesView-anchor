package vn.com.vng.modulesview_sample.sample.adapter.holder.friend_list;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.friend_list.FriendViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.friend_list.FriendView;

/**
 * Created by HungNQ on 27/10/2017.
 */

public class FriendViewHolder extends BaseViewHolder<FriendViewItem> {
    FriendView mView;
    public FriendViewHolder(FriendView itemView) {
        super(itemView);
        mView = itemView;
    }

    @Override
    public void onBind(FriendViewItem item) {
        super.onBind(item);
        if(mView != null && item != null)
            mView.bindData(item.getModel());
    }
}
