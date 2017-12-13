package cn.rongcloud.aochuang.server.response;


/**
 * Created by AMing on 15/12/23.
 * Company RongCloud
 */
public class RegisterResponse {


    private int resultCode;
    private String detailMsg;


    private String data;

    private ResultEntity result;

    public int getCode() {
        return resultCode;
    }

    public void setCode(int code) {
        this.resultCode = code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public static class ResultEntity {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
