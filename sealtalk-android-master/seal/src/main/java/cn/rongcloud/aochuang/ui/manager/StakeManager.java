package cn.rongcloud.aochuang.ui.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jrmf360.rylib.common.util.ToastUtil;

import cn.rongcloud.aochuang.R;
import io.rong.eventbus.EventBus;
import io.rong.imkit.AESCrypt;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by user on 2017/10/16.
 */

public class StakeManager {
    private ConversationFragment fragment;
    private Activity context;


    private View mCustomlayout, originView;
    private ViewGroup rc_container_layout, user_stake_content_layout;
    private EditText mEditText;

    public boolean IsCustomlayoutVisable() {
        return mCustomlayout.getVisibility() == View.VISIBLE;
    }

    private View.OnClickListener onNumInputClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                Button b = (Button) v;
                mEditText.append(b.getText().toString());
            } else {
                String s = mEditText.getText().toString();
                if (!s.isEmpty() && ':' != s.charAt(s.length() - 1)) {
                    mEditText.setText(s.substring(0, s.length() - 1));
                }
            }
        }
    };
    private View.OnClickListener onSidesSelector = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer i = (Integer) v.getTag();
            TextView tv = (TextView) v;
            if (i != null && i < 7) {
                String s = mEditText.getText().toString();
                if (s.matches("¥\\S{1,2}\\:\\d*")) {
                    int index = s.indexOf(":");
                    s = s.substring(index + 1);
                }
                s = "¥" + tv.getText().toString() + ":" + s;
                mEditText.setText(s);
            } else if (i != null && i == 7) {
                String s = mEditText.getText().toString();
                if (s.matches("¥\\S{1,2}\\:\\d*")) {
                    int index = s.indexOf(":");
                    s = s.substring(index + 1);
                }
                mEditText.setText(s);
            }
        }
    };

    public StakeManager(ConversationFragment fragment, View root) {
        this.fragment = fragment;
        context = fragment.getActivity();
        init(root);
    }

    private void init(View view) {
        mCustomlayout = view.findViewById(io.rong.imkit.R.id.mCustomLayout);
        rc_container_layout = (ViewGroup) view.findViewById(io.rong.imkit.R.id.rc_container_layout);
        user_stake_content_layout = (ViewGroup) View.inflate(context, io.rong.imkit.R.layout.user_stake_edittext, null);
        mEditText = (EditText) user_stake_content_layout.findViewById(io.rong.imkit.R.id.user_stake_edittext);
        //数字按钮
        View[] nums = new View[11];
        for (int i = 0; i < 11; i++) {
            nums[i] = mCustomlayout.findViewById(io.rong.imkit.R.id.user_stake_num_1 + i);
            nums[i].setOnClickListener(onNumInputClick);
        }
        //押方选择
        View[] sides = new View[8];
        for (int i = 0; i < 8; i++) {
            sides[i] = mCustomlayout.findViewById(io.rong.imkit.R.id.user_stake_side_1 + i);
            sides[i].setTag(i);
            sides[i].setOnClickListener(onSidesSelector);
        }
        // 下注确认
        View stake_confirm = mCustomlayout.findViewById(io.rong.imkit.R.id.user_stake_confirm);
        stake_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = mEditText.getText().toString();
                if (!s.matches("¥\\S{1,2}\\:\\d{1,}")) {
                    ToastUtil.showToast(context, "你的押注不符合规则.");
                } else {
                    String i = s.substring(s.indexOf(":") + 1);
                    int i1 = Integer.parseInt(i);
                    if (i1 > 0) {
                        // 构建文本消息实例
                        final TextMessage textMessage = TextMessage.obtain(AESCrypt.encrypt(s));
                        RongIMClient.getInstance().sendMessage(fragment.getConversationType(), fragment.getTargetId(), textMessage, null, null, new IRongCallback.ISendMessageCallback() {

                            @Override
                            public void onAttached(Message message) {
                                EventBus.getDefault().post(message);
                            }

                            @Override
                            public void onSuccess(Message message) {
                                EventBus.getDefault().post(message);
                            }

                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                                EventBus.getDefault().post(message);
                            }
                        });
                        dismissBoad();
                    } else {
                        ToastUtil.showToast(context, "你的押注不符合规则.");
                    }
                }
            }
        });

    }

    public boolean onSwitchToggleClick(View v, ViewGroup inputBoard) {
        Log.d("px", inputBoard.getClass().getSimpleName() + "," + v.getClass().getSimpleName());
//        ((ImageView) v).setImageResource(R.drawable.app_icon);
        Log.d("px", "语音imageView id=" + R.id.rc_voice_toggle + v.getVisibility());
        if (mCustomlayout.getVisibility() != View.VISIBLE) {
            mCustomlayout.setVisibility(View.VISIBLE);
            originView = rc_container_layout.getChildAt(0);
            originView.clearFocus();
            rc_container_layout.removeAllViews();
            rc_container_layout.addView(user_stake_content_layout);
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        } else {
            mCustomlayout.setVisibility(View.GONE);
            rc_container_layout.removeAllViews();
            rc_container_layout.addView(originView);
            originView.requestFocus();
            return false;
        }
    }

    public void dismissBoad() {
        mCustomlayout.setVisibility(View.GONE);
        rc_container_layout.removeAllViews();
        rc_container_layout.addView(originView);
        originView.requestFocus();
    }

    public void onPluginToggleClick(View v, ViewGroup extensionBoard) {
        if (mCustomlayout.getVisibility() == View.VISIBLE) {
            onSwitchToggleClick(v, extensionBoard);
        }
    }
}
