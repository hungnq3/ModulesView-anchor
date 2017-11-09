package vn.com.vng.modulesview_sample.sample.custom_view.test_view.litho;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDimension;
import com.facebook.yoga.YogaEdge;

import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 08/11/2017.
 */

@LayoutSpec
public class TestLithoViewSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Row.create(c)
                .paddingDip(YogaEdge.ALL, 8)
                .heightDip(76)
                .child(Image.create(c)
                        .scaleType(ImageView.ScaleType.CENTER_CROP)
                        .widthDip(60)
                        .heightDip(60)
                        .drawableRes(R.drawable.img))
                .child(Column.create(c)
                        .alignSelf(YogaAlign.CENTER)
                        .marginDip(YogaEdge.LEFT, 8)
                        .child(Text.create(c)
                                .text("Primary text")
                                .textColor(0xff050505)
                                .textStyle(Typeface.BOLD)
                                .textSizeSp(14))
                        .child(Text.create(c)
                                .text("Secondary text")
                                .textColor(0x8a000000)
                                .textSizeSp(12)))
                .build();

    }
}
