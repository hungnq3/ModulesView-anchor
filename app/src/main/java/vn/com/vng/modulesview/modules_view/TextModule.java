package vn.com.vng.modulesview.modules_view;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;

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


    public TextModule() {
        init();
    }

    private void init() {

        //init default properties
        buildDefaultProperties();

        //build LayoutBuilder with default properties
        mTextLayoutBuilder = new TextLayoutBuilder()
                .setShouldCacheLayout(true)
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
    public void internalMeasure(int width, int widthMode, int height, int heightMode) {
        super.internalMeasure(width, widthMode, height, heightMode);

        int maxWidth = width - getModuleParams().getPaddingLeft() - getModuleParams().getPaddingRight();
        if(maxWidth <0)
            maxWidth = 0;

        int maxHeight = height - getModuleParams().getPaddingTop() - getModuleParams().getPaddingBottom();
        if(maxHeight <0)
            maxHeight = 0;

        int textWidth = (widthMode == DIMENSION_MODE_FIXED) ? maxWidth : 0;

        //build a layout to calculate text width and height
        mTextLayout = buildTextLayout(textWidth, maxWidth);
        int vWidth = mTextLayout.getWidth() + getModuleParams().getPaddingLeft() + getModuleParams().getPaddingRight();
        int vHeight;
        if (heightMode == DIMENSION_MODE_MAX)
            vHeight = Math.min(mTextLayout.getHeight() +getModuleParams().getPaddingTop() + getModuleParams().getPaddingBottom() , height);
        else if (heightMode == DIMENSION_MODE_FIXED)
            vHeight = height;
        else
            vHeight = mTextLayout.getHeight() +getModuleParams().getPaddingTop() + getModuleParams().getPaddingBottom();

        updateMeasureDimension(vWidth, vHeight);
    }


    /**
     * build a {@link Layout} based on text width and element's properties set
     *
     * @return {@link Layout}
     */
    private Layout buildTextLayout(int width, int maxWidth) {
        //default text size
        if (mTextSize == 0 && mContext != null) {
            mTextSize = (int) (DEFAULT_TEXT_SIZE_IN_SP * mContext.getResources().getDisplayMetrics().scaledDensity);
            mTextLayoutBuilder.setTextSize(mTextSize);
        }
        if (width > 0)
            mTextLayoutBuilder.setWidth(width);
        else
            mTextLayoutBuilder.setWidth(0, TextLayoutBuilder.MEASURE_MODE_UNSPECIFIED);

        if (maxWidth > 0)
            mTextLayoutBuilder.setMaxWidth(maxWidth);

        Layout layout = mTextLayoutBuilder.build();

        //fix layout null when text empty
        if (layout == null) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(mTextSize);
            layout = new StaticLayout("", textPaint, width, mAlignment, mTextLayoutBuilder.getTextSpacingMultiplier(), mTextLayoutBuilder.getTextSpacingExtra(), false);
        }
        return layout;
    }

    @Override
    public void configModule() {
        super.configModule();
        if (getWidth() > 0)
            mTextLayout = buildTextLayout(getWidth(), 0);
        configClipBounds();
    }


    private void configClipBounds() {

        int contentWidth = getWidth() - getModuleParams().getPaddingLeft() - getModuleParams().getPaddingRight();
        int contentHeight = getHeight() - getModuleParams().getPaddingTop() - getModuleParams().getPaddingBottom();
        if (contentWidth <= 0 || contentHeight <= 0)
            return;
        mClipRect.set(0, 0, contentWidth, contentHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0)
            return;

        canvas.save();
        //translate if needed
        int translateLeft = getLeft() + getModuleParams().mPaddingLeft;
        int translateTop = getTop() + getModuleParams().mPaddingTop;
        canvas.translate(translateLeft, translateTop);

        //clip drawing region
        canvas.clipRect(mClipRect);

        if (mTextLayout != null)
            mTextLayout.draw(canvas);

        canvas.restore();
    }


}
