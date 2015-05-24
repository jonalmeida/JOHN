package com.jonalmeida.john;

/**
 * Base class for all Item types that the Hacker News API will send back. To be inherited from.
 * <b>Remeber to update the {@code id} here as well!</b>
 */
public abstract class Item {
    protected int id;

    public Item() {
        id = -1;
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(String id) {
        this.id = Integer.parseInt(id);
    }

    public String toString() {
        return " id: " + id;
    }

    public abstract void updateThis(Item i);
}
