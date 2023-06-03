package study.lsy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.lsy.bean.JsonMode;
import study.lsy.bean.favorites;
import study.lsy.bean.movie;
import study.lsy.bean.user;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class AnalyzerController {
    @Autowired
    study.lsy.user.biz.getFavoritesBiz getFavoritesBiz;
    @Autowired
    study.lsy.user.biz.getMovieBiz getMovieBiz;


    public Map<String,Double> Analyzer(HttpSession session){
        List<favorites> list = getFavoritesBiz.getlist(); //获取所有的收藏信息
        HashSet<String> set = new HashSet<String>(list.size());
        List<String> list2 = new ArrayList<String>(list.size());
        for (favorites f : list) {
            if (set.add(f.getId())) {
                list2.add(f.getId()); // list2中存放了所有的用户id
            }
        }
        List<String> mylist = new ArrayList<String>();
        user user = (user)session.getAttribute("uid");
        for (favorites f:list){
            if (f.getId().equals(user.getId())){
                mylist.add(f.getComid());
            }
        }
        Map<String,Double> map = new HashMap<>();
        for (String s:list2){
            List<String> otherlist = new ArrayList<String>();
            for (int i = 0;i<list.size();i++){
                if (s.equals(list.get(i).getId())&&!s.equals(user.getId())){
                      otherlist.add(list.get(i).getComid());
                }
            }
            double l = getAnalyzer(mylist,otherlist);
            map.put(s,l);
        }
        // 返回用户和对应的分数
        return map;
    }

    public double getAnalyzer(List<?> listA,List<?> listB){
        int count = 0;
           for (int i = 0;i<listA.size();i++){
               String x  = (String)listA.get(i);
               for (int j = 0;j<listB.size();j++){
                   String y = (String)listB.get(j);
                   if (x.equals(y)){
                       count++;
                   }
               }
           }
           // 返回相似度分数
        Double result = (double) count/Math.sqrt(listA.size()*listB.size());
        return result;
    }

    /**
     * 返回相似度最高的用户收藏的id
     * @param session
     * @return
     */
    public List<String> getuserAnalyer(HttpSession session){
        Map<String,Double> map = Analyzer(session);
        Set<String> s = map.keySet();
        Double l1=0.0;
        String key = null;
        for (String str : s){
            if (map.get(str)>l1){
                key=str;
                l1=map.get(str);
            }
        }
        List<String> getlist = getFavoritesBiz.getonelistbyid(key); // 推荐的人收藏的电影
        //剔除掉当前用户本身收藏的电影
        //空间换时间 降低时间复杂度
        user user = (user)session.getAttribute("uid");
        List<String> list2 = getFavoritesBiz.getonelistbyid(user.getId());
        Map<String, String> maps = new HashMap<>();
        for(String str:list2){
            maps.put(str,str);
        }
        for (int i = 0;i<list2.size();i++){
            if (maps.containsKey(list2.get(i))){
                getlist.remove(list2.get(i));
            }
        }
        if (list2.size()>4){
            // 返回的list的数量翻页推荐 一页四个？
        }
        return getlist;
    }

    /**
     * 获取推荐的电影id内容
     */
    @RequestMapping("/resultAnalyer")
    public JsonMode getfavermovie(JsonMode jm,HttpSession session){
        List<String> list = getuserAnalyer(session);
        List<movie> movie = getMovieBiz.getpermovie(list);
        if (list == null){
            jm.setCode(0);
        }else {
            jm.setCode(1);
            jm.setData(movie);
        }
        return jm;
    }
}
