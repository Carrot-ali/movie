package study.lsy.bean;

import lombok.Data;

@Data
public class user {
    private String id;
    private String password;
    private String name;
    private int age;
    private String sculpture;
    private String postbox;
    private String lable;
    private String natives;

}

/**
 *   `id`  '用户id/账号',
 *   `password`  '密码',
 *   `name`  '用户昵称',
 *   `sculpture` '用户头像',
 *   `postbox`  '邮箱',
 *   `age`  '年龄',
 *   `lable`  '分割线’/’  用户兴趣标签',
 */
