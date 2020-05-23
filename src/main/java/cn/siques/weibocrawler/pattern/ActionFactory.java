package cn.siques.weibocrawler.pattern;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
     List<Pattern> patternList = new ArrayList<>();

    public ActionFactory addPattern(Pattern pattern) {
        System.out.println(pattern);
        patternList.add(pattern);

        return this;
    }

    public void run(){
        for (Pattern p:patternList
             ) {
            try {
                p.action();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
