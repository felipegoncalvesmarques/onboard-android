package com.example.taqtile.onboard_android;

import android.app.Fragment;
import android.graphics.drawable.Drawable;

/**
 * Created by taqtile on 1/6/17.
 */

public class Option {
    Drawable icon;
    String name;
    Fragment fragment;

    public Option(Drawable icon, String name, Fragment fragment) {
        this.icon = icon;
        this.name = name;
        this.fragment = fragment;
    }
}
