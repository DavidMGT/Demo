package cn.rongcloud.aochuang.server.response;


/**
 * Created by AMing on 15/12/24.
 * Company RongCloud
 */
public class LoginResultResponse {

    /**
     * detailMsg : 登录成功！
     * resultCode : 100
     * data : {"id":18,"nick_name":"昵称","head_pic":"localhost:9999/static/pic/default.jpg","group":"4,6","area_code":"+86","phone":"18571150233","user_code":"AC1205162","token":"C7K5pUuOr3c4pn2RPXCkKgn/qXtxyWXYAnAmM0PwwVmANjFTwgvpOJsf1sODS08eD+fW7Kyx/z+yyM60Qckc3rXqJSkyLSxp"}
     */

    private String detailMsg;
    private int resultCode;
    private DataBean data;

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 18
         * nick_name : 昵称
         * head_pic : localhost:9999/static/pic/default.jpg
         * group : 4,6
         * area_code : +86
         * phone : 18571150233
         * user_code : AC1205162
         * token : C7K5pUuOr3c4pn2RPXCkKgn/qXtxyWXYAnAmM0PwwVmANjFTwgvpOJsf1sODS08eD+fW7Kyx/z+yyM60Qckc3rXqJSkyLSxp
         */

        private int id;
        private String nick_name;
        private String head_pic;
        private String group;
        private String area_code;
        private String phone;
        private String user_code;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
