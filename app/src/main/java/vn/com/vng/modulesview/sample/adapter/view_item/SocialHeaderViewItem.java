package vn.com.vng.modulesview.sample.adapter.view_item;

import vn.com.vng.modulesview.sample.adapter.ViewType;
import vn.com.vng.modulesview.sample.model.SocialModel;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class SocialHeaderViewItem extends BaseViewItem {

    private SocialModel mSocialModel;

    public SocialHeaderViewItem(SocialModel socialModel) {
        mSocialModel = socialModel;
    }


    @Override
    public int getViewType() {
        return ViewType.SOCIAL_HEADER;
    }

    public SocialModel getSocialModel() {
        return mSocialModel;
    }

    public void setSocialModel(SocialModel socialModel) {
        mSocialModel = socialModel;
    }
}
