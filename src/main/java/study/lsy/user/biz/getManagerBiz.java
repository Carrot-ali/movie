package study.lsy.user.biz;

import study.lsy.bean.working;

import java.util.List;

public interface getManagerBiz {
    public List<working> getmanager(String id);
    public void updateManager(String id,String email,String phone,String textarea);
    public void updateauthority(String id,String level);
    public void updatepassword(String id,String password);
    public void updatepageURL(String Pageurl);
    public void addnewmovie();

}
