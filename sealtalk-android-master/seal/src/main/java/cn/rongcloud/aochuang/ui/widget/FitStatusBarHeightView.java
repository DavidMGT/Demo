package cn.rongcloud.aochuang.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.rongcloud.aochuang.R;
import cn.rongcloud.aochuang.ui.widget.statusbar.StatusBarCompat;
import cn.rongcloud.aochuang.utils.UIUtils;


/**
 * @author Xu
 */
public class FitStatusBarHeightView extends View {

    public FitStatusBarHeightView(Context context) {
        super(context);
        init();
    }

    public FitStatusBarHeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FitStatusBarHeightView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Activity activity = UIUtils.getActivityFromContext(getContext());
        if (activity != null) {
            StatusBarCompat.translucentStatusBar(activity, true);
        }
        setBackgroundResource(R.color.seal_bg);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(StatusBarCompat.getStatusBarHeight(getContext()), MeasureSpec.EXACTLY));
    }
}