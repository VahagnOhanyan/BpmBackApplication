package ru.ohanyan.bpm.adapter.pageparser;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;

/**
 * todo Document type JsoupPageParser
 */
@Component
public class JsoupPageParser implements PageParser {
    @Override
    public String getUrlContent(String url, String xpath) throws IOException {
        var document = Jsoup.connect(url).get();
        return Xsoup.compile(xpath).evaluate(document).get();
    }


}
