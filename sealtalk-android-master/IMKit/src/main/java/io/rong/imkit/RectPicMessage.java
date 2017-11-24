package io.rong.imkit;

import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/************************************
 *
 * *  *   * * *      *****    ******           No Bugs okay?
 *  * *   *   *          *  *  *       Created by David MC on 2017/10/25.  
 *  * *   * * *            * * 自定义的一种消息类型。展示的是投注结果

 *************************************/

@MessageTag(
        value = "RC:TxtMsg1",
        flag = 3
)
public class RectPicMessage extends MessageContent {
    private static final String TAG = "TextMessage";
    private String content;
    protected String extra;
    public static final Creator<RectPicMessage> CREATOR = new Creator() {
        public RectPicMessage createFromParcel(Parcel source) {
            return new RectPicMessage(source);
        }

        public RectPicMessage[] newArray(int size) {
            return new RectPicMessage[size];
        }
    };

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("content", this.getContent());

        } catch (JSONException var4) {
            RLog.e("TextMessage", "JSONException " + var4.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }


    protected RectPicMessage() {
    }

    public static RectPicMessage obtain(String text) {
        RectPicMessage model = new RectPicMessage();
        model.setContent(text);
        return model;
    }

    public RectPicMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        try {
            JSONObject e = new JSONObject(jsonStr);
            if (e.has("content")) {
                this.setContent(e.optString("content"));
            }

        } catch (JSONException var4) {
            RLog.e("TextMessage", "JSONException " + var4.getMessage());
        }

    }

    public void setContent(String content) {
        this.content = content;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, this.content);
    }

    public RectPicMessage(Parcel in) {
        this.setContent(ParcelUtils.readFromParcel(in));
        //this.setUserInfo((UserInfo) ParcelUtils.readFromParcel(in, UserInfo.class));
        //  this.setMentionedInfo((MentionedInfo) ParcelUtils.readFromParcel(in, MentionedInfo.class));
    }

    public RectPicMessage(String content) {
        this.setContent(content);
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getSearchableWord() {
        ArrayList words = new ArrayList();
        words.add(this.content);
        return words;
    }
}