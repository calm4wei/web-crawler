package cn.insight.crawler.test;

import cn.insight.crawler.util.DateUtils;
import org.junit.Test;

/**
 * Created by fengwei on 17/5/1.
 */
public class UtilsTest {

    @Test
    public void test_date_complete() {
        System.out.println(DateUtils.completeDate("05-01发布"));
    }

    @Test
    public void test_get_year() {
        System.out.println(DateUtils.getYear());
    }

    @Test
    public void test_format_date() {
        System.out.println(DateUtils.formatDate("2017-04-30 17:23:27"));
    }

    @Test
    public void test_get_date() {
        System.out.println(DateUtils.getDate(-1, 1, 1));
        System.out.println(DateUtils.getDate(-10, 1, 1));
        System.out.println(DateUtils.getDate(-20, 1, 1));
    }
}
