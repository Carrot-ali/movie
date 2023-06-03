package study.lsy.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.lsy.bean.comment;
import study.lsy.bean.follow;
import study.lsy.bean.user;
import study.lsy.mapper.commentMapper;
import study.lsy.mapper.followMapper;
import study.lsy.mapper.userMapper;

import java.util.List;
@Service
public class getCommentonBizImpl implements getCommentonBiz{
    @Autowired
    commentMapper commentMapper;
    @Autowired
    userMapper userMapper;
    @Autowired
    followMapper followMapper;
    /**
     * 获取对应的影评信息
     * @return
     */
    @Override
    public List<comment> getcommenbyid(String cmid) {
        QueryWrapper<comment> qw = new QueryWrapper<>();
        qw.eq("comid",cmid);
        return commentMapper.selectList(qw);
    }

    /**
     * 获取写影评的用户信息
     * @param uid
     * @return
     */
    @Override
    public List<user> getcommenuser(String uid) {
        QueryWrapper<user> qw = new QueryWrapper<>();
        qw.eq("id",uid);
        return userMapper.selectList(qw);
    }

    /**
     * 查询用户是否关注影评发布人
     * @param cid
     * @param wid
     * @return
     */
    @Override
    public List<follow> thecomstart(String cid, String wid) {
        QueryWrapper<follow> qw = new QueryWrapper<>();
        qw.eq("id",cid);
        qw.eq("gid",wid);
        return followMapper.selectList(qw);
    }

    /**
     * 增加关注
     * @param cid
     * @param wid
     * @return
     */
    @Override
    public int addstart(String cid, String wid) {
        follow follow = new follow();
        follow.setId(cid);
        follow.setGid(wid);
        return followMapper.insert(follow);
    }

    /**
     * 取消关注
     * @param cid
     * @param wid
     * @return
     */
    @Override
    public int deletestart(String cid, String wid){
        QueryWrapper<follow> qw = new QueryWrapper<>();
        qw.eq("id",cid);
        qw.eq("gid",wid);
        return followMapper.delete(qw);
    }

    @Override
    public List<comment> getcommenall() {
        QueryWrapper<comment> qw = new QueryWrapper<>();
        return commentMapper.selectList(qw);
    }

}
