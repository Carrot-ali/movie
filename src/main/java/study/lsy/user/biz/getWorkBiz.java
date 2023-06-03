package study.lsy.user.biz;

import study.lsy.bean.working;

import java.util.List;

public interface getWorkBiz {
    public List<working> getWorking(String id);
    public int updatework(String id,String email,String phone,String textarea);
    public List<working> getworkbyid(String id,String password);
}
