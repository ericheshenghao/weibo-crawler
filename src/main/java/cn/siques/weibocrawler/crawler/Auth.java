package cn.siques.weibocrawler.crawler;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.catalina.filters.CsrfPreventionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.net.www.http.HttpClient;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Auth {
    private static String USERNAE = "17855832194";
    private static String PASSWORD = "heshenghao0.0";

    public String driverDir="src/main/resources/static/phantomjs-2.1.1-windows/bin/chromedriver77.exe";

    public void login(){
//        DesiredCapabilities dcaps = new DesiredCapabilities();
//        // ssl证书支持
//        dcaps.setCapability("acceptSslCerts", true);
//        // css搜索支持
//        dcaps.setCapability("cssSelectorsEnabled", true);
////        // js支持
//        dcaps.setJavascriptEnabled(true);
//
//        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                driverDir);
//        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        System.setProperty("webdriver.chrome.driver",
                driverDir);

        ChromeOptions options = new ChromeOptions();

        options.setAcceptInsecureCerts(true);

//        options.setHeadless(true);

//        options.setProxy(seleniumProxy);

//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
//// 忽略不可信证书错误。
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
        try{
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.get("https://weibo.com/u/5368257713?profile_ftype=1&is_ori=1#_0");

            Thread.sleep(2000);
            driver.get("https://weibo.com/u/5368257713?profile_ftype=1&is_ori=1#_0");



//      登陆相关
            Thread.sleep(15000);

            driver.findElement(By.xpath("//a[@node-type='loginBtn']")).click();
            Thread.sleep(500);
            driver.findElement(By.name("username")).sendKeys(USERNAE);
            Thread.sleep(1000);
            driver.findElement(By.name("password")).sendKeys(PASSWORD);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[@node-type='submitBtn']")).click();
//            // 登陆完成
            Thread.sleep(10000);
        // 向下翻页
           String jsCode = "document.documentElement.scrollTop=100000";

            while(driver.findElements(By.xpath("//a[@class='page next S_txt1 S_line1']")).isEmpty()){
                driver.executeScript(jsCode);
                Thread.sleep(3000);
            }

            driver.executeScript("document.documentElement.scrollTop=0");
            Thread.sleep(10000);

            Page page = new Page();
            page.setRawText(driver.getPageSource());
            Request request = new Request("https://weibo.com/u/5329121551?profile_ftype=1&is_ori=1#_0");
            page.setRequest(request);


            List<Selectable> nodes = page.getHtml().xpath("//div[@class='WB_cardwrap WB_feed_type S_bg2 WB_feed_like']").nodes();

            int i =0;
            for (Selectable s:nodes
                 ) {
                // 自动评论
                String a = s.xpath("//a[@action-type='fl_comment']").$("a", "suda-uatrack").toString();
                String b = s.xpath("//a[@action-type='fl_like']").$("a","action-data").toString();
                String isliked = s.xpath("//a[@action-type='fl_like']").$("a","title").toString();
                System.out.println(isliked);
                String x = "//a[@suda-uatrack='"+a+"']";
                String y = "//a[@action-data='"+b+"']";

                driver.findElement(By.xpath(x)).click();
                Thread.sleep(2500);
                if("赞".equals(isliked)){
                    driver.findElement(By.xpath(y)).click();
                }

                Thread.sleep(2500);

                driver.findElements(By.xpath("//textarea[@action-type='check']")).get(i).sendKeys("爱小猪猪");
                Thread.sleep(1500);
//                driver.findElements(By.xpath("//a[@action-type='post']")).get(i).click();
                // 自动点赞

                i++;
            }

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
