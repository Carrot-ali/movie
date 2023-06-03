package study.lsy.user.biz;

import study.lsy.bean.comment;
import study.lsy.bean.follow;
import study.lsy.bean.user;

import java.util.List;

public interface getCommentonBiz {
    public List<comment> getcommenbyid(String cmid);
    public List<user> getcommenuser(String uid);
    public List<follow> thecomstart(String cid, String wid);
    public int addstart(String cid,String wid);
    public int deletestart(String cid, String wid);
    public List<comment> getcommenall();
}
