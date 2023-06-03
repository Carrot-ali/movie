package study.lsy.user.biz;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.lsy.bean.user;
import study.lsy.mapper.userMapper;

import java.util.List;

@Service
public class getUserBizImpl implements getUserBiz {

    @Autowired
    userMapper userMapper;
    /**
     * 新建用户
     * @param Uid 用户id
     * @param Password 用户密码
     * @param postbox 用户邮箱
     */
    @Override
    public void newUser(String Uid, String Password,  String postbox) {
        user user = new user();
        user.setId(Uid);
        user.setPassword(Password);
        user.setPostbox(postbox);
        userMapper.insert(user);
    }

    /**
     * 修改密码
     * @param Uid 用户id
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     */
    @Override
    public void changePassword(String Uid, String OldPassword, String NewPassword) {
        user user = new user();
    }

    /**
     * 发送邮箱验证码
     * @param Postbox 验证码邮箱
     */
    @Override
    public String getVerification(String Postbox) {
        String math = getmath();
        String newstring = createverification(math);
        MailUtil.send(Postbox,"系统发送的验证码",newstring,true,null);
        return math;
    }
    public String getmath(){
        String math=Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
        return math;
    }
    public String createverification(String math){
        String q="<head>\n" +
                "    <base target=\"_blank\" />\n" +
                "    <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
                "    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
                "    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                "    <style type=\"text/css\">\n" +
                "        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                "        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                "        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                "        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                "        img{ border:0}\n" +
                "        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                "        blockquote{margin-right:0px}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body tabindex=\"0\" role=\"listitem\">\n" +
                "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                "                    <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                        您正在进行<span style=\"color: red\">注销账号</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">"+math+"</span>，以完成操作。\n" +
                "                    </strong>\n" +
                "                </div>\n" +
                "                <div style=\"margin-bottom:30px;\">\n" +
                "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                "                        <p style=\"color:#747474;\">\n" +
                "                            注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                "                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                "                        </p>\n" +
                "                    </small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"width:700px;margin:0 auto;\">\n" +
                "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                    <p>此为系统邮件，请勿回复<br>\n" +
                "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                "                    </p>\n" +
                "                    <p>电影院测试项目组</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n";
        return q;
    }
    /**
     * 用户登陆
     * @param uid 用户名
     * @param Password 密码
     */
    @Override
    public List<user> userlogin(String uid, String Password) {
        user user = new user();
        QueryWrapper<user> qw = new QueryWrapper<>();
        qw.eq("id",uid);
        qw.eq("password",Password);
        return userMapper.selectList(qw);
    }

    /**
     * 查找是否存在uid为该数值的用户
     * @param uid
     * @return
     */
    @Override
    public user finduser(String uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public int changepassword(String uid, String password) {
        user user = new user();
        QueryWrapper<user> qw = new QueryWrapper<>();
        user.setPassword(password);
        qw.eq("id",uid);
        return userMapper.update(user,qw);
    }


}
