package cn.ld.ljc;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ld.ljc.base.BaseActivity;
import cn.ld.ljc.view.LettersNavigationView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.show)
    Button show;
    @Bind(R.id.navigation_bar)
    LettersNavigationView navigationView;
    @Bind(R.id.dialog_txt)
    TextView dialogTxt;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initContentView() {
        super.initContentView();
        ButterKnife.bind(this, subContent);
    }

    @Override
    protected void initData() {
        super.initData();
        show.setOnClickListener(clickListener);
        setSubTitle("show", clickListener);

        navigationView.setDialogTv(dialogTxt);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.show:
                    showProgressDialog();
                    break;
                case R.id.sub_title:
                    showPopupWindow(v);
                    break;
            }
        }
    };

    private void showPopupWindow(View v) {
        View view = getLayoutInflater().inflate(R.layout.pop, null);
        PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.showAsDropDown(v, -225, 0);
    }

}
