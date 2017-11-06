package vn.com.vng.modulesview_sample.sample.adapter.view_item.friend_list;

import vn.com.vng.modulesview_sample.sample.adapter.ViewType;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.model.FriendModel;

/**
 * Created by HungNQ on 27/10/2017.
 */

public class FriendViewItem extends BaseViewItem {

    private FriendModel mModel;

    public FriendViewItem(FriendModel model) {
        mModel = model;
    }

    public FriendModel getModel() {
        return mModel;
    }

    public void setModel(FriendModel model) {
        mModel = model;
    }

    @Override
    public int getViewType() {
        return ViewType.FRIEND_ITEM;
    }
}
