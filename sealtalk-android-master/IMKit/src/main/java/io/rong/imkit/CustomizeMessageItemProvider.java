package io.rong.imkit;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/************************************
 *
 * *  *   * * *      *****    ******           No Bugs okay?
 *  * *   *   *          *  *  *       Created by David MC on 2017/10/23.  
 *  * *   * * *            * *

 *************************************/
@ProviderTag(messageContent = RectPicMessage.class)
public class CustomizeMessageItemProvider extends IContainerItemProvider.MessageProvider<RectPicMessage> {
    class ViewHolder {
        TextView message;
    }

    @Override
    public void bindView(View view, int i, RectPicMessage customizeMessage, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
        holder.message.setText(customizeMessage.getContent());
        //  AndroidEmoji.ensure((Spannable) holder.message.getText());//显示消息中的 Emoji 表情。
    }

    @Override
    public Spannable getContentSummary(RectPicMessage customizeMessage) {
        return new SpannableString("这是一条自定义消息CustomizeMessage");
    }

    @Override
    public void onItemClick(View view, int i, RectPicMessage customizeMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(io.rong.imkit.R.layout.rc_item_custom_message, null);
        ViewHolder holder = new ViewHolder();
        holder.message = (TextView) view.findViewById(R.id.text);
        view.setTag(holder);
        return view;
    }
}
