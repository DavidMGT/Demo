package cn.rongcloud.aochuang.server.request;


/**
 * Created by AMing on 15/12/24.
 * Company RongCloud
 */
public class LoginRequest {
    private String region;
    private String phone;
    private String password;
    private String login_name;

    private String pass_wd;

    public LoginRequest(String region, String phone, String password) {
        this.region = region;
        this.phone = phone;
        this.password = password;
    }

    public LoginRequest(String phone, String password) {
        this.login_name = phone;
        this.pass_wd = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPass_wd() {
        return pass_wd;
    }

    public void setPass_wd(String pass_wd) {
        this.pass_wd = pass_wd;
    }
}
