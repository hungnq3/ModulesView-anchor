package vn.com.vng.modulesview.modules_view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;

import vn.com.vng.modulesview.modules_view.Module;

/**
 * Created by HungNQ on 08/09/2017.
 */

public class TextModule extends Module {

    public static final int DEFAULT_TEXT_COLOR = 0x8a000000;
    public static final int DEFAULT_TEXT_SIZE_IN_SP = 14;

    //stuff
    private TextLayoutBuilder mTextLayoutBuilder;
    private Layout mTextLayout;
    private RectF mClipRect = new RectF();

    //properties
    private CharSequence mText;
    private int mTextSize;
    private int mTextColor;
    private Layout.Alignment mAlignment;
    private int mMaxLines;
    private boolean mSingleLine;
    private TextUtils.TruncateAt mEllipsize;
    private Typeface mTypeFace;

    private boolean mNeedToRebuildTextLayout;

    public TextModule(Context context) {
        super(context);
        init();
    }

    private void init() {

        //init default properties
        buildDefaultProperties();

        //build LayoutBuilder with default properties
        mTextLayoutBuilder = new TextLayoutBuilder()
                .setShouldCacheLayout(false)
                .setText(mText)
                .setTextColor(mTextColor)
                .setAlignment(mAlignment)
                .setEllipsize(mEllipsize)
                .setMaxLines(mMaxLines)
                .setSingleLine(mSingleLine)
                .setTextSize(mTextSize)
                .setTypeface(mTypeFace);
    }

    private void buildDefaultProperties() {
        mText = "";
        mTextColor = DEFAULT_TEXT_COLOR;
        mMaxLines = Integer.MAX_VALUE;
        mSingleLine = false;
        mAlignment = Layout.Alignment.ALIGN_NORMAL;
        mEllipsize = null;
    }

    //----------------------Properties' getter & setter region---------------
    public CharSequence getText() {
        return mText;
    }

    public void setText(CharSequence text) {
        mText = makeSureTextValid(text);
        mTextLayoutBuilder.setText(mText);
    }

    private CharSequence makeSureTextValid(CharSequence text) {
        return text == null ? "" : text;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        mTextLayoutBuilder.setTextSize(mTextSize);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        mTextLayoutBuilder.setTextColor(mTextColor);
    }

    public int getMaxLines() {
        return mMaxLines;
    }

    public void setMaxLines(int maxLines) {
        mMaxLines = maxLines;
        mTextLayoutBuilder.setMaxLines(mMaxLines);
    }

    public boolean isSingleLine() {
        return mSingleLine;
    }

    public void setSingleLine(boolean singleLine) {
        mSingleLine = singleLine;
        mTextLayoutBuilder.setSingleLine(mSingleLine);
    }

    public Layout.Alignment getAlignment() {
        return mAlignment;
    }

    public void setAlignment(Layout.Alignment alignment) {
        mAlignment = alignment;
        mTextLayoutBuilder.setAlignment(mAlignment);
    }

    public TextUtils.TruncateAt getEllipsize() {
        return mEllipsize;
    }

    public void setEllipsize(TextUtils.TruncateAt ellipsize) {
        mEllipsize = ellipsize;
        mTextLayoutBuilder.setEllipsize(mEllipsize);
    }

    public Typeface getTypeFace() {
        return mTypeFace;
    }

    public void setTypeFace(Typeface typeFace) {
        mTypeFace = typeFace;
        mTextLayoutBuilder.setTypeface(mTypeFace);
    }

    public Layout getTextLayout() {
        return mTextLayout;
    }

    //-----------------endregion--------------------------------------------


    @Override
    public void onMeasureContent(int width, int widthMode, int height, int heightMode) {
//        super.onMeasureContent(width, widthMode, height, heightMode);
        int maxWidth = width <= 0 ? Integer.MAX_VALUE : width;
        int textWidth = widthMode == Module.DIMENSION_MODE_EXACTLY ? width : 0;
        mTextLayout = buildTextLayout(textWidth, maxWidth);
        textWidth = mTextLayout.getWidth();
        int textHeight = mTextLayout.getHeight();
        setContentDimensions(textWidth, textHeight);
    }


    /**
     * build a {@link Layout} based on text width and element's properties set
     *
     * @return {@link Layout}
     */
    private Layout buildTextLayout(int with, int maxWidth) {
        if (with > 0)
            mTextLayoutBuilder.setWidth(with);
        else
            mTextLayoutBuilder.setWidth(0, TextLayoutBuilder.MEASURE_MODE_UNSPECIFIED);
        mTextLayoutBuilder.setMaxWidth(maxWidth);
        return buildTextLayout();
    }

    /**
     * build a {@link Layout} based on text width and element's properties set
     *
     * @return {@link Layout}
     */
    private Layout buildTextLayout() {
        //default text size
        if (mTextSize == 0 && mContext != null) {
            mTextSize = (int) (DEFAULT_TEXT_SIZE_IN_SP * mContext.getResources().getDisplayMetrics().scaledDensity);
            mTextLayoutBuilder.setTextSize(mTextSize);
        }

        Layout layout = mTextLayoutBuilder.build();

        //fix layout null when text empty
        if (layout == null) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(mTextSize);
            layout = new StaticLayout("", textPaint, 0, mAlignment, mTextLayoutBuilder.getTextSpacingMultiplier(), mTextLayoutBuilder.getTextSpacingExtra(), false);
        }
        return layout;
    }

    @Override
    public void configModule() {
        super.configModule();
        if (mTextLayout == null)
            mTextLayout = buildTextLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0)
            return;

        canvas.save();

        //clip drawing region
        int left = getCoordinateX() + getLayoutParams().getPaddingLeft() + mDeltaContentCoordinateX;
        int top = getCoordinateY() + getLayoutParams().getPaddingTop() + mDeltaContentCoordinateY;
        canvas.translate(left, top);

        canvas.clipRect(0, 0, getWidth() - getLayoutParams().getPaddingLeft() - getLayoutParams().getPaddingRight(), getHeight() - getLayoutParams().getPaddingTop() - getLayoutParams().getPaddingBottom());

        if (mTextLayout != null)
            mTextLayout.draw(canvas);

        canvas.restore();
    }

}
