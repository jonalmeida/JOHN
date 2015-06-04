package com.jonalmeida.john;

import android.graphics.drawable.Drawable;

public class NavItem {
    private String mText;
    private Drawable mDrawable;

    public NavItem(String text, Drawable drawable) {
        mText = text;
        mDrawable = drawable;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}
