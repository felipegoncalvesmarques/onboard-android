package com.example.taqtile.onboard_android;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by taqtile on 1/9/17.
 */

public abstract class BasicViewHolder extends RecyclerView.ViewHolder {


    public BasicViewHolder(View itemView) {
        super(itemView);
        bindElements(itemView);

    }

    public abstract void bindElements(View itemView);

    public abstract void onBindViewHolder(Object model);

}
