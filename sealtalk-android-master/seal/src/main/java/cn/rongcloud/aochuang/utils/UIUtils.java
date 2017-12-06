package cn.rongcloud.aochuang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.rongcloud.aochuang.App;
import cn.rongcloud.aochuang.R;


/**
 * @author Xu
 */
public class UIUtils {

    private static final int[] RES_IDS_ACTION_BAR_SIZE = {R.attr.actionBarSize};

    private static int statusBarHeight = 0;

    /**
     * Calculates the Action Bar height in pixels.
     */
    public static int calculateActionBarSize(Context context) {
        if (context == null) {
            return 0;
        }

        Resources.Theme curTheme = context.getTheme();
        if (curTheme == null) {
            return 0;
        }

        TypedArray att = curTheme.obtainStyledAttributes(RES_IDS_ACTION_BAR_SIZE);
        if (att == null) {
            return 0;
        }

        float size = att.getDimension(0, 0);
        att.recycle();
        return (int) size;
    }


    /**
     * Calculates the Status Bar height in pixels.
     */
    public static int calculateStatusBarSize(Context context) {
        if (statusBarHeight > 0) {
            return statusBarHeight;
        }
        if (context == null) {
            return 0;
        }
        Rect rectangle = new Rect();
        Activity activity = getActivityFromContext(context);
        if (activity == null) {
            return 0;
        }
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        statusBarHeight = rectangle.top;
        return statusBarHeight;
    }

    public static boolean isActivityDestroyed(Activity activity) {
        if (activity == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity.isDestroyed();
        } else {
            return activity.isFinishing();
        }
    }

    public static Activity getActivityFromContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivityFromContext(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /**
     * @param recyclerView
     * @param view         该View随着recyclerview的滚动而改变背景色
     */
    public static void trunTransparentBackgroundAlongWithScroll(RecyclerView recyclerView, final View view) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int scrollYOffset = 0;

            @SuppressWarnings("Range")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollYOffset += dy;
                if (scrollYOffset == 0) {
                    view.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    int alpha = scrollYOffset > 300 ? 0xdd : (int) (0xdd * scrollYOffset / 300.0f);
                    int frameBackgroundColor = ColorUtils.setAlphaComponent(Color.BLACK, alpha);
                    view.setBackgroundColor(frameBackgroundColor);
                }
            }

        });
    }

    /**
     * 屏幕宽度
     *
     * @return
     */
    public static int screenWidthPixels() {
        return App.getContext().getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕高度
     *
     * @return
     */
    public static int screenHeightPixels() {
        return App.getContext().getDisplayMetrics().heightPixels;
    }

    /**
     * @param listView
     * @param isFixItemHeight item是否为固定高度
     * @return
     */
    public static int getTotalHeightOfListView(@NonNull ListView listView, boolean isFixItemHeight) {
        ListAdapter adapter = listView.getAdapter();
        int totalItemHeight = 0;
        final int itemCount = adapter.getCount();
        for (int i = 0; i < itemCount; i++) {
            View child = adapter.getView(i, null, listView);
            child.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            if (isFixItemHeight) {
                totalItemHeight = child.getMeasuredHeight() * itemCount;
                break;
            } else {
                totalItemHeight += child.getMeasuredHeight();
            }
        }
        return totalItemHeight + (listView.getDividerHeight() * (itemCount - 1));
    }
}
