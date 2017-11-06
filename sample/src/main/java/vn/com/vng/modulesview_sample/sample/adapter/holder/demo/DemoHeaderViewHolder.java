package vn.com.vng.modulesview_sample.sample.adapter.holder.demo;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.demo.DemoHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoHeaderView;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoHeaderViewHolder extends BaseViewHolder<DemoHeaderViewItem> {
    DemoHeaderView mDemoHeaderView;
    public DemoHeaderViewHolder(DemoHeaderView itemView) {
        super(itemView);
        mDemoHeaderView = itemView;
    }

    @Override
    public void onBind(DemoHeaderViewItem item) {
        super.onBind(item);
        mDemoHeaderView.setText(item != null ? item.getText() : null);
    }
}
