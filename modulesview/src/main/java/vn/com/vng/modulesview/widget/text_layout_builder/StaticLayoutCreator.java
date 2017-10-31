package vn.com.vng.modulesview.widget.text_layout_builder;

import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

import java.lang.reflect.Constructor;

/**
 * Created by HungNQ on 31/10/2017.
 */

public class StaticLayoutCreator {
    private static Constructor<StaticLayout> sConstructor;
    private static Object[] sConstructorArgs;
    private static Object sTextDirection;

    private static final String TEXT_DIR_CLASS = "android.text.TextDirectionHeuristic";
    private static final String TEXT_DIRS_CLASS = "android.text.TextDirectionHeuristics";
    private static final String TEXT_DIR_FIRSTSTRONG_LTR = "FIRSTSTRONG_LTR";
    private static boolean initialized = false;

    public static void init() {
        if (initialized) {
            return;
        }

        try {
            final Class<?> textDirClass;
            if (Build.VERSION.SDK_INT >= 18) {
                textDirClass = TextDirectionHeuristic.class;
                sTextDirection = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            } else {
                ClassLoader loader = TextLayoutBuilder.class.getClassLoader();
                textDirClass = loader.loadClass(TEXT_DIR_CLASS);
                Class<?> textDirsClass = loader.loadClass(TEXT_DIRS_CLASS);
                sTextDirection = textDirsClass.getField(TEXT_DIR_FIRSTSTRONG_LTR).get(textDirsClass);
            }

            final Class<?>[] signature = new Class[]{
                    CharSequence.class,
                    int.class,
                    int.class,
                    TextPaint.class,
                    int.class,
                    Layout.Alignment.class,
                    textDirClass,
                    float.class,
                    float.class,
                    boolean.class,
                    TextUtils.TruncateAt.class,
                    int.class,
                    int.class
            };

            sConstructor = StaticLayout.class.getDeclaredConstructor(signature);
            sConstructor.setAccessible(true);
            sConstructorArgs = new Object[signature.length];
            initialized = true;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static StaticLayout createStaticLayout(CharSequence source, int bufstart, int bufend, TextPaint paint, int outerWidth, Layout.Alignment align, float spacingMult, float spacingAdd, boolean includePad, TextUtils.TruncateAt ellipsize, int ellipsisWidth, int maxLines) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                return StaticLayout.Builder.obtain(source, bufstart, bufend, paint, outerWidth)
                        .setLineSpacing(spacingAdd, spacingMult)
                        .setAlignment(align)
                        .setIncludePad(includePad)
                        .setEllipsize(ellipsize)
                        .setEllipsizedWidth(ellipsisWidth)
                        .setMaxLines(maxLines)
                        .setBreakStrategy(Layout.BREAK_STRATEGY_HIGH_QUALITY)
                        .build();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= 14) {
            init();
            try {
                sConstructorArgs[0] = source;
                sConstructorArgs[1] = bufstart;
                sConstructorArgs[2] = bufend;
                sConstructorArgs[3] = paint;
                sConstructorArgs[4] = outerWidth;
                sConstructorArgs[5] = align;
                sConstructorArgs[6] = sTextDirection;
                sConstructorArgs[7] = spacingMult;
                sConstructorArgs[8] = spacingAdd;
                sConstructorArgs[9] = includePad;
                sConstructorArgs[10] = ellipsize;
                sConstructorArgs[11] = ellipsisWidth;
                sConstructorArgs[12] = maxLines;
                return sConstructor.newInstance(sConstructorArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if (maxLines == 1) {
                return new StaticLayout(source, bufstart, bufend, paint, outerWidth, align, spacingMult, spacingAdd, includePad, ellipsize, ellipsisWidth);
            } else {
                StaticLayout layout = new StaticLayout(source, paint, outerWidth, align, spacingMult, spacingAdd, includePad);
                if (layout.getLineCount() <= maxLines) {
                    return layout;
                } else {
                    int lastLineIndex = maxLines - 1;
                    int off;
                    float left = layout.getLineLeft(lastLineIndex);
                    if (left != 0) {
                        off = layout.getOffsetForHorizontal(lastLineIndex, left);
                    } else {
                        off = layout.getOffsetForHorizontal(lastLineIndex, layout.getLineWidth(lastLineIndex));
                    }
                    SpannableStringBuilder stringBuilder = new SpannableStringBuilder(source.subSequence(0, Math.max(0, off - 1)));
                    stringBuilder.append("\u2026");
                    return new StaticLayout(stringBuilder, paint, outerWidth, align, spacingMult, spacingAdd, includePad);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
