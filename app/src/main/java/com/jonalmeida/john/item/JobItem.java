package com.jonalmeida.john.item;

import android.os.Parcel;
import android.os.Parcelable;

/// Json data structure view:
// {
//         "by" : "justin",
//         "id" : 192327,
//         "score" : 6,
//         "text" : "Justin.tv is the biggest live video site online. We serve hundreds..",
//         "time" : 1210981217,
//         "title" : "Justin.tv is looking for a Lead Flash Engineer!",
//         "type" : "job",
//         "url" : ""
// }

public class JobItem extends Item implements Parcelable {

    protected String by;
    protected int score;
    protected String text;
    protected int time;
    protected String title;
    protected String url;

    public JobItem() {
        super();
    }

    public JobItem(int i) {
        super(i);
    }

    public JobItem(String i) {
        super(i);
    }

    private JobItem(Parcel in) {
        this.by = in.readString();
        this.id = in.readInt();
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
        this.id = item.id;
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
        parcel.writeInt(this.id);
        parcel.writeInt(this.score);
        parcel.writeString(this.text);
        parcel.writeInt(this.time);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeString(this.url);
    }

    public static final Parcelable.Creator<JobItem> CREATOR = new Creator<JobItem>() {
        @Override
        public JobItem createFromParcel(Parcel parcel) {
            return new JobItem(parcel);
        }

        @Override
        public JobItem[] newArray(int i) {
            return new JobItem[0];
        }
    };

    public String getBy() {
        return by;
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
