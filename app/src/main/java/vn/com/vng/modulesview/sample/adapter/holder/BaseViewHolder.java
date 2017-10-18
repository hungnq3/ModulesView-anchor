package vn.com.vng.modulesview.sample.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import vn.com.vng.modulesview.sample.adapter.view_item.BaseViewItem;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class BaseViewHolder<T extends BaseViewItem> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(T item){

    }
}
