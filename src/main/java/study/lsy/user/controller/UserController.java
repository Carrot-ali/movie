package study.lsy.user.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.lsy.bean.JsonMode;
import study.lsy.bean.user;
import study.lsy.user.biz.getUserBiz;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    getUserBiz getUserBiz;

    /**
     * 登陆验证
     * @return 返回是否存在该用户
     */
    @RequestMapping("/userloging")
    public boolean getlog(String uid,String password,HttpSession session){
        session.removeAttribute("uid");
        List<user> getlist = getUserBiz.userlogin(uid,password);
        System.out.println(getlist);
        if (getlist.size()==0 || getlist.isEmpty()){
            return false;
        }else{
            user user = getlist.get(0);
            session.setAttribute("uid",user);
            return true;
        }
    }

    /**
     * 新用户创建
     * @param uid 用户名
     * @param password 密码
     * @param postbox 邮箱
     * @return
     */
    @RequestMapping("/addnewuser")
    public boolean addnewuser(String uid,String password,String postbox,String verification,HttpSession session){
        try{
            if (verification.equals(session.getAttribute("verification"))){
                session.removeAttribute("verification");
                getUserBiz.newUser(uid,password,postbox);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 发送邮件
     * @param postbox 收邮件的邮件
     */
    @RequestMapping("/postbox")
    public void postbox(String postbox,HttpSession session) {
        session.removeAttribute("verification");
        String math = getUserBiz.getVerification(postbox);
        session.setAttribute("verification",math);
    }
    /**
     * 生成图形验证码
     */
    @RequestMapping("/pageverification")
    public void getverification(HttpServletResponse resp,HttpSession session) throws IOException {
        session.removeAttribute("pageverti");
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
        //LineCaptcha lineCaptcha = new LineCaptcha(200, 100, 4, 150);
        //图形验证码写出，可以写出到文件，也可以写出到流
        session.setAttribute("pageverti",lineCaptcha.getCode());
        ServletOutputStream sos = resp.getOutputStream();
        lineCaptcha.write(sos);
        sos.flush();
        sos.close();
    }
    /**
     * 验证图形验证码
     */
    @RequestMapping("/pageverti")
    public boolean pageverti(HttpSession session, String Verification1){
        if (session.getAttribute("pageverti").equals(Verification1)){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 验证用户是否存在
     */
    @RequestMapping("/finduser")
    public JsonMode findusergetword(HttpSession session,String uid,JsonMode js){
        user users = getUserBiz.finduser(uid);
        if (users!=null){
            session.setAttribute("findpassworduser",users);
            postbox(users.getPostbox(),session);
            js.setCode(1);
            js.setData(users);
            return js;
        }else {
            js.setCode(0);
            js.setMsg("未找到账户信息");
            return js;
        }
    }
    /**
     * 验证用户的邮箱验证码
     * 可改成redis 30分钟自动过期
     */
    @RequestMapping("/emailisreal")
    public boolean emailisreal(HttpSession session,String verification){
        if (session.getAttribute("verification").equals(verification)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("/repassword")
    public int repassword(String uid,String newpassword){
        int i = getUserBiz.changepassword(uid,newpassword);
        return i;
    }
}
