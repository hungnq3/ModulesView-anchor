package vn.com.vng.modulesview.widget.text_layout_builder;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Px;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by HungNQ on 30/10/2017.
 */

public class TextLayoutBuilder {


    /**
     * Measure mode constants similar to {@link android.view.View.MeasureSpec}
     *
     * @see #MEASURE_MODE_UNSPECIFIED
     * @see #MEASURE_MODE_EXACTLY
     * @see #MEASURE_MODE_AT_MOST
     */
    @Retention(SOURCE)
    @IntDef({MEASURE_MODE_UNSPECIFIED, MEASURE_MODE_EXACTLY, MEASURE_MODE_AT_MOST})
    public @interface MeasureMode {
    }

    public static final int MEASURE_MODE_UNSPECIFIED = 0;
    public static final int MEASURE_MODE_EXACTLY = 1;
    public static final int MEASURE_MODE_AT_MOST = 2;

    // Default maxLines.
    public static final int DEFAULT_MAX_LINES = Integer.MAX_VALUE;

    private static final int EMS = 1;
    private static final int PIXELS = 2;


    private int mWidth;
    private int mMinWidth = 0;
    private int mMinWidthMode = PIXELS;
    private int mMaxWidth = Integer.MAX_VALUE;
    private int mMaxWidthMode = PIXELS;
    @MeasureMode
    private int mMeasureMode;

    private CharSequence mText;
    float mSpacingMult = 1.0f;
    float mSpacingAdd = 0.0f;
    boolean mIncludePadding = true;
    private TextUtils.TruncateAt mEllipsize;
    private boolean mSingleLine = false;
    private int mMaxLines = DEFAULT_MAX_LINES;
    private Layout.Alignment mAlignment;
    private Typeface mTypeface;
    private int mTextStyle;
    private boolean mUnderLine;

    private float mShadowRadius, mShadowDx, mShadowDy;
    private int mShadowColor;


    private boolean mShouldSavedInstance = true;
    private Layout mSavedInstance;


    //Text Paint
    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);


    /**
     * Sets whether the text layout instance should be saved or not.
     *
     * @param shouldSavedInstance True to cache the text layout, false otherwise
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setShouldSavedInstance(boolean shouldSavedInstance) {
        mShouldSavedInstance = shouldSavedInstance;
        if (!mShouldSavedInstance)
            mSavedInstance = null;
        return this;
    }

    /**
     * Sets the intended width of the text layout.
     *
     * @param width The width of the text layout
     * @return This {@link TextLayoutBuilder} instance
     * @see #setWidth(int, int)
     */
    public TextLayoutBuilder setWidth(@Px int width) {
        return setWidth(
                width,
                width <= 0 ? MEASURE_MODE_UNSPECIFIED : MEASURE_MODE_EXACTLY);
    }


    /**
     * Sets the intended width of the text layout while respecting the measure mode.
     *
     * @param width       The width of the text layout
     * @param measureMode The mode with which to treat the given width
     * @return This {@link TextLayoutBuilder} instance
     * @see #setWidth(int)
     */
    public TextLayoutBuilder setWidth(@Px int width, @MeasureMode int measureMode) {
        if (mWidth != width || mMeasureMode != measureMode) {
            mWidth = width;
            mMeasureMode = measureMode;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the text for the layout.
     *
     * @param text The text for the layout
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setText(CharSequence text) {
        if (text == mText ||
                (text != null && mText != null && text.equals(mText))) {
            return this;
        }
        mText = text;
        mSavedInstance = null;
        return this;
    }

    /**
     * Sets the text size for the layout.
     *
     * @param size The text size in pixels
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTextSize(int size) {
        if (mTextPaint.getTextSize() != size) {
//            createNewPaintIfNeeded();
            mTextPaint.setTextSize(size);
            mSavedInstance = null;
        }
        return this;
    }

    /**
     * Sets whether the text layout should be in an underline or not.
     *
     * @param underLine whether the text layout should be in an underline or no
     * @return This {@link TextLayoutBuilder} instance
     */

    public TextLayoutBuilder setUnderLine(boolean underLine) {
        if (mUnderLine != underLine) {
            mUnderLine = underLine;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the text color for the layout.
     *
     * @param color The text color for the layout
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTextColor(@ColorInt int color) {
//        createNewPaintIfNeeded();
        if (getTextColor() != color) {
            mTextPaint.setColor(color);
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the text extra spacing for the layout.
     *
     * @param spacingExtra the extra space that is added to the height of each line
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTextSpacingExtra(float spacingExtra) {
        if (mSpacingAdd != spacingExtra) {
            mSpacingAdd = spacingExtra;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the line spacing multiplier for the layout.
     *
     * @param spacingMultiplier the value by which each line's height is multiplied
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTextSpacingMultiplier(float spacingMultiplier) {
        if (mSpacingMult != spacingMultiplier) {
            mSpacingMult = spacingMultiplier;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Set whether the text Layout includes extra top and bottom padding to make
     * room for accents that go above the normal ascent and descent.
     * <p>
     * The default is true.
     *
     * @param shouldInclude Whether to include font padding or not
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setIncludeFontPadding(boolean shouldInclude) {
        if (mIncludePadding != shouldInclude) {
            mIncludePadding = shouldInclude;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets text alignment for the layout.
     *
     * @param alignment The text alignment for the layout
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setAlignment(Layout.Alignment alignment) {
        if (mAlignment != alignment) {
            mAlignment = alignment;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the shadow layer for the layout.
     *
     * @param radius The radius of the blur for shadow
     * @param dx     The horizontal translation of the origin
     * @param dy     The vertical translation of the origin
     * @param color  The shadow color
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setShadowLayer(float radius, float dx, float dy, @ColorInt int color) {
//        createNewPaintIfNeeded();
        if (radius != mShadowRadius || dx != mShadowDx || dy != mShadowDy || color != mShadowColor) {
            mShadowRadius = radius;
            mShadowDx = dx;
            mShadowDy = dy;
            mShadowColor = color;
            mTextPaint.setShadowLayer(radius, dx, dy, color);
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets a text style for the layout.
     *
     * @param style The text style for the layout
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTextStyle(int style) {
        if (mTextStyle != style) {
            mTextStyle = style;
//            createNewPaintIfNeeded();
//            mTextPaint.setTypeface(typeface);
            mSavedInstance = null;
        }
        return this;
    }

    /**
     * Sets the typeface used by this TextLayoutBuilder.
     *
     * @param typeface The typeface for this TextLayoutBuilder
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setTypeface(Typeface typeface) {
        if (mTypeface != typeface) {
            mTypeface = typeface;
//            createNewPaintIfNeeded();
//            mTextPaint.setTypeface(typeface);
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the ellipsis location for the layout.
     *
     * @param ellipsize The ellipsis location in the layout
     * @return This {@link TextLayoutBuilder} instance
     */
    public TextLayoutBuilder setEllipsize(TextUtils.TruncateAt ellipsize) {
        if (mEllipsize != ellipsize) {
            mEllipsize = ellipsize;
            mSavedInstance = null;
        }
        return this;
    }

    /**
     * Sets whether the text should be in a single line or not.
     *
     * @param singleLine Whether the text should be in a single line or not
     * @return This {@link TextLayoutBuilder} instance
     * @see #setMaxLines(int)
     */
    public TextLayoutBuilder setSingleLine(boolean singleLine) {
        if (mSingleLine != singleLine) {
            mSingleLine = singleLine;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets a maximum number of lines to be shown by the Layout.
     * <p>
     * Note: Gingerbread always default to two lines max when ellipsized. This cannot be changed.
     * Use a TextView if you want more control over the number of lines.
     *
     * @param maxLines The number of maxLines to show in this Layout
     * @return This {@link TextLayoutBuilder} instance
     * @see #setSingleLine(boolean)
     */
    public TextLayoutBuilder setMaxLines(int maxLines) {
        if (mMaxLines != maxLines) {
            mMaxLines = maxLines;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the min width expressed in ems.
     *
     * @param minEms min width expressed in ems
     * @return This {@link TextLayoutBuilder} instance
     * @see #setMaxEms(int)
     * @see #setMinWidth(int)
     */
    public TextLayoutBuilder setMinEms(int minEms) {
        if (mMaxWidth != minEms || mMinWidthMode != EMS) {
            mMinWidth = minEms;
            mMinWidthMode = EMS;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the min width expressed in pixels.
     *
     * @param minWidth min width expressed in pixels.
     * @return This {@link TextLayoutBuilder} instance
     * @see #setMaxWidth(int)
     * @see #setMinEms(int)
     */
    public TextLayoutBuilder setMinWidth(@Px int minWidth) {
        if (mMinWidth != minWidth || mMinWidthMode != PIXELS) {
            mMinWidth = minWidth;
            mMinWidthMode = PIXELS;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the max width expressed in ems.
     *
     * @param maxEms max width expressed in ems
     * @return This {@link TextLayoutBuilder} instance
     * @see #setMaxWidth(int)
     * @see #setMinEms(int)
     */
    public TextLayoutBuilder setMaxEms(int maxEms) {
        if (mMaxWidth != maxEms || mMinWidthMode != EMS) {
            mMaxWidth = maxEms;
            mMaxWidthMode = EMS;
            mSavedInstance = null;
        }
        return this;
    }


    /**
     * Sets the max width expressed in pixels.
     *
     * @param maxWidth max width expressed in pixels
     * @return This {@link TextLayoutBuilder} instance
     * @see #setMaxEms(int)
     * @see #setMinWidth(int)
     */
    public TextLayoutBuilder setMaxWidth(@Px int maxWidth) {
        if (mMaxWidth != maxWidth || mMinWidthMode != PIXELS) {
            mMaxWidth = maxWidth;
            mMaxWidthMode = PIXELS;
            mSavedInstance = null;
        }
        return this;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getMinWidth() {
        return mMinWidth;
    }

    public int getMinWidthMode() {
        return mMinWidthMode;
    }

    public int getMaxWidth() {
        return mMaxWidth;
    }

    public int getMaxWidthMode() {
        return mMaxWidthMode;
    }

    public int getMeasureMode() {
        return mMeasureMode;
    }

    public CharSequence getText() {
        return mText;
    }

    public float getSpacingMult() {
        return mSpacingMult;
    }

    public float getSpacingAdd() {
        return mSpacingAdd;
    }

    public boolean isIncludePadding() {
        return mIncludePadding;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return mEllipsize;
    }

    public boolean isSingleLine() {
        return mSingleLine;
    }

    public int getMaxLines() {
        return mMaxLines;
    }

    public Layout.Alignment getAlignment() {
        return mAlignment;
    }

    public TextPaint getTextPaint() {
        return mTextPaint;
    }

    public float getTextSize() {
        return mTextPaint.getTextSize();
    }

    public int getTextColor() {
        return mTextPaint.getColor();
    }

    public Typeface getTypeFace() {
        return mTypeface;
    }

    public int getTextStyle() {
        return mTextStyle;
    }

    public boolean isUnderLine() {
        return mUnderLine;
    }


//--------------------------getter--------------------------------------------


    //-----------------------------------------------------------------------

    int getLineHeight() {
        return Math.round(mTextPaint.getFontMetricsInt(null) * mSpacingMult + mSpacingAdd);
    }


    public Layout build() {

        //return saved layout if still available
        if (mShouldSavedInstance && mSavedInstance != null) {
            return mSavedInstance;
        }

        //make sure text valid
        CharSequence text = mText != null ? mText : "";


        //config attributes


        if (mTypeface != null && mTextStyle != Typeface.NORMAL) {
            mTextPaint.setTypeface(Typeface.create(mTypeface, mTextStyle));
        } else if (mTextStyle != Typeface.NORMAL) {
            mTextPaint.setTypeface(Typeface.defaultFromStyle(mTextStyle));
        } else
            mTextPaint.setTypeface(mTypeface);

        if (mUnderLine)
            mTextPaint.setFlags(mTextPaint.getFlags() | TextPaint.UNDERLINE_TEXT_FLAG);
        else
            mTextPaint.setFlags(mTextPaint.getFlags() & (~TextPaint.UNDERLINE_TEXT_FLAG));


        int numLines = mSingleLine ? 1 : mMaxLines;

        int width = 0;
        switch (mMeasureMode) {
            case MEASURE_MODE_UNSPECIFIED:
                width = (int) Math.ceil(Layout.getDesiredWidth(mText, mTextPaint));
                break;
            case MEASURE_MODE_EXACTLY:
                width = mWidth;
                break;
            case MEASURE_MODE_AT_MOST:
                width = Math.min(
                        (int) Math.ceil(Layout.getDesiredWidth(mText, mTextPaint)),
                        mWidth);
                break;
        }

        final int lineHeight = getLineHeight();

        if (mMaxWidthMode == EMS) {
            width = Math.min(width, mMaxWidth * lineHeight);
        } else {
            width = Math.min(width, mMaxWidth);
        }

        if (mMinWidthMode == EMS) {
            width = Math.max(width, mMinWidth * lineHeight);
        } else {
            width = Math.max(width, mMinWidth);
        }

        Layout.Alignment alignment = mAlignment != null ? mAlignment : Layout.Alignment.ALIGN_NORMAL;


        Layout layout = StaticLayoutCreator.createStaticLayout(text, 0, text.length(), mTextPaint, width, alignment, mSpacingMult, mSpacingAdd, mIncludePadding, mEllipsize, width, numLines);

        if (mShouldSavedInstance)
            mSavedInstance = layout;
        return layout;
    }

}
