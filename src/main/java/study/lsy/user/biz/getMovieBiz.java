package study.lsy.user.biz;

import study.lsy.bean.lable;
import study.lsy.bean.movie;
import study.lsy.bean.uscore;

import java.util.List;

public interface getMovieBiz {
    public List<movie> getallmovie();
    public List<uscore> getscore();
    public List<movie> getmovieIpage(int pagename,int size,String mname,String mlable);
    public List<lable> getoptions();
    public int getmovie(String label,String mname);
    public List<lable> getalllable();
    public List<movie> getmoviebyname(String name,String style,String local,String year);
    public int getallcount(String name, String style, String local, String year);
    public List<movie> getperfectmovie();
    public List<movie> getthreelabel(String label1,String label2,String label3);
    public List<movie> getpermovie(List<String> list);
    public List<movie> getweekmovie();
    public List<movie> getmoviebyid(String id);
}
