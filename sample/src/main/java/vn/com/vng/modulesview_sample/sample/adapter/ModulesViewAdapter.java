package vn.com.vng.modulesview_sample.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import vn.com.vng.modulesview_sample.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.chat_list.ChatHeaderViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.demo.DemoHeaderViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.demo.DemoTitleViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.friend_list.FriendViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.chat_list.GroupChatHeaderViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.social_list.SocialFooterViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.social_list.SocialHeaderViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.social_list.SocialImageContentViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.social_list.SocialTextContentViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.holder.TestViewHolder;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.demo.DemoImageDefaultViewItem;
import vn.com.vng.modulesview_sample.sample.custom_view.chat_view.ChatHeaderView;
import vn.com.vng.modulesview_sample.sample.custom_view.chat_view.GroupChatHeaderView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoHeaderView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoImageNormalView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoImageRoundedView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoImageScaleTypeView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutAlignParentView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutAlignView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutFenceView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutGravityView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutGuidelineView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutNormalView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoLayoutSideOfView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextAlignmentView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextColorView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextEllipselView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextNormalView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextSizeView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextStyleView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTextTypefaceView;
import vn.com.vng.modulesview_sample.sample.custom_view.demo_view.DemoTitleView;
import vn.com.vng.modulesview_sample.sample.custom_view.friend_list.FriendView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social1ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social2ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social3ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social4ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social5ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social6ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social7ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social8ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.Social9ImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialContentTextView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialFooterView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialHeaderView;
import vn.com.vng.modulesview_sample.sample.custom_view.social_view.SocialImageContentView;
import vn.com.vng.modulesview_sample.sample.custom_view.test_view.TestView;

/**
 * Created by HungNQ on 15/09/2017.
 */

public class ModulesViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<BaseViewItem> mItems;

    public ModulesViewAdapter(List<BaseViewItem> items) {
        mItems = items;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        switch (viewType){
            case ViewType.TEST:{
                holder = new TestViewHolder(new TestView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TITLE_VIEW:{
                holder = new DemoTitleViewHolder(new DemoTitleView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_HEADER_VIEW:{
                holder = new DemoHeaderViewHolder(new DemoHeaderView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TEXT_NORMAL:{
                holder = new BaseViewHolder(new DemoTextNormalView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_TEXT_ALIGNMENT:{
                holder = new BaseViewHolder(new DemoTextAlignmentView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TEXT_SIZE:{
                holder = new BaseViewHolder(new DemoTextSizeView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TEXT_ELLIPSIZE:{
                holder = new BaseViewHolder(new DemoTextEllipselView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TEXT_STYLE:{
                holder = new BaseViewHolder(new DemoTextStyleView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_TEXT_TYPEFACE:{
                holder = new BaseViewHolder(new DemoTextTypefaceView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_TEXT_COLOR:{
                holder = new BaseViewHolder(new DemoTextColorView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_IMAGE_NORMAL:{
                holder = new BaseViewHolder(new DemoImageNormalView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_IMAGE_SCALE_TYPE:{
                holder = new BaseViewHolder(new DemoImageScaleTypeView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_IMAGE_ROUND_CORNERS:{
                holder = new BaseViewHolder(new DemoImageRoundedView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_LAYOUT_NORMAL:{
                holder = new BaseViewHolder(new DemoLayoutNormalView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_LAYOUT_ALIGN_PARENT:{
                holder = new BaseViewHolder(new DemoLayoutAlignParentView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_LAYOUT_ALIGN:{
                holder = new BaseViewHolder(new DemoLayoutAlignView(parent.getContext()));
                break;
            }
            case ViewType.DEMO_LAYOUT_SIDE_OF:{
                holder = new BaseViewHolder(new DemoLayoutSideOfView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_LAYOUT_GUIDELINE:{
                holder = new BaseViewHolder(new DemoLayoutGuidelineView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_LAYOUT_FENCE:{
                holder = new BaseViewHolder(new DemoLayoutFenceView(parent.getContext()));
                break;
            }

            case ViewType.DEMO_LAYOUT_GRAVITY:{
                holder = new BaseViewHolder(new DemoLayoutGravityView(parent.getContext()));
                break;
            }


            case ViewType.SOCIAL_HEADER:{
                holder = new SocialHeaderViewHolder(new SocialHeaderView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_FOOTER:{
                holder = new SocialFooterViewHolder(new SocialFooterView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_TEXT_CONTENT:{
                holder = new SocialTextContentViewHolder(new SocialContentTextView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_0:{
                holder = new SocialImageContentViewHolder(new SocialImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_1:{
                holder = new SocialImageContentViewHolder(new Social1ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_2:{
                holder = new SocialImageContentViewHolder(new Social2ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_3:{
                holder = new SocialImageContentViewHolder(new Social3ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_4:{
                holder = new SocialImageContentViewHolder(new Social4ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_5:{
                holder = new SocialImageContentViewHolder(new Social5ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_6:{
                holder = new SocialImageContentViewHolder(new Social6ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_7:{
                holder = new SocialImageContentViewHolder(new Social7ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_8:{
                holder = new SocialImageContentViewHolder(new Social8ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.SOCIAL_IMAGES_CONTENT_9:{
                holder = new SocialImageContentViewHolder(new Social9ImageContentView(parent.getContext()));
                break;
            }
            case ViewType.CHAT_HEADER:{
                holder = new ChatHeaderViewHolder(new ChatHeaderView(parent.getContext()));
                break;
            }
            case ViewType.GROUP_CHAT_HEADER:{
                holder = new GroupChatHeaderViewHolder(new GroupChatHeaderView(parent.getContext()));
                break;
            }
            case ViewType.FRIEND_ITEM:{
                holder = new FriendViewHolder(new FriendView(parent.getContext()));
                break;
            }
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getViewType();
    }
}
