package cn.siques.weibocrawler;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class WeiboCrawlerApplicationTests {

    @Test
    void contextLoads() throws TesseractException {
        ITesseract instance = new Tesseract();
        // chi_sim中文 eng英文

        File file = new File("src/main/resources/static/pin.png");
        instance.setDatapath("src/main/resources/tessdata");

//        instance.setPageSegMode(ITessAPI.TessPageSegMode.PSM_SINGLE_LINE);
        instance.setLanguage("eng");


        String s = instance.doOCR(file);
        System.out.println(s);
    }

}
