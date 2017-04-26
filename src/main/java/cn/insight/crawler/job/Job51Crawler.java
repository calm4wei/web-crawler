package cn.insight.crawler.job;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.insight.crawler.job.entity.Position;
import cn.insight.crawler.util.NumberUtils;
import org.jsoup.nodes.Element;

import static cn.insight.crawler.common.Constants.*;

/**
 * Created by fengwei on 17/4/25.
 */
public class Job51Crawler extends BreadthCrawler {

    /**
     * 构造一个基于伯克利DB的爬虫
     * 伯克利DB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath 伯克利DB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public Job51Crawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    public void visit(Page page, CrawlDatums next) {
        if (page.matchUrl("http://search.51job.com/list/.*")) {
            Links links = page.getLinks("#resultList > div.el > p > span > a");
            next.add(links, PageType.DETAIL.toString());
            //System.out.println("links.size=" + links.size() + " , links=" + links);
        } else if (page.matchType(PageType.DETAIL.toString())) {
            //System.out.println("detail=" + page.url());
            detailAnalysis(page);
        }
    }

    public void detailAnalysis(Page page) {
        try {
            Position entity = new Position();

            entity.setSource_url(page.url());
            // 公司信息
            Element companyEls = page.select("div.tHeader.tHjob > div > div.cn").first();
            String position = companyEls.select("h1").first().attr("title");
            String location = companyEls.select("span.lname").first().text();
            String salary = companyEls.select("strong").first().text();
            String company = companyEls.select("p.cname > a").first().attr("title");
            String natures = companyEls.select(" p.msg.ltype").first().text();
            natures = natures.replaceAll(" *", "");
            String[] natureArr = natures.split("\\|");

            entity.setPosition(position);
            if (location.contains("-")) {
                location = location.substring(0, location.indexOf("-"));
            }

            entity.setCity(location);
            entity.setSalary(salaryConvert(salary));
            entity.setCompany(company);
            entity.setNature(natureArr[0]);
            entity.setScale(natureArr[1]);
            entity.setIndustry(natureArr[2]);

            // 发布日期
            Element publishInfo = page.select("div.tCompany_main > div.tBorderTop_box.bt > div").first();
            String publishDate = publishInfo.select("div > span").last().text();
            entity.setPublish_date(publishDate);

            // 岗位职责
            String duty = page.select("div.tCompany_main > div:nth-child(4) > div").first().text();
            entity.setDuty(duty);
            System.out.println(entity);
        } finally {

        }

    }

    public String salaryConvert(String salary) {
        if (salary.contains("/")) {
            String ss = salary.substring(0, salary.indexOf("/") - 1);
            if (ss.contains("-")) {
                String[] startEnd = ss.split("-");
                if (salary.contains(SalaryUnit.QIAN.toString())) {
                    salary = startEnd[0] + "k-" + startEnd[1] + "k";
                } else if (salary.contains(SalaryUnit.WAN.toString())) {
                    salary = NumberUtils.wanToQianUnit(startEnd[0])
                            + "k-"
                            + NumberUtils.wanToQianUnit(startEnd[1])
                            + "k";
                }
            }
        }
        return salary;
    }

    public static void main(String[] args) {
        Job51Crawler crawler = new Job51Crawler("crawl/51jobDB", true);
        crawler.addSeed("http://search.51job.com/list/070200,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html"
                , PageType.LIST.toString());
        crawler.addRegex("http://(search.51job.com/list/).*|(jobs.51job.com/).*");
        crawler.setThreads(10);
        try {
            crawler.start(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
