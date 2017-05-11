package cn.insight.crawler.util;

/**
 * Created by zqykj on 2017/5/5.
 */
public class StringUtils {

    public static String convertUrl(String url, String ori, String dest) {
        return url.replaceFirst(ori, dest);
    }

    /**
     * 取名字的姓氏
     *
     * @param name
     * @return
     */
    public static String getSurName(String name) {
        String surname;
        int len = name.length();
        if (len <= 3) {
            surname = name.substring(0, 1);
        } else {
            surname = name.substring(0, 2);
        }
        return surname;
    }
}
