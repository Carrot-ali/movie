package study.lsy.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import study.lsy.bean.working;
import study.lsy.mapper.workingMapper;

import java.util.List;
@Service
public class getManagerBizImpl implements getManagerBiz{

    workingMapper workingMapper;

    /**
     * 查询登录的员工信息
     * @param id
     * @return
     */
    @Override
    public List<working> getmanager(String id) {
        QueryWrapper<working> qw = new QueryWrapper<>();
        return workingMapper.selectList(qw);
    }

    /**
     * 更改对应的员工信息
     * @param id
     * @param email
     * @param phone
     * @param textarea
     */
    @Override
    public void updateManager(String id, String email, String phone, String textarea) {
        QueryWrapper<working> qw = new QueryWrapper<>();
        qw.eq("id",id);
        working working = new working();
        working.setEmail(email);
        working.setPhone(phone);
        working.setTextarea(textarea);
        workingMapper.update(working,qw);
    }

    /**
     * 更新角色的权限等级
     * @param id
     * @param level
     */
    @Override
    public void updateauthority(String id, String level) {

    }

    /**
     * 更改密码
     * @param id
     * @param password
     */
    @Override
    public void updatepassword(String id, String password) {

    }

    /**
     * 更改头像
     * @param Pageurl
     */
    @Override
    public void updatepageURL(String Pageurl) {

    }

    /**
     * 增加新电影
     */
    @Override
    public void addnewmovie() {

    }


}
