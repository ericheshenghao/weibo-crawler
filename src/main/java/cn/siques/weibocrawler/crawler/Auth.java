package cn.siques.weibocrawler.crawler;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.net.www.http.HttpClient;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
        options.addArguments("--window-size=1920,1080");
//// 启动就最大化
        options.addArguments("--start-maximized");
//// 禁止默认浏览器检查
        options.addArguments("no-default-browser-check");
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--disable-icon-ntp");
        options.addArguments("--disable-ntp-favicons");
        options.addArguments("--no-sandbox");
// 设置用户代理
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36";
        options.addArguments(userAgent);

        ChromeDriver driver = new ChromeDriver(options);
        try{
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.get("http://weibo.com/login.php");
//            Dimension dimension = new Dimension(1920, 1080);
//            driver.manage().window().setSize(dimension);
            while(!driver.findElement(By.id("loginname")).isEnabled()){

            }

            driver.findElement(By.id("loginname")).sendKeys(USERNAE);

            driver.findElement(By.name("password")).sendKeys(PASSWORD);

            driver.findElement(By.cssSelector("a.W_btn_a")).click();

            Thread.sleep(35000);
//            while(driver.findElement(By.xpath("//a[@class='code W_fl']/img")).getAttribute("src")=="about:blank"){
//
//            }
            String src = driver.findElement(By.xpath("//a[@class='code W_fl']/img")).getAttribute("src");
            URL url = new URL(src);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            BufferedImage read = ImageIO.read(inputStream);
            ITesseract instance = new Tesseract();
            instance.setLanguage("eng");
            instance.doOCR(read);

            urlConnection.disconnect();
            String pageSource = driver.getPageSource();


            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println(123);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
