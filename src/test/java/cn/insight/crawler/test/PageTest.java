package cn.insight.crawler.test;

import cn.insight.crawler.common.Constants;
import cn.insight.crawler.job.Job51Crawler;
import cn.insight.crawler.util.NumberUtils;
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
    public void test_str_space() {
        String natures = "  民营公司   |  150-500人   |  互联网/电子商务,金融/投资/证券";
        natures = natures.replaceAll(" *", "");
        System.out.println(natures);
    }

    @Test
    public void test_str_sub() {
        String salary = "0.8-1.5万/月";
        String nums = salary.substring(0, salary.indexOf("/") - 1);
        String[] sarr = nums.split("-");
        String s = NumberUtils.wanToQianUnit(sarr[0]);
        String e = NumberUtils.wanToQianUnit(sarr[1]);
        System.out.println(s + "k-" + e + "k");

    }

    @Test
    public void test_str_qian() {
        String salary = "5-10千/月";
        System.out.println(Constants.SalaryUnit.QIAN.toString());
        System.out.println(salary.contains(Constants.SalaryUnit.QIAN.toString()));
        String nums = salary.substring(0, salary.indexOf("/") - 1);
        String[] sarr = nums.split("-");
        System.out.println(sarr[0] + "k-" + sarr[1] + "k");
    }

    @Test
    public void test_job51_convert() {
        Job51Crawler crawler = new Job51Crawler("", false);
        System.out.println(crawler.salaryConvert("5-10千/月"));
        System.out.println(crawler.salaryConvert("0.8-1.5万/月"));
        System.out.println(crawler.salaryConvert("0.8-0.9万/月"));
        System.out.println(crawler.salaryConvert("1.8-1.9万/月"));
        System.out.println(crawler.salaryConvert("10-20万/年"));
    }
}
