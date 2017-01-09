package com.example.taqtile.onboard_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 1/9/17.
 */

public class HeaderViewHolder extends BasicViewHolder {

    public static final int LAYOUT = R.layout.drawer_header;
    @Bind(R.id.drawer_header_id)
    public TextView root;


    public static HeaderViewHolder createHeaderViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(LAYOUT, parent, false);
        return new HeaderViewHolder(itemView);
    }

    public HeaderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindElements(View itemView) {
        root = (TextView) itemView.findViewById(R.id.drawer_header_id);
    }

    @Override
    public void onBindViewHolder(Object model) {
        if (model instanceof String) {
            root.setText((String) model);
        } else {
            throw new IllegalArgumentException("The model should be an String, but it is a "
                    + model.getClass().getName());
        }
    }
}
