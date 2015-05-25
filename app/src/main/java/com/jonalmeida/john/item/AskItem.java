package com.jonalmeida.john.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/// Json data structure view:
// {
//         "by" : "tel",
//         "descendants" : 16,
//         "id" : 121003,
//         "kids" : [ 121016, 121109, 121168 ],
//         "score" : 25,
//         "text" : "<i>or</i> HN: the Next Iteration<p>I get the impression that with Arc..",
//         "time" : 1203647620,
//         "title" : "Ask HN: The Arc Effect",
//         "type" : "story",
//         "url" : ""
// }

public class AskItem extends Item implements Parcelable {

    protected String by;
    protected int descendants;
    protected List<Integer> kids;
    protected int score;
    protected String text;
    protected int time;
    protected String title;
    protected String url;

    public AskItem() {
        super();
    }

    public AskItem(int id) {
        super(id);
    }

    public AskItem(String id) {
        super(id);
    }

    private AskItem(Parcel in) {
        this.by = in.readString();
        this.descendants = in.readInt();
        this.id = in.readInt();
        in.readList(kids, ClassLoader.getSystemClassLoader());
        this.score = in.readInt();
        this.text = in.readString();
        this.time = in.readInt();
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    @Override
    public void updateThis(Item i) {
        AskItem item = (AskItem) i;
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
        parcel.writeList(this.kids);
        parcel.writeInt(this.score);
        parcel.writeString(this.text);
        parcel.writeInt(this.time);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeString(this.url);
    }

    public static final Parcelable.Creator<AskItem> CREATOR = new Creator<AskItem>() {
        @Override
        public AskItem createFromParcel(Parcel parcel) {
            return new AskItem(parcel);
        }

        @Override
        public AskItem[] newArray(int i) {
            return new AskItem[0];
        }
    };

    public String getBy() {
        return by;
    }

    public int getDescendants() {
        return descendants;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public int getScore() {
        return score;
    }

    public String getText() {
        return text;
    }

    public int getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

}
