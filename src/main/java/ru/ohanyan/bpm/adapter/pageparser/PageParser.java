package ru.ohanyan.bpm.adapter.pageparser;

import java.io.IOException;

/**
 * todo Document type PageParser
 */
public interface PageParser {
    String getUrlContent(String url, String xpath) throws IOException;
}
