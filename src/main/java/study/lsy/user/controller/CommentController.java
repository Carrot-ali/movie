package study.lsy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.lsy.bean.JsonMode;
import study.lsy.bean.comment;
import study.lsy.bean.follow;
import study.lsy.bean.user;
import study.lsy.user.biz.getCommentonBiz;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    getCommentonBiz getCommentonBiz;

    @RequestMapping("/getcomm")
    public JsonMode getcomm(String comid,JsonMode jm){
        List<comment> list = getCommentonBiz.getcommenbyid(comid);
        if (list == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getcomauthor")
    public JsonMode getcomauthor(String authorid,JsonMode jm){
        List<user> list =  getCommentonBiz.getcommenuser(authorid);
        if (list == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getstart")
    public boolean getstart(String cid, String wid,JsonMode jm){
        List<follow> list = getCommentonBiz.thecomstart(cid,wid);
        if (list == null) return false;
        else return true;
    }
    @RequestMapping("/addstart")
    public int addstart(String cid, String wid){
        return getCommentonBiz.addstart(cid,wid);
    }
    @RequestMapping("/deletestart")
    public int deletestart(String cid,String wid){
        return getCommentonBiz.deletestart(cid,wid);
    }

    @RequestMapping("/atributecommen")
    public void atributethemovie(String id, HttpSession session){
        session.removeAttribute("commenid");
        session.setAttribute("commenid",id);
    }

    @RequestMapping("/atributegetcommen")
    public String atributgetemovie(HttpSession session,JsonMode jm){
        return session.getAttribute("commenid").toString();
    }

    @RequestMapping("/getallcommen")
    public JsonMode getallcommen(JsonMode jm){
        List<comment> list = getCommentonBiz.getcommenall();
        if (list == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

}
