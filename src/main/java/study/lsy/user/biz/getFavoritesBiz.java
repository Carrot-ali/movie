package study.lsy.user.biz;

import study.lsy.bean.favorites;

import java.util.List;

public interface getFavoritesBiz {
    public List<favorites> getlist();
    public List<String> getonelistbyid(String id);
}
