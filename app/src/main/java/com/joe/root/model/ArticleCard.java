package com.joe.root.model;


import com.joe.root.feed.FeedPartDefine;

import java.util.List;

/**
 * Created by JoeZ on 2015/10/26.
 */
public class ArticleCard implements Card{
    private String type;
    private String author;
    private String title;
    private String subtitle;
    private Content content;

    @Override
    public String getType() {
        return type;
    }

    public static String getTypeCode(){
        return "article";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

   public String getBody() {
        String result = null;
        if(content != null){
            result = content.getBody();
        }
        return result;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    private class Content{
        private String body;

        public Content(){

        }
        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
