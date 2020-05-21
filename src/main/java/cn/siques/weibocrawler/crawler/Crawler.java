package cn.siques.weibocrawler.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class Crawler implements PageProcessor {
    @Override
    public void process(Page page) {

    }

    private Site site= Site.me()
            .setCharset("utf8")  // 设置编码
            .setTimeOut(10000)   //设置超时时间
            .setRetrySleepTime(3000) //设置重试间隔时间
            .setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

}
