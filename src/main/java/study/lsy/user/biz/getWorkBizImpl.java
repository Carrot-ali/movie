package study.lsy.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.lsy.bean.working;
import study.lsy.mapper.workingMapper;

import java.util.List;

@Service
public class getWorkBizImpl implements getWorkBiz{
    @Autowired
    workingMapper workingMapper;
    /**
     * 获取指定的员工信息
     */
    @Override
    public List<working> getWorking(String id) {
        QueryWrapper<working> qw = new QueryWrapper<>();
        qw.eq("id",id);
        return workingMapper.selectList(qw);
    }

    /**
     * 修改员工信息（本人）
     *
     * @param
     * @return
     */
    @Override
    public int updatework(String id,String email,String phone,String textarea) {
        QueryWrapper<working> qw = new QueryWrapper<>();
        working working = workingMapper.selectById(id);
        working.setEmail(email);
        working.setPhone(phone);
        working.setTextarea(textarea);
        qw.eq("id",id);
        return workingMapper.update(working,qw);
    }

    /**
     * 是否存在员工
     * @param id
     * @param password
     * @return
     */
    @Override
    public List<working> getworkbyid(String id, String password) {
        QueryWrapper<working> qw = new QueryWrapper<>();
        qw.eq("id",id);
        qw.eq("password",password);
        return workingMapper.selectList(qw);
    }


}
