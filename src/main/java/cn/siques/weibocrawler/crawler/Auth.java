package cn.siques.weibocrawler.crawler;


import cn.siques.weibocrawler.pattern.PatternBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Auth {


    public String driverDir="src/main/resources/static/phantomjs-2.1.1-windows/bin/chromedriver77.exe";

    public ChromeDriver init(){
        System.setProperty("webdriver.chrome.driver",
                driverDir);

        ChromeOptions options = new ChromeOptions();

        options.setAcceptInsecureCerts(true);

//        options.setHeadless(true);

//        options.setProxy(seleniumProxy);

//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
// 忽略不可信证书错误。
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--window-size=1920,1080");
//// 启动就最大化
        options.addArguments("--start-maximized");
//// 禁止默认浏览器检查
        options.addArguments("no-default-browser-check");
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--disable-icon-ntp");
        options.addArguments("--disable-ntp-favicons");
        options.addArguments("--no-sandbox");

        options.addArguments("--enable-javascript");
// 设置用户代理
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36";
        options.addArguments(userAgent);

        ChromeDriver driver = new ChromeDriver(options);
        return  driver;
    }

    public void start() throws InterruptedException {
        ChromeDriver driver = init();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://weibo.com/u/5368257713?profile_ftype=1&is_ori=1#_0");

        Thread.sleep(2000);
        driver.get("https://weibo.com/u/5368257713?profile_ftype=1&is_ori=1#_0");


        PatternBuilder patternBuilder = new PatternBuilder(driver);
        patternBuilder.build();


            // 验证码识别相关
//            List<Selectable> nodes = driver.findElement(By.xpath("//textarea[@node-type='textEl']")).
//            while(driver.findElement(By.xpath("//a[@class='code W_fl']/img")).getAttribute("src")=="about:blank"){
//
//            }
//            String src = driver.findElement(By.xpath("//a[@class='code W_fl']/img")).getAttribute("src");
//            URL url = new URL(src);
//            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            InputStream inputStream = urlConnection.getInputStream();
//
//            BufferedImage read = ImageIO.read(inputStream);
//            ITesseract instance = new Tesseract();
//            instance.setLanguage("eng");
//            instance.doOCR(read);
//
//            urlConnection.disconnect();
//            String pageSource = driver.getPageSource();

            Set<Cookie> cookies = driver.manage().getCookies();

//            inputStream.close();


    }
}
