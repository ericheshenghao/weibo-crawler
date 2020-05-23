package cn.siques.weibocrawler;

import cn.siques.weibocrawler.crawler.Auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeiboCrawlerApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WeiboCrawlerApplication.class, args);
        Auth auth = new Auth();
        auth.start();


    }

}
