package vn.com.vng.modulesview_sample.sample.adapter.view_item.social_list;

import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.model.SocialModel;
import vn.com.vng.modulesview_sample.sample.adapter.ViewType;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialTextContentViewItem extends BaseViewItem {

    private SocialModel mSocialModel;

    public SocialTextContentViewItem(SocialModel socialModel) {
        mSocialModel = socialModel;
    }


    @Override
    public int getViewType() {
        return ViewType.SOCIAL_TEXT_CONTENT;
    }

    public SocialModel getSocialModel() {
        return mSocialModel;
    }

    public void setSocialModel(SocialModel socialModel) {
        mSocialModel = socialModel;
    }
}
