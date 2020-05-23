package cn.siques.weibocrawler.pattern;

import cn.siques.weibocrawler.pattern.ActionFactory;
import cn.siques.weibocrawler.pattern.Impl.CommentPattern;
import cn.siques.weibocrawler.pattern.Impl.LoginPattern;
import cn.siques.weibocrawler.pattern.Impl.ScrollPattern;
import org.openqa.selenium.chrome.ChromeDriver;

public class PatternBuilder  {
    public ChromeDriver driver;

    public PatternBuilder(ChromeDriver driver){
        this.driver = driver;
    }
    public void build(){
       ActionFactory actionFactory = new ActionFactory();
       actionFactory.addPattern(new LoginPattern(driver)).addPattern(new ScrollPattern(driver)).addPattern(new CommentPattern(driver));

       actionFactory.run();
   }

}
