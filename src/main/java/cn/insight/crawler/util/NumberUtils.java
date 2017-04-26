package cn.insight.crawler.util;

/**
 * Created by fengwei on 17/4/26.
 */
public class NumberUtils {

    public static String wanToQianUnit(String wan) {
        String qian;
        if (wan.contains(".")) {
            if (wan.charAt(0) == '0') {
                qian = wan.replaceAll("0\\.", "");
            } else {
                qian = wan.replaceAll("\\.", "");
            }
        } else {
            qian = wan + "0";
        }
        return qian;
    }

}
