package vn.com.vng.modulesview_sample.sample.adapter.view_item.chat_list;

import vn.com.vng.modulesview_sample.sample.adapter.ViewType;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.model.ChatHeaderModel;

/**
 * Created by HungNQ on 23/10/2017.
 */

public class ChatHeaderViewItem extends BaseViewItem {

    private ChatHeaderModel mModel;

    public ChatHeaderViewItem(ChatHeaderModel model) {
        mModel = model;
    }

    public ChatHeaderModel getModel() {
        return mModel;
    }

    public void setModel(ChatHeaderModel model) {
        mModel = model;
    }

    @Override
    public int getViewType() {
        return ViewType.CHAT_HEADER;
    }
}
