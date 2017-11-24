package cn.rongcloud.aochuang.message.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.jrmf360.rylib.common.util.ToastUtil;

import cn.rongcloud.aochuang.R;
import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/************************************
 *
 * *  *   * * *      *****    ******           No Bugs okay?
 *  * *   *   *          *  *  *       Created by David MC on 2017/10/10.  
 *  * *   * * *            * *

 *************************************/

public class ResultPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.tab_found);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.touzhu_result);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        ToastUtil.showToast(fragment.getContext(), "功能开发当中");
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
