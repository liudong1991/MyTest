package cn.ld.ljc.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.ld.ljc.R;
import cn.ld.ljc.view.CustomDialog;

/**
 * Created by Shinelon on 2016/5/3.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected View head;
    protected ViewGroup subContent;

    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar(this);
        setContentView(R.layout.base_layout);
        initBaseView();
        getIntentData();
        getIntentData(savedInstanceState);
        initContentView();
        initData();
    }

    private void initBaseView() {
        head = findViewById(R.id.head);
        subContent = (ViewGroup) findViewById(R.id.sub_content);
        View view = getLayoutInflater().inflate(getContentView(), null);
        subContent.addView(view);
    }

    protected abstract int getContentView();

    protected void getIntentData() {

    }

    protected void getIntentData(Bundle savedInstanceState) {

    }

    protected void initContentView() {

    }

    protected void initData() {

    }

    protected void setSubTitle(String subTitle, View.OnClickListener clickListener) {
        TextView subTitleTv = (TextView) head.findViewById(R.id.sub_title);
        subTitleTv.setVisibility(View.VISIBLE);
        subTitleTv.setText(subTitle);
        if (clickListener != null) {
            subTitleTv.setOnClickListener(clickListener);
        }
    }

    protected void showProgressDialog() {
        if (dialog == null) {
            dialog = new CustomDialog(this, R.style.costom_dialog);
        }
        dialog.show();
    }

    protected void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        tintManager.setStatusBarTintResource(R.color.basecolor);
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
