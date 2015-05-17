package com.jonalmeida.john;

import java.io.Serializable;
/// To implement:
// {
//         "by" : "dhouston",
//         "descendants" : 71,
//         "id" : 8863,
//         "kids" : [ 8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005, 9671, 8940, 9067, 8908, 9055, 8865, 8881, 8872, 8873, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8878, 8870, 8980, 8934, 8876 ],
//         "score" : 111,
//         "time" : 1175714200,
//         "title" : "My YC app: Dropbox - Throw away your USB drive",
//         "type" : "story",
//         "url" : "http://www.getdropbox.com/u/2/screencast.html"
// }
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
