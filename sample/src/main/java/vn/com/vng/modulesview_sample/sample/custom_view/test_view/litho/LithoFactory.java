package vn.com.vng.modulesview_sample.sample.custom_view.test_view.litho;

import android.content.Context;
import android.view.View;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

/**
 * Created by HungNQ on 08/11/2017.
 */

public class LithoFactory {
    public  static View createLithoTestView(Context context){
        return LithoView.create(context, TestLithoView.create(new ComponentContext(context)).build());
    }
    public  static View createComplexLithoView(Context context){
        return LithoView.create(context, ComplexLithoView.create(new ComponentContext(context)).build());
    }
}
