package com.jonalmeida.john.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.jonalmeida.john.Constants;

/**
 * Base class for all Item types that the Hacker News API will send back. To be inherited from.
 * <b>Remeber to update the {@code id} here as well!</b>
 */
public abstract class Item implements Parcelable {
    protected int id;
    protected String type;

    public Item() {
        id = -1;
        type = Constants.ITEM_TYPE;
    }

    public Item(int id) {
        this.id = id;
        this.type = Constants.ITEM_TYPE;
    }

    public Item(String id) {
        this.id = Integer.parseInt(id);
        this.type = Constants.ITEM_TYPE;
    }

    public String toString() {
        return " id: " + id +
                " type: " + type;
    }

    public abstract void updateThis(Item i);

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
