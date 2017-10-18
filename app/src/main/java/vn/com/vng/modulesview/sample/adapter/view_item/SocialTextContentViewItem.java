package vn.com.vng.modulesview.sample.adapter.view_item;

import vn.com.vng.modulesview.sample.model.SocialModel;
import vn.com.vng.modulesview.sample.adapter.ViewType;

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
