package cn.insight.crawler.job;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

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
            System.out.println("detail=" + page.url());
        }
    }

    public static void main(String[] args) {
        Job51Crawler crawler = new Job51Crawler("crawl/51jobDB", true);
        crawler.addSeed("http://search.51job.com/list/070200,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html"
                , PageType.LIST.toString());
        crawler.addRegex("http://(search.51job.com/list/).*|(jobs.51job.com/).*");
        crawler.setThreads(10);
        try {
            crawler.start(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
