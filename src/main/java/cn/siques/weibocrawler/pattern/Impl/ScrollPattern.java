package cn.siques.weibocrawler.pattern.Impl;

import cn.siques.weibocrawler.pattern.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ScrollPattern extends Pattern {
    public ScrollPattern(ChromeDriver driver) {
        super(driver);
    }

    @Override
   public void action() throws InterruptedException {
        System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐滚动模块开始时间"+System.currentTimeMillis());

        // 向下翻页
        String jsCode = "document.documentElement.scrollTop=100000";

        int retryTime=0;
        while(driver.findElements(By.xpath("//a[@class='page next S_txt1 S_line1']")).isEmpty()&&retryTime<3){
            driver.executeScript(jsCode);
            Thread.sleep(1000);
            retryTime++;
        }

        //回到顶部
        driver.executeScript("document.documentElement.scrollTop=0");
        Thread.sleep(4000);
    }
}
