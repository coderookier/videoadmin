package org.video.admin.pojo.bean;

/**
 * @author gutongxue
 * @date 2019/11/28 15:07
 **/
public class AdminUser {
    private String usrname;
    private String usertoken;
    private String password;

    public AdminUser(String usrname, String usertoken, String password) {
        this.usrname = usrname;
        this.usertoken = usertoken;
        this.password = password;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
