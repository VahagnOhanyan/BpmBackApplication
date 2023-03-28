package ru.ohanyan.bpm.app.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ohanyan.bpm.adapter.pageparser.JsoupPageParser;
import ru.ohanyan.bpm.fw.BpmApplication;

/**
 * todo Document type JsoupPageParserTest
 */
@SpringBootTest(classes = BpmApplication.class)
@Slf4j
public class JsoupPageParserTest {

    @Test
        void name() throws Exception{
        JsoupPageParser parser = new JsoupPageParser();
        String result = parser.getUrlContent("https://www.cbr.ru/", "//div[@class='main-indicator_rate'][2]/div[contains(@class,'mono-num')][2]/text()");
        Assertions.assertEquals("58,3895 ₽ ",result);

        }
}
