package study.lsy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.lsy.bean.JsonMode;
import study.lsy.bean.lable;
import study.lsy.bean.movie;
import study.lsy.user.biz.getMovieBiz;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@RestController
public class MovieController {
    @Autowired
    getMovieBiz getMovieBiz;

    @RequestMapping("/Mgetmoviepage")
    public JsonMode Mgetmoviepage(int ipagename,String mname,String mlable,JsonMode js){
        List<movie> list = getMovieBiz.getmovieIpage(ipagename,5,mname,mlable);
        if (list == null || list.equals("")){
            js.setCode(0);
        }else {
            js.setCode(1);
            js.setData(list);
        }
        return js;
    }

    @RequestMapping("/getoptions")
    public JsonMode getoptions(JsonMode js){
        List<lable> list = getMovieBiz.getoptions();
        if (list==null){
            js.setCode(0);
        }else {
            js.setCode(1);
            js.setData(list);
        }
        return js;
    }

    @RequestMapping("/getintmovie")
    public int getintmovie(String lable,String name){
        return getMovieBiz.getmovie(lable,name);
    }

    @RequestMapping("/usergetallmovie")
    public JsonMode usergetallmovie(JsonMode jm,String mname,String label,int pagename){
        List<movie> list = getMovieBiz.getmovieIpage(pagename,10,mname,label);
        if (list == null){
            jm.setCode(0);
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getalllabel")
    public JsonMode getalllabel(JsonMode jm){
        List<lable> list = getMovieBiz.getalllable();
        if (list == null){
            jm.setCode(0);
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getmoviebyname")
    public JsonMode getmoviebyname(JsonMode jm,String mname,String style,String local,String year){
        List<movie> list = getMovieBiz.getmoviebyname(mname,style,local,year);
        if (list ==null) {
            jm.setCode(0);
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/getmoviecount")
    public int getmoviecount(String name, String style, String local, String year){
        return getMovieBiz.getallcount(name,style,local,year);
    }

    @RequestMapping("/getperfectmovie")
    public JsonMode getperfectmovie(JsonMode jm){
        List<movie> list = getMovieBiz.getperfectmovie();
        if (list == null){
            jm.setCode(0);
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getthreelabel")
    public JsonMode getthreelabel(JsonMode jm,String label1, String label2,String label3){
        List<movie> list = getMovieBiz.getthreelabel(label1,label2,label3);
        if (list==null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/getweekmovie")
    public JsonMode getweekmovie(JsonMode jm){
        List<movie> list = getMovieBiz.getweekmovie();
        if (list == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }

    @RequestMapping("/atributethemovie")
    public void atributethemovie(String id, HttpSession session){
        session.removeAttribute("movieid");
        session.setAttribute("movieid",id);
    }

    @RequestMapping("/atributgetemovie")
    public String atributgetemovie(HttpSession session,JsonMode jm){
        return session.getAttribute("movieid").toString();
    }

    @RequestMapping("/getmoviebyid")
    public JsonMode getmoviebyid(String mid,JsonMode jm){
        List<movie> list = getMovieBiz.getmoviebyid(mid);
        if (list == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/getmovies")
    public JsonMode getmovies(JsonMode jm){
        List<movie> list = getMovieBiz.getallmovie();
        List<String> list2 = new ArrayList<>();
        for (movie m:list){
            list2.add(m.getName());
        }
        if (list2 == null) jm.setCode(0);
        else {
            jm.setCode(1);
            jm.setData(list2);
        }
        return jm;
    }
}
