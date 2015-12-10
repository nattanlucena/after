package ballons.com.after.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

import java.io.InputStream;

/**
 * Created by nattan on 10/26/15.
 */
public class GradientOverImageDrawable extends BitmapDrawable {

    private int[] mGradientColors;
    private float[] mGradientPositions;
    private double mGradientStart = 0.55;
    private double mGradientEnd = 1;

    public GradientOverImageDrawable(Resources res, Bitmap bitmap){
        super(res, bitmap);
    }

    public GradientOverImageDrawable(Resources res, String filePath){
        super(res, filePath);
    }

    public GradientOverImageDrawable(Resources res, InputStream is){
        super(res, is);
    }

    public void setGradientColors(int[] gradientColors){
        mGradientColors = gradientColors;
    }

    public void setGradientColors(int startColor, int... endColors){
        if(endColors.length == 0){
            throw new IllegalArgumentException("The endColors array must have at least one element");
        }
        mGradientColors = new int[endColors.length + 1];
        mGradientColors[0] = startColor;

        System.arraycopy(endColors, 0, mGradientColors, 1, endColors.length);
    }

    public int[] getGradientColors(){
        return mGradientColors;
    }

    public float[] getGradientPositions(){
        return mGradientPositions;
    }

    public void setGradientPositions(float[] gradientPositions) {
        for (float pos : gradientPositions) {
            if (pos > 1 || pos < 0) {
                throw new IllegalArgumentException("The gradient position must be a float number between 0 and 1");
            }
        }
        this.mGradientPositions = gradientPositions;
    }

    public double getGradientStart() {
        return mGradientStart;
    }

    public void setGradientStart(double gradientStart) {
        this.mGradientStart = gradientStart;
    }

    public double getGradientEnd() {
        return mGradientEnd;
    }

    public void setGradientEnd(double gradientEnd) {
        this.mGradientEnd = gradientEnd;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mGradientColors != null) {
            Rect bounds = getBounds();

            int x0 = bounds.left;
            int y0 = (int) Math.round(bounds.bottom * mGradientStart);
            int x1 = x0;
            int y1 = (int) Math.round(bounds.bottom * mGradientEnd);

            LinearGradient shader = new LinearGradient(x0, y0, x1, y1, mGradientColors, mGradientPositions, Shader.TileMode.CLAMP);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(shader);
            canvas.drawRect(x0, y0, bounds.right, y1, paint);
        }
    }

}
