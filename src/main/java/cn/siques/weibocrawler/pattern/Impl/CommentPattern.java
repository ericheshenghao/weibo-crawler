package cn.siques.weibocrawler.pattern.Impl;

import cn.siques.weibocrawler.pattern.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommentPattern extends Pattern {
    public CommentPattern(ChromeDriver driver) {
        super(driver);
    }

    @Override
    public void action() throws InterruptedException {
        //
        System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐评论模块开始时间"+System.currentTimeMillis());

        // 评论
        Page page = new Page();
        page.setRawText(driver.getPageSource());
        Request request = new Request("https://weibo.com/u/5368257713?profile_ftype=1&is_ori=1#_0");
        page.setRequest(request);


        List<Selectable> nodes = page.getHtml().xpath("//div[@class='WB_cardwrap WB_feed_type S_bg2 WB_feed_like']").nodes();

        int i =0;
        for (Selectable s:nodes
        ) {
            // 自动评论
            String a = s.xpath("//a[@action-type='fl_comment']").$("a", "suda-uatrack").toString();
            String b = s.xpath("//a[@action-type='fl_like']").$("a","action-data").toString();
            String isliked = s.xpath("//a[@action-type='fl_like']").$("a","title").toString();

            String x = "//a[@suda-uatrack='"+a+"']";
            String y = "//a[@action-data='"+b+"']";

            driver.findElement(By.xpath(x)).click();
            Thread.sleep(2000);
            if("赞".equals(isliked)){
                // 自动点赞
                driver.findElement(By.xpath(y)).click();
            }

            Thread.sleep(2000);

            driver.findElements(By.xpath("//textarea[@action-type='check']")).get(i).sendKeys("爱小猪猪");
            Thread.sleep(2000);
//                driver.findElements(By.xpath("//a[@action-type='post']")).get(i).click();


            i++;
        }
    }
}
