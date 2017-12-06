package cn.rongcloud.aochuang.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jrmf360.rylib.common.util.ToastUtil;

import cn.rongcloud.aochuang.R;
import cn.rongcloud.aochuang.message.plugin.ResultPlugin;
import cn.rongcloud.aochuang.ui.activity.ReadReceiptDetailActivity;
import cn.rongcloud.aochuang.ui.manager.StakeManager;
import cn.rongcloud.aochuang.utils.LogUtils;
import io.rong.common.RLog;
import io.rong.imkit.AESCrypt;
import io.rong.imkit.MyMessageListAdapter;
import io.rong.imkit.RectPicMessage;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.mention.RongMentionManager;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * 会话 Fragment 继承自ConversationFragment
 * onResendItemClick: 重发按钮点击事件. 如果返回 false,走默认流程,如果返回 true,走自定义流程
 * onReadReceiptStateClick: 已读回执详情的点击事件.
 * 如果不需要重写 onResendItemClick 和 onReadReceiptStateClick ,可以不必定义此类,直接集成 ConversationFragment 就可以了
 * Created by Yuejunhong on 2016/10/10.
 */
public class ConversationFragmentEx extends ConversationFragment {
    private StakeManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        manager = new StakeManager(this, view);
        return view;
    }

    @Override
    public MessageListAdapter onResolveAdapter(Context context) {
        return new MyMessageListAdapter(context);
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
        if (pluginModule instanceof ResultPlugin) {
            LogUtils.d("点就了自定义消息");
            RectPicMessage customizeMessage = new RectPicMessage("点就了自定义消息");
            Message message = Message.obtain(getTargetId(), getConversationType(), customizeMessage);
            RongIM.getInstance().sendMessage(message, customizeMessage.getContent(), customizeMessage.getContent(), (IRongCallback.ISendMessageCallback) null);
        }
        ToastUtil.showToast(getContext(), "onPluginClicked" + pluginModule.getClass().getName());
    }

    @Override
    public void onMenuClick(int root, int sub) {
        super.onMenuClick(root, sub);
        ToastUtil.showToast(getContext(), "onMenuClick :root-=" + root + " :sub=" + sub);
    }

    private View originView;

    @Override
    public void onSwitchToggleClick(View v, ViewGroup inputBoard) {
        ((ImageView) v).setImageResource(R.drawable.app_icon);
        if (!manager.onSwitchToggleClick(v, inputBoard)) {
            super.onSwitchToggleClick(v, inputBoard);
        }
    }

    @Override
    public void onEmoticonToggleClick(View v, ViewGroup extensionBoard) {
        super.onEmoticonToggleClick(v, extensionBoard);
        ToastUtil.showToast(getContext(), "onEmoticonToggleClick");
    }

    @Override
    public void onPluginToggleClick(View v, ViewGroup extensionBoard) {
        manager.onPluginToggleClick(v, extensionBoard);
    }

    @Override
    public void onSendToggleClick(View v, String text) {
        if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(text.trim())) {
            TextMessage textMessage = TextMessage.obtain(AESCrypt.encrypt(text));
            MentionedInfo mentionedInfo = RongMentionManager.getInstance().onSendButtonClick();
            if (mentionedInfo != null) {
                textMessage.setMentionedInfo(mentionedInfo);
            }
            Message message = Message.obtain(getTargetId(), getConversationType(), textMessage);
            RongIM.getInstance().sendMessage(message, (String) null, (String) null, (IRongCallback.ISendMessageCallback) null);
        } else {
            RLog.e("ConversationFragment", "text content must not be null");
        }
    }
}
