package vn.com.vng.modulesview_sample.sample.adapter.view_item.demo;

import vn.com.vng.modulesview_sample.sample.adapter.ViewType;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;

/**
 * Created by HungNQ on 06/11/2017.
 */

public class DemoHeaderViewItem extends BaseViewItem {

    private CharSequence mText;

    public DemoHeaderViewItem(CharSequence text) {
        mText = text;
    }

    public CharSequence getText() {
        return mText;
    }

    public void setText(CharSequence text) {
        mText = text;
    }

    @Override
    public int getViewType() {
        return ViewType.DEMO_HEADER_VIEW;
    }
}
