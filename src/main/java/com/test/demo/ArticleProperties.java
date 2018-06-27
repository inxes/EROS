package com.test.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "article")
public class ArticleProperties {

    private String Config;

    private String Array;

    private Integer Size;

    public String getConfig() {
        return Config;
    }

    public String getArray() {
        return Array;
    }

    public Integer getSize() {
        return Size;
    }

    public void setSize(Integer size) {
        Size = size;
    }

    public void setConfig(String articleConfig) {
        this.Config = articleConfig;
    }

    public void setArray(String articleArray) {
        this.Array = articleArray;
    }
}
