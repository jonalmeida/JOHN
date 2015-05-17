package com.jonalmeida.john;

import java.io.Serializable;

public class NewsItem implements Serializable {
    protected int id;
    protected String title;
    protected String url;
    protected int upvoteCount;
    protected String author;
    protected int commentCount;

    public NewsItem(String title, String url, int upvoteCount, String author, int commentCount) {
        this.title = title;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.author = author;
        this.commentCount = commentCount;
    }
}
