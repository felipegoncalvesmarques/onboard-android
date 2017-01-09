package com.example.taqtile.onboard_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 1/9/17.
 */

public class OptionViewHolder extends BasicViewHolder {

    public static final int LAYOUT = R.layout.drawer_option;
    @Bind(R.id.drawer_option_id)
    public LinearLayout root;
    @Bind(R.id.option_name_id)
    public TextView optionName;
    @Bind(R.id.option_icon_id)
    public ImageView optionIcon;

    public static OptionViewHolder createOptionViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(LAYOUT, parent, false);
        return new OptionViewHolder(itemView);
    }

    public OptionViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindElements(View itemView) {
        optionName = (TextView) itemView.findViewById(R.id.option_name_id);
        optionIcon = (ImageView) itemView.findViewById(R.id.option_icon_id);
        root = (LinearLayout) itemView.findViewById(R.id.drawer_option_id);
    }

    @Override
    public void onBindViewHolder(Object model) {
        if (model instanceof Option) {
            Option option = (Option) model;
            optionName.setText(option.name);
            optionIcon.setImageDrawable(option.icon);
        } else {
            throw new IllegalArgumentException("The model should be an Option, but it is a "
                                                + model.getClass().getName());
        }
    }
}
