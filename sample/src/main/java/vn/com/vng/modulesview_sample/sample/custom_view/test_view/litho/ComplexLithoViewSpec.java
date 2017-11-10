package vn.com.vng.modulesview_sample.sample.custom_view.test_view.litho;

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
import com.facebook.yoga.YogaEdge;

import vn.com.vng.modulesview_sample.R;

/**
 * Created by HungNQ on 08/11/2017.
 */

@LayoutSpec
public class ComplexLithoViewSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Column.create(c)
                .child(
                        Column.create(c)
                                .paddingDip(YogaEdge.ALL, 12)
                                .marginDip(YogaEdge.LEFT, 40)
                                .marginDip(YogaEdge.RIGHT, 40)
                                .marginDip(YogaEdge.TOP, 4)
                                .backgroundRes(R.drawable.bg_corners)
                                .child(Row.create(c)
                                        .child(Image.create(c)
                                                .scaleType(ImageView.ScaleType.CENTER_CROP)
                                                .drawableRes(R.drawable.img)
                                                .widthDip(60)
                                                .heightDip(60))
                                        .child(Column.create(c)
                                                .marginDip(YogaEdge.LEFT, 12)
                                                .child(Text.create(c)
                                                        .text("Name")
                                                        .textSizeSp(14f)
                                                        .textStyle(Typeface.BOLD)
                                                        .textColor(0xff000000)
                                                        .marginDip(YogaEdge.BOTTOM, 2)
                                                )
                                                .child(Text.create(c)
                                                        .text("Let's start this conversation with great stories!")
                                                        .text("Let'ss")
                                                        .textColor(0x8a000000)
                                                        .textSizeSp(12f)
                                                        .marginDip(YogaEdge.TOP, 2)
                                                )
                                        )
                                )
                                .child(Row.create(c)
                                        .marginDip(YogaEdge.TOP, 12)
                                        .child(Image.create(c)
                                                .widthDip(0)
                                                .flexGrow(1f)
                                                .aspectRatio(1f)
                                                .marginPx(YogaEdge.RIGHT, 1)
                                                .scaleType(ImageView.ScaleType.CENTER_CROP)
                                                .drawableRes(R.drawable.img7)
                                        )
                                        .child(Image.create(c)
                                                .scaleType(ImageView.ScaleType.CENTER_CROP)
                                                .widthDip(0)
                                                .flexGrow(1f)
                                                .aspectRatio(1f)
                                                .marginPx(YogaEdge.RIGHT, 1)
                                                .marginPx(YogaEdge.LEFT, 1)
                                                .drawableRes(R.drawable.img5)
                                        )
                                        .child(Image.create(c)
                                                .scaleType(ImageView.ScaleType.CENTER_CROP)
                                                .widthDip(0)
                                                .flexGrow(1f)
                                                .aspectRatio(1f)
                                                .marginPx(YogaEdge.LEFT, 1)
                                                .drawableRes(R.drawable.img6)
                                        )
                                )
                )
                .child(Text.create(c)
                        .paddingDip(YogaEdge.LEFT, 8)
                        .paddingDip(YogaEdge.TOP, 2)
                        .paddingDip(YogaEdge.RIGHT, 8)
                        .paddingDip(YogaEdge.BOTTOM, 2)
                        .text("9:08, 10/11/2017")
                        .textColor(0xffffffff)
                        .textSizeSp(10.5f)
                        .backgroundRes(R.drawable.chip_background)
                        .marginDip(YogaEdge.TOP, 8)
                        .alignSelf(YogaAlign.CENTER)
                )
                .build();

    }
}
