package cn.ld.ljc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import cn.ld.ljc.R;

/**
 * Created by Shinelon on 2016/5/10.
 */
public class ProgressSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private RenderThread renderThread;
    private boolean isDraw = false;//控制开关

    private float centerX, centerY;

    private Paint mPaint;

    public ProgressSurfaceView(Context context) {
        super(context);
        init();
    }

    public ProgressSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        renderThread = new RenderThread();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getResources().getColor(R.color.basecolor));
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDraw = true;
        renderThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDraw = false;
    }

    private class RenderThread extends Thread {
        @Override
        public void run() {
            while (isDraw) {
                try {
                    Thread.sleep(40);
                    drawUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

    public void drawUI() {
        Canvas canvas = holder.lockCanvas();
        try {
            drawCanvas(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private int degrees;

    private void drawCanvas(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        degrees = (degrees + 3) % 90;
        canvas.drawRect(centerX - 70, centerY - 70, centerX + 70, centerY + 70, mPaint);
        float len;
        len = (float) (140 * (Math.abs(Math.sin(degrees * Math.PI / 180)) + Math.cos(degrees * Math.PI / 180)));
        canvas.save();
        canvas.rotate(degrees, centerX, centerY);
        canvas.drawRect(centerX - len / 2, centerY - len / 2, centerX + len / 2, centerY + len / 2, mPaint);
        canvas.restore();
    }

}
