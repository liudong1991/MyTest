package cn.ld.ljc.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import cn.ld.ljc.R;

/**
 * Created by Shinelon on 2016/5/9.
 */
public class CustomPopwindow extends PopupWindow {

    private View contentView;

    public CustomPopwindow(Activity context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.custom_popupwindow_layout, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        setContentView(contentView);
        setWidth(w / 2 + 50);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        //刷新状态
        update();
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        setBackgroundDrawable(dw);
    }

    public void showPopupWindow(View parent) {
        if (!isShowing()) {
            showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            dismiss();
        }
    }
}
