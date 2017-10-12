package cn.rongcloud.aochuang.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jrmf360.rylib.common.util.ToastUtil;

import cn.rongcloud.aochuang.R;
import cn.rongcloud.aochuang.ui.activity.ReadReceiptDetailActivity;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * 会话 Fragment 继承自ConversationFragment
 * onResendItemClick: 重发按钮点击事件. 如果返回 false,走默认流程,如果返回 true,走自定义流程
 * onReadReceiptStateClick: 已读回执详情的点击事件.
 * 如果不需要重写 onResendItemClick 和 onReadReceiptStateClick ,可以不必定义此类,直接集成 ConversationFragment 就可以了
 * Created by Yuejunhong on 2016/10/10.
 */
public class ConversationFragmentEx extends ConversationFragment {

    private View mCustomlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mCustomlayout = view.findViewById(io.rong.imkit.R.id.mCustomLayout);
        return view;
    }

    @Override
    public void onReadReceiptStateClick(io.rong.imlib.model.Message message) {
        if (message.getConversationType() == Conversation.ConversationType.GROUP) { //目前只适配了群组会话
            Intent intent = new Intent(getActivity(), ReadReceiptDetailActivity.class);
            intent.putExtra("message", message);
            getActivity().startActivity(intent);
        }
    }

    public void onWarningDialog(String msg) {
        String typeStr = getUri().getLastPathSegment();
        if (!typeStr.equals("chatroom")) {
            super.onWarningDialog(msg);
        }
    }

    @Override
    public void onPluginClicked(IPluginModule pluginModule, int position) {
        super.onPluginClicked(pluginModule, position);
        ToastUtil.showToast(getContext(), "onPluginClicked" + pluginModule.getClass().getName());
    }

    @Override
    public void onMenuClick(int root, int sub) {
        super.onMenuClick(root, sub);
        ToastUtil.showToast(getContext(), "onMenuClick :root-=" + root + " :sub=" + sub);
    }

    @Override
    public void onSwitchToggleClick(View v, ViewGroup inputBoard) {
        // super.onSwitchToggleClick(v, inputBoard);
        ((ImageView) v).setImageResource(R.drawable.app_icon);
//        ToastUtil.showToast(getContext(), "onSwitchToggleClick");
        if (mCustomlayout.getVisibility() !=View.VISIBLE) {
            mCustomlayout.setVisibility(View.VISIBLE);
        } else {
            mCustomlayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onEmoticonToggleClick(View v, ViewGroup extensionBoard) {
        super.onEmoticonToggleClick(v, extensionBoard);
        ToastUtil.showToast(getContext(), "onEmoticonToggleClick");
    }
}
