package cn.ld.ljc.adapter;

import android.view.View;

/**
 * Created by liudong on 2016/5/25.
 */
public interface ViewHolderInterface {
    void injectView(View root);

    void fillView();
}
