package io.rong.imkit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.adapter.MessageListAdapter;

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

        super.bindView(v, position, data);
    }
}
