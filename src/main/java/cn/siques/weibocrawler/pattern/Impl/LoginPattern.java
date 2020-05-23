package cn.siques.weibocrawler.pattern.Impl;

import cn.siques.weibocrawler.pattern.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPattern extends Pattern {

    private static String USERNAME = "17855832194";
    private static String PASSWORD = "heshenghao0.0";

    public LoginPattern(ChromeDriver driver) {

        super(driver);
    }

    @Override
    public void action() throws InterruptedException {
//      登陆相关
        System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐登陆模块开始时间"+System.currentTimeMillis());
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

         boolean enabled = false;

        while(!enabled){
            try {
                 enabled = driver.findElement(By.xpath("//a[@node-type='loginBtn']")).isEnabled();
            }catch (Exception e){}
        }

        driver.findElement(By.xpath("//a[@node-type='loginBtn']")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("username")).sendKeys(USERNAME);
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@node-type='submitBtn']")).click();
         // 登陆完成

    }


}
