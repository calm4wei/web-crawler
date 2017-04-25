package cn.insight.crawler.test;

import cn.insight.crawler.common.Constants;
import org.junit.Test;

/**
 * Created by fengwei on 17/4/25.
 */
public class PageTest {

    @Test
    public void test_page_type() {
        System.out.println(Constants.PageType.LIST);
        System.out.println(Constants.PageType.DETAIL);
    }
}
