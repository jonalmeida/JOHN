package com.jonalmeida.john;

import java.io.Serializable;

public class StoryItem implements Serializable {
    protected int id;
    protected String title;
    protected String url;
    protected int upvoteCount;
    protected String author;
    protected int commentCount;

    public StoryItem() {
        this.id = -1;
        this.upvoteCount = -1;
        this.commentCount = -1;
    }

    public StoryItem(int id) {
        this.id = id;
        this.upvoteCount = -1;
        this.commentCount = -1;
    }

    public StoryItem(String id) {
        this.id = Integer.parseInt(id);
    }

    public StoryItem(String title, String url, int upvoteCount, String author, int commentCount) {
        this.title = title;
        this.url = url;
        this.upvoteCount = upvoteCount;
        this.author = author;
        this.commentCount = commentCount;
    }
}
