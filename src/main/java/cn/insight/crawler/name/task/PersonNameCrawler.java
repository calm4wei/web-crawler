package cn.insight.crawler.name.task;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import cn.insight.crawler.name.entity.NameEntity;
import cn.insight.crawler.name.service.NameService;
import cn.insight.crawler.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zqykj on 2017/5/5.
 */
public class PersonNameCrawler extends RamCrawler {

    private static Logger logger = LoggerFactory.getLogger(PersonNameCrawler.class);

    NameService nameService = new NameService();

    public void visit(Page page, CrawlDatums next) {
        String type = page.meta("type");
        if (type.equals("list")) {
            Links links = page.getLinks("body > div.main_ > div > div > div:nth-child(3) > a");
            for (int i = 0; i < links.size(); i++) {
                String link = links.get(i);
                String boyUrl = StringUtils.convertUrl(link, "_list", "/boys");
                String girlUrl = StringUtils.convertUrl(link, "_list", "/girls");
                Links boyAndGirlLinks = new Links();
                boyAndGirlLinks.add(girlUrl);
                CrawlDatum crawlBoy = new CrawlDatum(boyUrl)
                        .meta("type", "names").meta("sex", "男");
                CrawlDatum crawlGirl = new CrawlDatum(girlUrl)
                        .meta("type", "names").meta("sex", "女");
                next.add(crawlBoy);
                next.add(crawlGirl);
            }
        } else if (type.equals("names")) {
            String sex = page.meta("sex");
            System.out.println(sex);
            String names = page.select("body > div.main_ > div:nth-child(3) > div:nth-child(1) > div > a").text();
            String[] namesArr = names.split(" ");
            for (String name : namesArr) {
                NameEntity nameEntity = new NameEntity();
                nameEntity.setSex(sex);
                nameEntity.setName(name);
                nameEntity.setSurname(StringUtils.getSurName(name));
                //System.out.println(nameEntity);
                //logger.debug(nameEntity.toString());
                nameService.insertOne(nameEntity);
            }
        }

    }

    public static void main(String[] args) {
        PersonNameCrawler crawler = new PersonNameCrawler();
        CrawlDatum seed = new CrawlDatum("http://www.resgain.net/xmdq.html").meta("type", "list");
        crawler.addSeed(seed);
        crawler.setThreads(15);
        try {
            crawler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
