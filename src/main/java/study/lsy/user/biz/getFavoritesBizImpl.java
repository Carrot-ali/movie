package study.lsy.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.lsy.bean.favorites;
import study.lsy.mapper.favoritesMapper;

import java.util.List;
@Service
public class getFavoritesBizImpl implements getFavoritesBiz{
    @Autowired
    favoritesMapper favoritesMapper;
    @Override
    public List<favorites> getlist() {
        QueryWrapper<favorites> qw = new QueryWrapper<>();
        return favoritesMapper.selectList(qw);
    }

    @Override
    public List<String> getonelistbyid(String id) {
        return favoritesMapper.findfavorid(id);
    }
}
