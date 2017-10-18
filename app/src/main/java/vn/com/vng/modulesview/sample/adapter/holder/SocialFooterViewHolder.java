package vn.com.vng.modulesview.sample.adapter.holder;

import android.widget.Toast;

import vn.com.vng.modulesview.modules_view.Module;
import vn.com.vng.modulesview.sample.adapter.view_item.SocialFooterViewItem;
import vn.com.vng.modulesview.sample.social_view.SocialFooterView;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialFooterViewHolder extends BaseViewHolder<SocialFooterViewItem> {
    SocialFooterView mSocialFooterView;
    public SocialFooterViewHolder(SocialFooterView itemView) {
        super(itemView);

        mSocialFooterView = itemView;
        init();
    }

    private void init() {
        mSocialFooterView.setOnLikeClickListener(new Module.OnClickListener() {
            @Override
            public void onClick(Module module) {
                Toast.makeText(module.getContext(), "Click like", Toast.LENGTH_SHORT).show();
            }
        });
        mSocialFooterView.setOnCommentClickListener(new Module.OnClickListener() {
            @Override
            public void onClick(Module module) {
                Toast.makeText(module.getContext(), "Click comment", Toast.LENGTH_SHORT).show();
            }
        });
        mSocialFooterView.setOnMoreClickListener(new Module.OnClickListener() {
            @Override
            public void onClick(Module module) {
                Toast.makeText(module.getContext(), "Click more", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBind(SocialFooterViewItem item) {
        super.onBind(item);
        if(item != null)
            mSocialFooterView.bindModel(item.getSocialModel());
    }
}
