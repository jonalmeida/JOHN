package com.jonalmeida.john;

import java.io.Serializable;
import java.util.Map;

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
public class StoryItem extends Item implements Serializable {
    protected String by;
    protected int descendants;
    protected int[] kids;
    protected int score;
    protected String text;
    protected int time;
    protected String title;
    protected String type;
    protected String url;

    public StoryItem() {
        super();
        this.score = -1;
        this.descendants = -1;
    }

    public StoryItem(int id) {
        super(id);
        this.score = -1;
        this.descendants = -1;
    }

    public StoryItem(String id) {
        super(id);
    }

    public StoryItem(String title, String url, int score, String by, int descendants) {
        this.title = title;
        this.url = url;
        this.score = score;
        this.by = by;
        this.descendants = descendants;
    }

    @Override
    public void updateThis(Item i) {
        StoryItem item = (StoryItem) i;
        this.id = item.id;
        this.by = item.by;
        this.descendants = item.descendants;
        this.kids = item.kids;
        this.score = item.score;
        this.text = item.text;
        this.time = item.time;
        this.title = item.title;
        this.type = item.type;
        this.url = item.url;
    }

    public String getBy() {
        return by;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getScore() {
        return score;
    }

    public int getDescendants() {
        return descendants;
    }

    public int getId() {
        return super.id;
    }

    public int[] getKids() {
        return kids;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getTime() {
        return time;
    }

    public String toString() {
        return " title: " + title +
                " url: " + url +
                " score: " + score +
                " by: "  + by +
                " descendants: " + descendants +
                super.toString();
    }

}
