package vn.com.vng.modulesview.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import vn.com.vng.modulesview.sample.adapter.holder.BaseViewHolder;
import vn.com.vng.modulesview.sample.adapter.holder.SocialFooterViewHolder;
import vn.com.vng.modulesview.sample.adapter.holder.SocialHeaderViewHolder;
import vn.com.vng.modulesview.sample.adapter.holder.SocialImageContentViewHolder;
import vn.com.vng.modulesview.sample.adapter.holder.SocialTextContentViewHolder;
import vn.com.vng.modulesview.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview.sample.social_view.Social1ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social2ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social3ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social4ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social5ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social6ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social7ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social8ImageContentView;
import vn.com.vng.modulesview.sample.social_view.Social9ImageContentView;
import vn.com.vng.modulesview.sample.social_view.SocialContentTextView;
import vn.com.vng.modulesview.sample.social_view.SocialFooterView;
import vn.com.vng.modulesview.sample.social_view.SocialHeaderView;
import vn.com.vng.modulesview.sample.social_view.SocialImageContentView;

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
