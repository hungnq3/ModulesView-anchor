package vn.com.vng.modulesview_sample.sample.adapter.holder.demo;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.demo.DemoTitleViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTitleView;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoTitleViewHolder extends BaseViewHolder<DemoTitleViewItem> {
    DemoTitleView mDemoTitleView;
    public DemoTitleViewHolder(DemoTitleView itemView) {
        super(itemView);
        mDemoTitleView = itemView;
    }

    @Override
    public void onBind(DemoTitleViewItem item) {
        super.onBind(item);
        mDemoTitleView.setText(item != null ? item.getText() : null);
    }
}
