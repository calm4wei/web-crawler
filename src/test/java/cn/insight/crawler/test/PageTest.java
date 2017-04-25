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

    @Test
    public void test_str_space(){
        String natures = "  民营公司   |  150-500人   |  互联网/电子商务,金融/投资/证券";
        natures = natures.replaceAll(" *", "");
        System.out.println(natures);
    }
}
