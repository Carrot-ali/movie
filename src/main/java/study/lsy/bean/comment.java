package study.lsy.bean;

import lombok.Data;

@Data
public class comment {
    private int comid;
    private String comname;
    private String cowork;
    private String tips;
    private String coauthor;
    private String content;
    private String time;
    private String status;
    private int rate;
}
