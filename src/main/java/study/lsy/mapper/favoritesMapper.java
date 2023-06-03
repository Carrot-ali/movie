package study.lsy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.lsy.bean.favorites;

import java.util.List;

public interface favoritesMapper extends BaseMapper<favorites> {
    @Select("select comid from favorites where id = '${id}' ")
    List<String> findfavorid(@Param("id")String id);
}
