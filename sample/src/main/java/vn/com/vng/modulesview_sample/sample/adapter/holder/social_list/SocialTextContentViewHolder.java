package vn.com.vng.modulesview_sample.sample.adapter.holder.social_list;

import android.view.View;
import android.widget.Toast;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.social_list.SocialTextContentViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialContentTextView;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialTextContentViewHolder extends BaseViewHolder<SocialTextContentViewItem> {
    SocialContentTextView mSocialContentTextView;

    public SocialTextContentViewHolder(SocialContentTextView itemView) {
        super(itemView);
        mSocialContentTextView = itemView;
        init();
    }

    private void init() {
        mSocialContentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Click content", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onBind(SocialTextContentViewItem item) {
        super.onBind(item);
        if (item != null)
            mSocialContentTextView.bindModel(item.getSocialModel());
    }
}
