package study.lsy.user.biz;

import study.lsy.bean.user;

import java.util.List;

public interface getUserBiz {

    public void newUser(String Uid,String Password, String Postbox);
    public void changePassword(String Uid,String OldPassword,String NewPassword);
    public String getVerification(String Postbox);
    public List<user> userlogin(String uid, String Password);
    public user finduser(String uid);
    public int changepassword(String uid, String password);

}
