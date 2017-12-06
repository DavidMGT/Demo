package io.rong.imkit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.common.RLog;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

/************************************
 *
 * *  *   * * *      *****    ******           No Bugs okay?
 *  * *   *   *          *  *  *       Created by David MC on 2017/10/18.  
 *  * *   * * *            * *

 *************************************/

public class MyMessageListAdapter extends MessageListAdapter {
    public MyMessageListAdapter(Context context) {
        super(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        return super.newView(context, position, group);
    }

    @Override
    protected void bindView(View v, int position, UIMessage data) {
        MessageContent messageContent = data.getMessage().getContent();
        RLog.d("消息", "消息是" + data);
        if (messageContent instanceof TextMessage) {
            data.getMessage().setContent(new TextMessage(AESCrypt.decrypt(((TextMessage) messageContent).getContent())));
            RLog.d("消息类型", "消息是" + ((TextMessage) messageContent).getContent());
        }
        super.bindView(v, position, data);
    }
}
