package cn.ld.ljc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import cn.ld.ljc.R;

/**
 * Created by liudong on 2016/5/24.
 */
public class LettersNavigationView extends View {

    private OnTouchingLetterChangedListener mOnTouchingLetterChangedListener;

    private static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private int choose = -1;

    private Paint paint = new Paint();

    private TextView mDialogText;


    public LettersNavigationView(Context context) {
        super(context);
    }

    public LettersNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDialogTv(TextView dialogTxt) {
        mDialogText = dialogTxt;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.rgb(33, 65, 98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(20);
            //选中状态
            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);//设置是否为粗体
            }
            //x坐标 = 中间 - 字符串宽度的一半
            float xPos = width / 2f - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();//点击y坐标
        final int oldChoose = choose;

        final OnTouchingLetterChangedListener listener = mOnTouchingLetterChangedListener;

        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景色
                choose = -1;
                invalidate();
                if (mDialogText != null) {
                    mDialogText.setVisibility(INVISIBLE);
                }
                break;
            default:
                setBackgroundResource(R.drawable.letter_navigation_bar_bg);
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchLetterChanged(b[c]);
                        }
                        if (mDialogText != null) {
                            mDialogText.setText(b[c]);
                            mDialogText.setVisibility(VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 向外松开的方法
     *
     * @param mOnTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener mOnTouchingLetterChangedListener) {
        this.mOnTouchingLetterChangedListener = mOnTouchingLetterChangedListener;
    }

    /**
     * 接口
     *
     * @anthur 刘冬
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchLetterChanged(String s);
    }
}
