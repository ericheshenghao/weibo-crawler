package cn.siques.weibocrawler.pattern;

import org.openqa.selenium.chrome.ChromeDriver;

abstract public class Pattern {
   public ChromeDriver driver;

    public Pattern(ChromeDriver driver) {
        this.driver = driver;
    }

    public abstract void action() throws InterruptedException;

}
