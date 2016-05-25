package cn.ld.ljc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cn.ld.ljc.R;

/**
 * Created by Shinelon on 2016/5/10.
 */
public class ProgressView extends View {

    private float width, height;
    private float centerX, centerY;
    private Paint mPaint;

    private boolean isStop = false;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void stop() {
        isStop = true;
    }

    public void start() {
        isStop = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        centerX = w / 2;
        centerY = h / 2;
    }

    private int degrees;

    @Override
    protected void onDraw(Canvas canvas) {
        degrees = (degrees + 3) % 90;
        float len = (float) (160 * (Math.abs(Math.sin(degrees * Math.PI / 180)) + Math.cos(degrees * Math.PI / 180)));
        canvas.save();
        canvas.rotate(degrees, centerX, centerY);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(centerX - len / 2, centerY - len / 2, centerX + len / 2, centerY + len / 2, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.basecolor));
        canvas.drawRect(centerX - len / 2, centerY - len / 2, centerX + len / 2, centerY + len / 2, mPaint);
        canvas.restore();
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(centerX - 80, centerY - 80, centerX + 80, centerY + 80, mPaint);
        if (!isStop)
            postInvalidateDelayed(40);
    }
}
