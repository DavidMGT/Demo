package cn.rongcloud.aochuang.server.request;


/**
 * Created by AMing on 15/12/23.
 * Company RongCloud
 */
public class RegisterRequest {


    private String nickname;
    private String password;

    public String getNick_name() {
        return nick_name;
    }

    public RegisterRequest setNick_name(String nick_name) {
        this.nick_name = nick_name;
        return this;
    }

    public String getLogin_name() {
        return login_name;
    }

    public RegisterRequest setLogin_name(String login_name) {
        this.login_name = login_name;
        return this;
    }

    public String getPass_wd() {
        return pass_wd;
    }

    public RegisterRequest setPass_wd(String pass_wd) {
        this.pass_wd = pass_wd;
        return this;
    }

    public String getArea_code() {
        return area_code;
    }

    public RegisterRequest setArea_code(String area_code) {
        this.area_code = area_code;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getInvite_user_id() {
        return invite_user_id;
    }

    public RegisterRequest setInvite_user_id(String invite_user_id) {
        this.invite_user_id = invite_user_id;
        return this;
    }

    private String nick_name;
    private String login_name;
    private String pass_wd;
    private String area_code;
    private String phone;
    private String invite_user_id;

    private String verification_token;

    public RegisterRequest() {
    }

    public RegisterRequest(String nickname, String password, String verification_token) {
        this.nickname = nickname;
        this.password = password;
        this.verification_token = verification_token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification_token() {
        return verification_token;
    }

    public RegisterRequest setVerification_token(String verification_token) {
        this.verification_token = verification_token;
        return this;
    }
}
