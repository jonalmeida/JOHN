package com.jonalmeida.john;

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
