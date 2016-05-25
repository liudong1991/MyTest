package cn.ld.ljc.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ld.ljc.R;

/**
 * Created by Shinelon on 2016/5/10.
 */
public class CustomDialog extends Dialog {
    @Bind(R.id.progress)
    ProgressView progress;

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        progress.stop();
    }

    @Override
    public void show() {
        super.show();
        progress.start();
    }
}
