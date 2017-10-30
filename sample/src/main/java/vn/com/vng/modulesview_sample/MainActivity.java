package vn.com.vng.modulesview_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vn.com.vng.modulesview_sample.sample.adapter.view_item.ChatHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.FriendViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.GroupChatHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.TestViewItem;
import vn.com.vng.modulesview_sample.sample.model.ChatHeaderModel;
import vn.com.vng.modulesview_sample.sample.model.FriendModel;
import vn.com.vng.modulesview_sample.sample.model.GroupChatHeaderModel;
import vn.com.vng.modulesview_sample.sample.model.SocialModel;
import vn.com.vng.modulesview_sample.sample.adapter.ModulesViewAdapter;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.SocialFooterViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.SocialHeaderViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.SocialImageContentViewItem;
import vn.com.vng.modulesview_sample.sample.adapter.view_item.SocialTextContentViewItem;

public class MainActivity extends AppCompatActivity {

    static final String[] IMGS = new String[]{
            "https://www.w3schools.com/css/img_fjords.jpg",
            "https://3.bp.blogspot.com/-W__wiaHUjwI/Vt3Grd8df0I/AAAAAAAAA78/7xqUNj8ujtY/s1600/image02.png",
            "https://cdn.athemes.com/wp-content/uploads/Original-JPG-Image.jpg",
            "https://i.sharefa.st/1295569823374302192636.jpg",
            "http://iforo.3djuegos.com/files_foros/89/894.jpg",
            "http://cdn1-www.dogtime.com/assets/uploads/gallery/bull-terier-dog-breed-pictures/6-siderun.jpg",
            "https://cdn.pixabay.com/photo/2016/10/27/16/58/full-moon-1775764_640.jpg",
            "https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg",
            "http://southafrica.worldswimsuit.com/images/made/images/uploads/website_images/195/sports_12_ler_21_56239-v1.web_1360_907_c1.jpg"
    };

    static final String[] NAMES = new String[]{
            "Thổ dân",
            "Cú vọ",
            "Cú xám",
            "Cú cu",
            "Anh",
            "Em",
            "Cú chồng",
            "Cú con",
            "Cú cháu",
            "Cú chắc"
    };

    static final String[] MESSAGES = new String[]{
            "Hello",
            "Xin chào",
            "Bonjour",
//            "",
//            null,
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Hello \n... \n... \n... \nworld",
            "Cả cả cả mọi người trên thể giới ra đây!!!",
            "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!",
            "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!"
    };

    static final String[] CHAT_TIMES = new String[]{
            "12:04",
            "13:07",
            "1 hour",
            "3 hours",
            "2 days",
            "17/10",
            "15/6"
    };

    static private String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

    static  private String getRandomMsg() {
        return MESSAGES[new Random().nextInt(MESSAGES.length)];
    }


    static  private String getRandomChatTime() {
        return CHAT_TIMES[new Random().nextInt(CHAT_TIMES.length)];
    }



    static  private String getRandomImg() {
        return IMGS[new Random().nextInt(IMGS.length)];
    }

    static  private List<String> getRandomImgs() {
        List<String> imgs = new LinkedList<>();
        int size = new Random().nextInt(IMGS.length);
        for (int i = 0; i < size; ++i)
            imgs.add(getRandomImg());
        return imgs;
    }

    static  private List<String> getRandomImgs(int size) {
        List<String> imgs = new LinkedList<>();
        for (int i = 0; i < size; ++i)
            imgs.add(getRandomImg());
        return imgs;
    }





    RecyclerView recyclerView;
    ModulesViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        setupRecycler();
    }

    private void setupRecycler() {
        mAdapter = new ModulesViewAdapter(buildItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }


    private List<BaseViewItem> buildItems() {

        List<BaseViewItem> items = new ArrayList<>(40);

        items.add(new TestViewItem());

        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), null, true));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), getRandomMsg(), true));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), getRandomMsg(), true));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), "", true));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), null, false));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), null, false));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), null, false));
        items.add(mockFriendViewItem(getRandomImg(), getRandomName(), null, false));

        items.add(mockGroupChatHeaderModel(getRandomImgs(3), getRandomName(), getRandomMsg(), getRandomChatTime(), 3, true));
        items.add(mockGroupChatHeaderModel(getRandomImgs(4), getRandomName(), getRandomMsg(), getRandomChatTime(), 4, false));
        items.add(mockGroupChatHeaderModel(getRandomImgs(5), getRandomName(), getRandomMsg(), getRandomChatTime(), 0, false));
        items.add(mockGroupChatHeaderModel(getRandomImgs(10), getRandomName(), getRandomMsg(), getRandomChatTime(), 10, true));

        items.add(mockChatHeaderModel(getRandomImg(), getRandomName(), getRandomMsg(), getRandomChatTime(), 0, true));
        items.add(mockChatHeaderModel(getRandomImg(), getRandomName(), getRandomMsg(), getRandomChatTime(), 1, false));
        items.add(mockChatHeaderModel(getRandomImg(), getRandomName(), getRandomMsg(), getRandomChatTime(), 2, false));
        items.add(mockChatHeaderModel(getRandomImg(), getRandomName(), getRandomMsg(), getRandomChatTime(), 3, true));
        items.add(mockChatHeaderModel(getRandomImg(), getRandomName(), getRandomMsg(), getRandomChatTime(), 10, false));

        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "12:07 Hôm nay", getRandomMsg(), 1022, 6233, null));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "12:07 Hôm qua", getRandomMsg(), 5, 0, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "11:08 Hôm qua", getRandomMsg(), 12, 1, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "10:07 Hôm qua", getRandomMsg(), 5, 12, null));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "9:00 Hôm qua", getRandomMsg(), 8, 0, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "08:07 Hôm qua", getRandomMsg(), 60, 24, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:02 Hôm qua", getRandomMsg(), 15, 42, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:02 Hôm qua", getRandomMsg(), 15, 42, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "08:02 Hôm qua", getRandomMsg(), 12, 23, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "08:01 Hôm qua", getRandomMsg(), 2, 0, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:07 Hôm qua", getRandomMsg(), 123, 122, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:07 Hôm qua", getRandomMsg(), 123, 122, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:02 Hôm qua", getRandomMsg(), 15, 42, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "07:02 Hôm qua", getRandomMsg(), 15, 42, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "06:07 Hôm qua", getRandomMsg(), 13, 5, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "06:07 Hôm qua", getRandomMsg(), 21, 0, getRandomImgs()));
        items.addAll(mockSocialModel(getRandomImg(), getRandomName(), "06:07 Hôm qua", getRandomMsg(), 22, 2, getRandomImgs()));

        return items;
    }

    private List<BaseViewItem> mockSocialModel(String avatar, String name, String time, String content, int likeCount, int commentCount, List<String> imgUrls) {
        List<BaseViewItem> items = new LinkedList<>();

        SocialModel model = new SocialModel();
        model.setAvatar(avatar);
        model.setName(name);
        model.setTime(time);
        model.setContent(content);
        model.setImages(imgUrls);
        model.setLikeCount(likeCount);
        model.setCommentCount(commentCount);

        items.add(new SocialHeaderViewItem(model));
        if (!TextUtils.isEmpty(content))
            items.add(new SocialTextContentViewItem(model));
        if (imgUrls != null && imgUrls.size() > 0)
            items.add(new SocialImageContentViewItem(model));
        items.add(new SocialFooterViewItem(model));
        return items;
    }

    private GroupChatHeaderViewItem mockGroupChatHeaderModel(List<String> avatars, String name, String message, String time, int newCount, boolean notificationOff) {
        GroupChatHeaderModel model = new GroupChatHeaderModel(avatars,avatars.size(), name, message, time, newCount, notificationOff);
        return new GroupChatHeaderViewItem(model);
    }

    private ChatHeaderViewItem mockChatHeaderModel(String avatar, String name, String message, String time, int newCount, boolean notificationOff) {
        ChatHeaderModel model = new ChatHeaderModel(avatar, name, message, time, newCount, notificationOff);
        return new ChatHeaderViewItem(model);
    }



    private FriendViewItem mockFriendViewItem(String avatar, String name, String status, boolean online) {
        FriendModel model = new FriendModel(avatar, name, status, online);
        return new FriendViewItem(model);
    }




}