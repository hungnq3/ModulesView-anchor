package vn.com.vng.modulesview_sample;

import android.content.Context;


/**
 * Created by HungNQ on 12/09/2017.
 */

public class Application extends android.app.Application {
    public static Context self;
    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
//        try {
//            ImageLoader.getInstance().init(Util.getDiskCacheDir(this, "image_cache"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
