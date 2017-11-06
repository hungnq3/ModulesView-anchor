package vn.com.vng.modulesview_sample.sample.adapter.holder.social_list;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.social_list.SocialImageContentViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialImageContentView;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialImageContentViewHolder extends BaseViewHolder<SocialImageContentViewItem> {
    SocialImageContentView mSocialImageContentView;

    public SocialImageContentViewHolder(SocialImageContentView itemView) {
        super(itemView);
        mSocialImageContentView = itemView;
        init();
    }

    private void init() {

    }


    @Override
    public void onBind(final SocialImageContentViewItem item) {
        super.onBind(item);
        if (item != null) {
            for (int i = 0, count = mSocialImageContentView.getImagesContentCount(); i < count; ++i) {
                mSocialImageContentView.loadImage(i, item.getSocialModel().getImages().get(i));
            }
        }
    }
}
