package study.lsy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.lsy.bean.JsonMode;
import study.lsy.bean.working;
import study.lsy.user.biz.getWorkBiz;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class WorkingController {
    @Autowired
    getWorkBiz getWorkBiz;
    @Autowired
    study.lsy.user.biz.getMovieBiz getMovieBiz;

    @RequestMapping("/getwork")
    public JsonMode getwork(HttpSession session, JsonMode js){
        working working = (working)session.getAttribute("work");
        List<working> list = getWorkBiz.getWorking(working.getId());
        if (list.isEmpty() || list==null){
            js.setCode(0);
        }else {
            js.setCode(1);
            js.setData(list);
        }
        return js;
    }
    @RequestMapping("/updatework")
    public int updatework(String id,String email,String phone,String textarea){
        return getWorkBiz.updatework(id,email,phone,textarea);
    }
    @RequestMapping("/getmovieall")
    public JsonMode getMovie(){
        return null;
    }
    @RequestMapping("/getmanagerlog")
    public boolean getmanagerlog(String name ,String password,HttpSession session){
        try {
            List<working> list= getWorkBiz.getworkbyid(name,password);
            if (list == null ||list.equals("")) return false;
            else {
                System.out.println(list);
                working working = list.get(0);
                session.setAttribute("work",working);
                return true;
            }
        }catch (Exception E){
            return false;
        }

    }
}
