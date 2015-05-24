package com.jonalmeida.john.item;

import android.os.Parcel;
import android.os.Parcelable;

/// Json data structure view:
// {
//         "by" : "dhouston",
//         "descendants" : 71,
//         "id" : 8863,
//         "kids" : [ 8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005],
//         "score" : 111,
//         "time" : 1175714200,
//         "title" : "My YC app: Dropbox - Throw away your USB drive",
//         "type" : "story",
//         "url" : "http://www.getdropbox.com/u/2/screencast.html"
// }

public class StoryItem extends Item implements Parcelable {
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

    private StoryItem(Parcel in) {
        this.by = in.readString();
        this.descendants = in.readInt();
        this.id = in.readInt();
        in.readIntArray(kids);
        this.score = in.readInt();
        this.text = in.readString();
        this.time = in.readInt();
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    @Override
    public void updateThis(Item i) {
        StoryItem item = (StoryItem) i;
        this.by = item.by;
        this.descendants = item.descendants;
        this.id = item.id;
        this.kids = item.kids;
        this.score = item.score;
        this.text = item.text;
        this.time = item.time;
        this.title = item.title;
        this.type = item.type;
        this.url = item.url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.by);
        parcel.writeInt(this.descendants);
        parcel.writeInt(this.id);
        parcel.writeIntArray(this.kids);
        parcel.writeInt(this.score);
        parcel.writeString(this.text);
        parcel.writeInt(this.time);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeString(this.url);
    }

    public static final Parcelable.Creator<StoryItem> CREATOR = new Creator<StoryItem>() {
        @Override
        public StoryItem createFromParcel(Parcel parcel) {
            return new StoryItem(parcel);
        }

        @Override
        public StoryItem[] newArray(int i) {
            return new StoryItem[0];
        }
    };

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
