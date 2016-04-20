package com.example.zjp.litepaldemo.model;

import org.litepal.crud.DataSupport;

/**
 * Created by zjp on 16-4-12.
 */
public class News extends DataSupport {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
