package com.jonalmeida.john.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/// Json data structure view:
// {
//         "by" : "norvig",
//         "id" : 2921983,
//         "kids" : [ 2922097, 2922429, 2924562, 2922709, 2922573, 2922140, 2922141 ],
//         "parent" : 2921506,
//         "text" : "Aw shucks, guys ... you make me blush with your compliments.<p>Tell you..",
//         "time" : 1314211127,
//         "type" : "comment"
// }

public class CommentItem extends Item implements Parcelable {
    protected String by;
    protected int[] kids;
    protected int parent;
    protected String text;
    protected int time;
    protected String type;

    public CommentItem() {
        super();
    }

    public CommentItem(int id) {
        super(id);
    }

    public CommentItem(String id) {
        super(id);
    }

    private CommentItem(Parcel in) {
        this.by = in.readString();
        this.id = in.readInt();
        in.readIntArray(kids);
        this.parent = in.readInt();
        this.text = in.readString();
        this.time = in.readInt();
        this.type = in.readString();
    }

    @Override
    public void updateThis(Item i) {
        CommentItem item = (CommentItem) i;
        this.by = item.by;
        this.id = item.id;
        this.kids = item.kids;
        this.parent = item.parent;
        this.text = item.text;
        this.time = item.time;
        this.type = item.type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.by);
        parcel.writeInt(this.id);
        parcel.writeIntArray(this.kids);
        parcel.writeInt(this.parent);
        parcel.writeString(this.text);
        parcel.writeInt(this.time);
        parcel.writeString(this.type);
    }

    public static final Parcelable.Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel parcel) {
            return new CommentItem(parcel);
        }

        @Override
        public CommentItem[] newArray(int i) {
            return new CommentItem[0];
        }
    };

    public String getBy() {
        // More like hardlyGetBy(), amarite?!
        return by;
    }

    public int getId() {
        return super.id;
    }

    public int[] getKids() {
        return kids;
    }

    public int getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public int getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return " by: " + by +
                " kids: " + Arrays.toString(kids) +
                " parent: " + parent +
                " text: " + text +
                " time: " + time +
                " type: " + type +
                super.toString();
    }
}