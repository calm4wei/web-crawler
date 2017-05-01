package cn.insight.crawler.job.task;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.insight.crawler.job.entity.Position;
import cn.insight.crawler.job.service.PositionService;
import cn.insight.crawler.util.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by fengwei on 17/5/1.
 */
public class LagouCrawler extends BreadthCrawler {

    PositionService positionService = new PositionService();

    public LagouCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        int maxPn = 15;
        for (int pn = 1; pn <= maxPn; pn++) {
            // 注意 url 的去重特性
            CrawlDatum crawlDatum = new CrawlDatum("https://www.lagou.com/jobs/positionAjax.json?" +
                    "city=%E5%8D%97%E4%BA%AC&needAddtionalResult=false" +
                    "&pn=" + pn);
            crawlDatum.meta("method", "POST")
                    .meta("first", "false")
                    .meta("pn", pn + "")
                    .meta("kd", "大数据");
            addSeed(crawlDatum);
        }
    }

    public void visit(Page page, CrawlDatums next) {
        String content = page.html();
        System.out.println("pn=" + page.meta("pn") + " , kd=" + page.meta("kd") + " , key=" + page.key());
        parseJson(page);
    }

    public void parseJson(Page page) {
        String jsonString = page.html();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject positionResult = jsonObject.getJSONObject("content")
                .getJSONObject("positionResult");
//        System.out.println("totalCount=" + positionResult.getInt("totalCount"));
//        System.out.println("resultSize=" + positionResult.getInt("resultSize"));
        JSONArray jsonArray = positionResult.getJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            //System.out.println(jsonArray.getJSONObject(i));
            JSONObject json = jsonArray.getJSONObject(i);
            Position p = new Position();
            p.setData_source(2);
            p.setSource_url(page.url());
            p.setCompany(json.getString("companyFullName"));
            p.setScale(json.getString("companySize"));
            p.setSalary(json.getString("salary"));
            p.setCity(json.getString("city"));
            p.setEdu(json.getString("education"));
            p.setPublish_date(DateUtils.formatDate(json.getString("createTime")));
            p.setExp(json.getString("workYear"));
            p.setWelfare(json.getString("positionAdvantage"));
            p.setPosition(json.getString("positionName"));
            p.setIndustry(json.getString("industryField"));

            System.out.println(p);
            positionService.insertPosition(p);
        }

    }

    @Override
    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum.url());
        //通过下面方式可以设置Cookie、User-Agent等http请求头信息
        request.setCookie("LGUID=20170121034354-c6c6ea64-df48-11e6-b6d8-525400f775ce; user_trace_token=20170121034354-d10939493522471fab24136b9378465b; index_location_city=%E6%B7%B1%E5%9C%B3; UM_distinctid=15b9e9a6d2a6dc-0c711d36229dd8-1d366f53-13c680-15b9e9a6d2b8d7; CNZZDATA1261666818=679676448-1493014114-%7C1493014114; JSESSIONID=987DB1A81EFCF8472F629654139CC915; _gat=1; LGSID=20170501132808-f5f2ced6-2e2e-11e7-b43c-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2Flist_%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE%3Fcity%3D%25E5%258D%2597%25E4%25BA%25AC%26cl%3Dfalse%26fromSearch%3Dtrue%26labelWords%3D%26suginput%3D; LGRID=20170501132808-f5f2d0e2-2e2e-11e7-b43c-5254005c3644; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1492668875,1493610340; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1493616489; _ga=GA1.2.2060883447.1484941434; TG-TRACK-CODE=search_code; SEARCH_ID=53ddf849f75f4e60a1fb742785714d22");
        request.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36");
        request.setMethod("POST");

        return request.response();
    }

    public static void main(String[] args) {
        LagouCrawler crawler = new LagouCrawler("crawl/lagou", true);
//        int maxPn = 3;
//        for (int pn = 1; pn <= maxPn; pn++) {
//            System.out.println("pageNum=" + pn);
//            crawler.addSeed(
//                    new CrawlDatum("https://www.lagou.com/jobs/positionAjax.json?city=%E5%8D%97%E4%BA%AC")
//                            .meta("method", "POST")
//                            //.meta("first", "true")
//                            .meta("pn", pn + "")
//                            .meta("kd", "大数据")
//            );
//        }

        try {
            System.out.println("size=" + crawler.seeds.size());
            crawler.setThreads(3);
            crawler.start(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
