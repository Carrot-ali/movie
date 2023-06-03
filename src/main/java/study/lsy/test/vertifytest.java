package study.lsy.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

public class vertifytest {
    public static void main(String[] args) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //LineCaptcha lineCaptcha = new LineCaptcha(200, 100, 4, 150);
        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.jpg");
    }
}
