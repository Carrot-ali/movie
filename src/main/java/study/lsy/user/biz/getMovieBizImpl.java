package study.lsy.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.lsy.bean.lable;
import study.lsy.bean.movie;
import study.lsy.bean.uscore;
import study.lsy.mapper.lableMapper;
import study.lsy.mapper.movieMapper;
import study.lsy.mapper.uscoreMapper;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class getMovieBizImpl implements getMovieBiz{
    @Autowired
    movieMapper movieMapper;
    @Autowired
    uscoreMapper uscoreMapper;
    @Autowired
    lableMapper lableMapper;
    /**
     * 获取所有的电影的信息
     * @return
     */
    @Override
    public List<movie> getallmovie() {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        return movieMapper.selectList(qw);
    }

    /**
     * 获取所有评分的信息
     * 后续可以优化到定时定点获取平均评分存入对应数据库
     * @return
     */
    @Override
    public List<uscore> getscore() {
        QueryWrapper<uscore> qw = new QueryWrapper<>();
        return uscoreMapper.selectList(qw);
    }


    public List<movie> getallgreate(){
        List<Map<String,Double>> list = new ArrayList<>();
        Map<String,Double> map = new HashMap<>();
        List<movie> movie = getallmovie();
        List<uscore> uscore = getscore();
        for (int i = 0 ; i<movie.size();i++){

        }
        return movie;
    }

    /**
     * 电影分页查询
     * @param pagename
     * @param size
     * @return
     */
    @Override
    public List<movie> getmovieIpage(int pagename, int size,String mname,String mlable) {
        if (mlable==null) mlable = "";
        if (mname==null) mname = "";
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.like("name",mname).like("mlable",mlable);
        qw.orderByAsc("id");
        //Pagename 第几页 size每页多少条
        IPage<movie> ipage=new Page<>(pagename,size);
        movieMapper.selectPage(ipage,qw);
        List<movie> itemList = ipage.getRecords();
        return itemList;
    }

    /**
     * 获取分类标签
     * @return
     */
    @Override
    public List<lable> getoptions() {
        QueryWrapper<lable> qw = new QueryWrapper<>();
        qw.eq("type","类型");
        return lableMapper.selectList(qw);
    }

    /**
     * 获取某一标签或电影名的电影总数
     * @param label
     * @param mname
     * @return
     */
    @Override
    public int getmovie(String label, String mname) {
        QueryWrapper<movie> qw=new QueryWrapper<>();
        qw.like("mlable",label).like("name",mname);
        qw.select("count(id) as id ");
        return movieMapper.selectOne(qw).getId();
    }

    /**
     * 获取所有标签
     * @return
     */
    @Override
    public List<lable> getalllable() {
        QueryWrapper<lable> qw = new QueryWrapper<>();
        return lableMapper.selectList(qw);
    }

    /**
     * 获取对应标签的电影
     * @param name
     * @param style
     * @param local
     * @param year
     * @return
     */
    @Override
    public List<movie> getmoviebyname(String name, String style, String local, String year) {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        if (style.equals("全部")) style="";
        if (local.equals("全部")) local="";
        if (year.equals("全部")) year="";
        if (year.equals("70年代")) year = "70";
        if (year.equals("80年代")) year = "80";
        if (year.equals("90年代")) year = "90";
        if (name==null) name="";
        qw.like("name",name);
        qw.like("mlocal",local);
        qw.like("time",year);
        qw.like("mlable",style);
        List<movie> list = movieMapper.selectList(qw);
        return list;
    }
    public int getallcount(String name, String style, String local, String year) {
        QueryWrapper<movie> qw=new QueryWrapper<>();
        if (style.equals("全部")) style="";
        if (local.equals("全部")) local="";
        if (year.equals("全部")) year="";
        if (year.equals("70年代")) year = "70";
        if (year.equals("80年代")) year = "80";
        if (year.equals("90年代")) year = "90";
        if (name==null) name="";
        qw.like("name",name).like("mlocal",local)
                .like("time",year).like("mlable",style);
        qw.select("count(id) as id ");
        return movieMapper.selectOne(qw).getId();
    }

    /**
     * 获取评分最高的四部电影到首页
     * @return
     */
    @Override
    public List<movie> getperfectmovie() {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.orderByDesc("rate");
        qw.last("limit 4");
        return movieMapper.selectList(qw);
    }

    /**
     * 获取三个标签的电影推荐
     * 喜剧 悬疑 动作
     * @return
     */
    @Override
    public List<movie> getthreelabel(String label1,String label2,String label3) {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.like("mlable",label1).or().like("mlable",label2)
                .or().like("mlable",label3);
        qw.orderByDesc("rate");
        return movieMapper.selectList(qw);
    }

    /**
     * 获取推荐列表中的电影
     * @param list
     * @return
     */
    @Override
    public List<movie> getpermovie(List<String> list) {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.in("id",list);
        qw.last("limit 4");
        return movieMapper.selectList(qw);
    }

    @Override
    public List<movie> getweekmovie() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.between("time",day,new Date());
        qw.orderByDesc("rate");
        qw.last("limit 10");
        return movieMapper.selectList(qw);
    }

    @Override
    public List<movie> getmoviebyid(String id) {
        QueryWrapper<movie> qw = new QueryWrapper<>();
        qw.eq("id",id);
        return movieMapper.selectList(qw);
    }
}
