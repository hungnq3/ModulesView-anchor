package vn.com.vng.modulesview_sample.sample.adapter.view_item;

/**
 * Created by HungNQ on 08/11/2017.
 */

public class FlexViewTypeItem extends BaseViewItem {
    int mViewType ;

    public FlexViewTypeItem(int viewType) {
        mViewType = viewType;
    }

    @Override
    public int getViewType() {
        return mViewType;
    }
}
