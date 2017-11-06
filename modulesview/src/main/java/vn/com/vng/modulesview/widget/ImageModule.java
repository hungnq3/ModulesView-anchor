package vn.com.vng.modulesview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import vn.com.vng.modulesview.Module;


/**
 * Created by HungNQ on 08/09/2017.
 */

public class ImageModule extends Module {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;

    public static final int ROUND_CIRCLE = -1;
    public static final int ROUND_NONE = 0;

    //Options for scaling the bounds of an image to the bounds of this module.
    public static final int FIT_CENTER = 0;
    public static final int FIT_XY = 1;
    public static final int CENTER = 2;
    public static final int CENTER_CROP = 3;
    public static final int CENTER_INSIDE = 4;

    public ImageModule(Context context) {
        super(context);
    }

    @IntDef({FIT_CENTER, FIT_XY, CENTER, CENTER_CROP, CENTER_INSIDE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScaleType {
    }


    //----------stuff-------------------------
    private Matrix mDrawMatrix;
    private Matrix mMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private BitmapShader mBitmapShader;

    //draw region
    private int mDrawWidth, mDrawHeight;
    private int mDrawTranslateX, mDrawTranslateY;
    private final RectF mCLipRect = new RectF();
    private final Path mClipPath = new Path();

    //properties
    private Bitmap mBitmap;
    private Drawable mDrawable;
    @ScaleType
    private int mScaleType;
    private float mRoundCorner;
    private boolean mAdjustViewBound;


    //-------------getter & setter----------------------


    @ScaleType
    public int getScaleType() {
        return mScaleType;
    }

    public void setScaleType(@ScaleType int scaleType) {
        if (mScaleType != scaleType) {
            mScaleType = scaleType;
        }
    }

    public float getRoundCorner() {
        return mRoundCorner;
    }

    public void setRoundCorner(float roundCorner) {
        if (mRoundCorner != roundCorner) {
            mRoundCorner = roundCorner;
        }
    }

    public void setImageDrawable(Drawable drawable) {
        mDrawable = drawable;
        mBitmap = null;
//        setImageBitmap(getBitmapFromDrawable(drawable));
    }

    public void setImageResource(int id) {
        setImageDrawable(ContextCompat.getDrawable(getContext(), id));
    }

    public void setImageBitmap(Bitmap bitmap) {
        mDrawable = null;
        mBitmap = bitmap;
        if (mBitmap != null) {
            mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mBitmapPaint.setShader(mBitmapShader);
        } else {
            mBitmapShader = null;
            mBitmapPaint.setShader(null);
        }
    }



    public boolean isAdjustViewBound() {
        return mAdjustViewBound;
    }

    public void setAdjustViewBound(boolean adjustViewBound) {
        mAdjustViewBound = adjustViewBound;
    }

    //-------------endregion------------------

    @Override
    public void onMeasureContent(int width, int widthMode, int height, int heightMode) {
        if (isAdjustViewBound() && (mBitmap != null || mDrawable != null)) {
            if (widthMode != DIMENSION_MODE_EXACTLY && heightMode != DIMENSION_MODE_EXACTLY) {
                int imageWidth = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicWidth();
                int imageHeight = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicHeight();
                int contentWidth = widthMode == DIMENSION_MODE_AT_MOST ? Math.min(width, imageWidth) : imageWidth;
                int contentHeight = heightMode == DIMENSION_MODE_AT_MOST ? Math.min(height, imageHeight) : imageHeight;

                setContentDimensions(contentWidth, contentHeight);
            } else if (widthMode == DIMENSION_MODE_EXACTLY && heightMode != DIMENSION_MODE_EXACTLY) {
                int imageWidth = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicWidth();
                int imageHeight = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicHeight();

                int contentWidth = width;
                int contentHeight;

                if (mScaleType == CENTER) //  Image bitmap no scale for this type
                    contentHeight = imageHeight;
                else if (mScaleType == CENTER_INSIDE && imageWidth < width) //  Image bitmap no scale when bitmap size < view size
                    contentHeight = imageHeight;
                else
                    contentHeight = imageWidth == 0 ? 0 : (int) ((imageHeight / (float) imageWidth) * contentWidth);

                if (heightMode == DIMENSION_MODE_AT_MOST)
                    contentHeight = Math.min(height, contentHeight);

                setContentDimensions(contentWidth, contentHeight);


            } else if (widthMode != DIMENSION_MODE_EXACTLY && heightMode == DIMENSION_MODE_EXACTLY) {
                int imageWidth = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicWidth();
                int imageHeight = mBitmap != null ? mBitmap.getWidth() : mDrawable.getIntrinsicHeight();

                int contentWidth;
                int contentHeight = height;

                if (mScaleType == CENTER) //  Image bitmap no scale for this type
                    contentWidth = imageWidth;
                else if (mScaleType == CENTER_INSIDE && imageHeight < height)
                    contentWidth = imageWidth;
                else {
                    contentWidth = imageHeight == 0 ? 0 : (int) ((imageWidth / (float) imageHeight) * contentHeight);
                }

                if (widthMode == DIMENSION_MODE_AT_MOST)
                    contentWidth = Math.min(contentWidth, width);
                setContentDimensions(contentWidth, contentHeight);
            } else
                super.onMeasureContent(width, widthMode, height, heightMode);
        } else
            super.onMeasureContent(width, widthMode, height, heightMode);

    }


    @Override
    public void configModule() {
        super.configModule();
        if (getWidth() <= 0 || getHeight() <= 0)
            return;

        if (mDrawable != null)
            if (mScaleType == CENTER_CROP)
                setImageBitmap(getBitmapFromDrawable(mDrawable, getContentWidth(), getContentHeight(), false));
            else
                setImageBitmap(getBitmapFromDrawable(mDrawable, getContentWidth(), getContentHeight(), true));

        configureImageBounds();
        configureDrawRegionPath();
        configureBitmapPaint();
    }


    //copy a part of ImageView.configureBounds(()
    private void configureImageBounds() {
        if (mBitmap == null) {
            return;
        }

        final int iWidth = mBitmap.getWidth();
        final int iHeight = mBitmap.getHeight();

        final int vWidth = getContentWidth();
        final int vHeight = getContentHeight();

        if (iWidth <= 0 || iHeight <= 0 || vWidth <= 0 || vHeight <= 0)
            return;

        mDrawTranslateX = 0;
        mDrawTranslateY = 0;

        final boolean fits = vWidth == iWidth && vHeight == iHeight;

        if (FIT_XY == mScaleType) {
            /* If the drawable has no intrinsic size, or we're told to
                scale to fit, then we just fill our entire view.
            */
            mDrawMatrix = mMatrix;
            float scaleX = vWidth / (float) iWidth;
            float scaleY = vHeight / (float) iHeight;
            mDrawMatrix.setScale(scaleX, scaleY);

            //determine draw region
            mDrawWidth = vWidth;
            mDrawHeight = vHeight;


        } else {
            // We need to do the scaling ourself, so have the drawable
            // use its native size.
            if (fits) {
                // The bitmap fits exactly, no transform needed.
                mDrawMatrix = null;

                //determine draw region
                mDrawWidth = vWidth;
                mDrawHeight = vHeight;
            } else if (mScaleType == CENTER) {
                // Center bitmap in view, no scaling.
                mDrawMatrix = mMatrix;
//                mDrawMatrix.setTranslate(Math.round((vWidth - iWidth) * 0.5f),
//                        Math.round((vHeight - iHeight) * 0.5f));

                //determine draw region
                mDrawWidth = Math.min(vWidth, iWidth);
                mDrawHeight = Math.min(vHeight, iHeight);

                mDrawTranslateX = (int) ((vWidth - mDrawWidth) * 0.5f);
                mDrawTranslateY = (int) ((vHeight - mDrawHeight) * 0.5f);

            } else if (mScaleType == CENTER_CROP) {
                mDrawMatrix = mMatrix;

                float scale;
                float dx = 0, dy = 0;

                if (iWidth * vHeight > vWidth * iHeight) {
                    scale = (float) vHeight / (float) iHeight;
                    dx = (vWidth - iWidth * scale) * 0.5f;
                } else {
                    scale = (float) vWidth / (float) iWidth;
                    dy = (vHeight - iHeight * scale) * 0.5f;
                }

                mDrawMatrix.setScale(scale, scale);
                mDrawMatrix.postTranslate(Math.round(dx), Math.round(dy));

                //determine draw region
                mDrawWidth = vWidth;
                mDrawHeight = vHeight;
            } else if (mScaleType == CENTER_INSIDE) {
                mDrawMatrix = mMatrix;
                float scale;
                float dx;
                float dy;

                if (iWidth <= vWidth && iHeight <= vHeight) {
                    scale = 1.0f;
                } else {
                    scale = Math.min((float) vWidth / (float) iWidth,
                            (float) vHeight / (float) iHeight);
                }

                dx = Math.round((vWidth - iWidth * scale) * 0.5f);
                dy = Math.round((vHeight - iHeight * scale) * 0.5f);

                mDrawMatrix.setScale(scale, scale);
//                mDrawMatrix.postTranslate(dx, dy);

                //determine draw region
                mDrawWidth = (int) (iWidth * scale);
                mDrawHeight = (int) (iHeight * scale);
                mDrawTranslateX = (int) dx;
                mDrawTranslateY = (int) dy;
            } else { //FIT_CENTER
                // Generate the required transform.
                float scale = Math.min((float) vWidth / (float) iWidth,
                        (float) vHeight / (float) iHeight);
                int dx = Math.round((vWidth - iWidth * scale) * 0.5f);
                int dy = Math.round((vHeight - iHeight * scale) * 0.5f);

                mDrawMatrix = mMatrix;
                mDrawMatrix.setScale(scale, scale);
//                mDrawMatrix.postTranslate(dx, dy);

                //determine draw region
                mDrawWidth = (int) (iWidth * scale);
                mDrawHeight = (int) (iHeight * scale);
                mDrawTranslateX = dx;
                mDrawTranslateY = dy;
            }
        }
    }


    private void configureDrawRegionPath() {
        if (mBitmap != null) {
            mClipPath.reset();
            if (mRoundCorner == ROUND_CIRCLE) {
                float halfWidth = mDrawWidth / 2f;
                float halfHeight = mDrawHeight / 2f;
                float radius = Math.min(halfWidth, halfHeight);
                mClipPath.addCircle(halfWidth, halfHeight, radius, Path.Direction.CW);
            } else if (mRoundCorner > 0) {
                mCLipRect.set(0, 0, mDrawWidth, mDrawHeight);
                mClipPath.addRoundRect(mCLipRect, mRoundCorner, mRoundCorner, Path.Direction.CW);
            } else {
                mCLipRect.set(0, 0, mDrawWidth, mDrawHeight);
//                mClipPath.addRect(mCLipRect, Path.Direction.CW);
            }

        }
    }


    private void configureBitmapPaint() {
        if (mBitmap != null) {
            if (mDrawMatrix != null)
                mBitmapShader.setLocalMatrix(mDrawMatrix);
            mBitmapPaint.setAntiAlias(needToAntialias());
        }
    }

    private boolean needToAntialias() {
        return mRoundCorner != ROUND_NONE;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null)
            return;

        if (mBitmap.getHeight() == 0 || mBitmap.getHeight() == 0)
            return;

        if (getContentWidth() <= 0 || getContentHeight() <= 0)
            return;

        canvas.save();

//        translate if needed
//        int translateLeft = getCoordinateX() + getLayoutParams().getPaddingLeft() + dX + mDrawTranslateX;
//        int translateTop = getCoordinateY() + getLayoutParams().getPaddingTop() + dY + mDrawTranslateY;
        int translateLeft = getLeft() + getLayoutParams().getPaddingLeft() + mContentLeft + dX + mDrawTranslateX;
        int translateTop = getTop() + getLayoutParams().getPaddingTop() + mContentTop + dY + mDrawTranslateY;
        canvas.translate(translateLeft, translateTop);

        if (!mClipPath.isEmpty())
            canvas.drawPath(mClipPath, mBitmapPaint);
        else
            canvas.drawRect(mCLipRect, mBitmapPaint);

        canvas.restore();
    }


    private boolean needAntiAlias() {
        return mRoundCorner > 0 || mRoundCorner == ROUND_CIRCLE;
    }


    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable, int width, int height, boolean getInside) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable || drawable.getIntrinsicHeight() == 0 || drawable.getIntrinsicWidth() == 0) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                int bWidth, bHeight;

                int dWidth = drawable.getIntrinsicWidth();
                int dHeight = drawable.getIntrinsicHeight();

                boolean k = dWidth / (float) dHeight >= width / (float) height;
                if ((getInside && k) || (!getInside && !k)) {
                    bWidth = width;
                    bHeight = (int) (bWidth * dHeight / (float) dWidth);
                } else {
                    bHeight = height;
                    bWidth = (int) (bHeight * dWidth / (float) dHeight);
                }
                bitmap = Bitmap.createBitmap(bWidth, bHeight, BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void loadImage(final String url) {
        loadImage(url, 0, 0);
    }

    public void loadImage(final String url, int placeHolderResId, int errorResId) {
        if (getContext() == null)
            return;

        final RequestCreator request = Picasso.with(getContext())
                .load(url)
                .priority(Picasso.Priority.HIGH);

        if (errorResId != 0)
            request.error(errorResId);

        if (placeHolderResId != 0)
            request.placeholder(placeHolderResId);

        if ((getWidth() > 0 && getHeight() > 0)) {
            request.resize(getWidth(), getHeight())
                    .onlyScaleDown();

            switch (mScaleType) {
                case CENTER_INSIDE:
                    request.centerInside();
                    break;
                case CENTER:
                case CENTER_CROP:
                    request.centerCrop();
                    break;
                case FIT_XY:
                case FIT_CENTER:
                    request.fit();
                    break;
            }
        }
        request.into(getLoaderTarget());
    }

    Target mLoaderTarget;

    Target getLoaderTarget() {
        if (mLoaderTarget == null)
            mLoaderTarget = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    setImageBitmap(bitmap);
                    configModule();
                    invalidate();
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    setImageDrawable(errorDrawable);
                    configModule();
                    invalidate();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    setImageDrawable(placeHolderDrawable);
                    configModule();
                    invalidate();
                }
            };
        return mLoaderTarget;
    }
}
