package vn.com.vng.modulesview.sample.adapter.holder;

import android.view.View;

import vn.com.vng.modulesview.sample.adapter.view_item.FriendViewItem;
import vn.com.vng.modulesview.sample.custom_view.friend_list.FriendView;

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
