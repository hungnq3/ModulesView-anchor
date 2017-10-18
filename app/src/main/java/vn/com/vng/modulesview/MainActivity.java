package vn.com.vng.modulesview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import vn.com.vng.modulesview.sample.model.SocialModel;
import vn.com.vng.modulesview.sample.adapter.ModulesViewAdapter;
import vn.com.vng.modulesview.sample.adapter.view_item.BaseViewItem;
import vn.com.vng.modulesview.sample.adapter.view_item.SocialFooterViewItem;
import vn.com.vng.modulesview.sample.adapter.view_item.SocialHeaderViewItem;
import vn.com.vng.modulesview.sample.adapter.view_item.SocialImageContentViewItem;
import vn.com.vng.modulesview.sample.adapter.view_item.SocialTextContentViewItem;

public class MainActivity extends AppCompatActivity {

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

    String[] IMGS = new String[]{
            "https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg",
            "https://www.w3schools.com/css/img_fjords.jpg",
            "https://3.bp.blogspot.com/-W__wiaHUjwI/Vt3Grd8df0I/AAAAAAAAA78/7xqUNj8ujtY/s1600/image02.png",
            "https://cdn.athemes.com/wp-content/uploads/Original-JPG-Image.jpg",
            "https://i.sharefa.st/1295569823374302192636.jpg",
            "http://iforo.3djuegos.com/files_foros/89/894.jpg",
            "http://www.ikea.com/gb/en/images/products/pj%C3%A4tteryd-picture-silver-deer__0455534_pe603586_s4.jpg",
            "http://cdn1-www.dogtime.com/assets/uploads/gallery/bull-terier-dog-breed-pictures/6-siderun.jpg",
            "https://cdn.pixabay.com/photo/2016/10/27/16/58/full-moon-1775764_640.jpg",
            "http://southafrica.worldswimsuit.com/images/made/images/uploads/website_images/195/sports_12_ler_21_56239-v1.web_1360_907_c1.jpg"
    };


    private String getRandomImg(){
        return IMGS[new Random().nextInt(IMGS.length)];
    }

    private List<String> getRandomImgs(){
        List<String> imgs = new LinkedList<>();
        int size = new Random().nextInt(IMGS.length);
//        int size = 9;
        for(int i=0; i<size; ++i)
            imgs.add(getRandomImg());
        return imgs;
    }

    private List<BaseViewItem> buildItems() {

        List<BaseViewItem> items = new ArrayList<>(40);
        items.addAll(mockModel(getRandomImg(), "Thổ dân", "12:07 Hôm nay", "Hello \n... \n... \n... \nmotor", 1022, 6233, null));
        items.addAll(mockModel(getRandomImg(), "Cú vọ", "12:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem", 5, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú xám", "11:08 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 1, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cu", "10:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây!!!", 5, 12, null));
        items.addAll(mockModel(getRandomImg(), "Cú cụ", "9:00 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra hết đây mà xem đê ra hết đây mà xem đê!!!", 8, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú con", "08:07 Hôm qua", null, 60, 24, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cha", "08:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 23, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú vợ", "08:01 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây!!!", 2, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chồng", "07:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 123, 122, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chồng", "07:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 123, 122, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chắc", "06:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!",13,5, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chắc", "06:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!",21,0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chắc", "06:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!",22,2, getRandomImgs()));

        //double
        items.addAll(mockModel(getRandomImg(), "Cú vọ", "12:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem", 5, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú xám", "11:08 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 1, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cu", "10:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây!!!", 5, 12, null));
        items.addAll(mockModel(getRandomImg(), "Cú cụ", "9:00 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra hết đây mà xem đê ra hết đây mà xem đê!!!", 8, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú con", "08:07 Hôm qua", null, 60, 24, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cha", "08:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 23, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú vợ", "08:01 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây!!!", 2, 0, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chồng", "07:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 123, 122, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chồng", "07:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 123, 122, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú cháu", "07:02 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 15, 42, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chắc", "06:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 26, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú chắc", "06:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem, ra mà xem, ra mà xem, ra hết đây mà xem đê, ra mà xem, ra mà xem, ra hết đây mà xem đê!!!", 12, 26, getRandomImgs()));
        items.addAll(mockModel(getRandomImg(), "Cú vọ", "12:07 Hôm qua", "Cả cả cả mọi người trên thể giới ra đây mà xem", 5, 0, getRandomImgs()));

        return items;
    }

    private List<BaseViewItem> mockModel(String avatar, String name, String time, String content, int likeCount, int commentCount, List<String> imgUrls) {
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


}
