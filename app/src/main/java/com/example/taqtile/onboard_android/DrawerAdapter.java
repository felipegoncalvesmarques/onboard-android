package com.example.taqtile.onboard_android;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 1/6/17.
 */

public class DrawerAdapter extends RecyclerView.Adapter<BasicViewHolder> {

    private static int HEADER_VIEW_TYPE = 0;
    private static int OPTION_VIEW_TYPE = 1;
    private List<Object> models;
    private OptionClickListener modelClickListener;
    private OptionViewHolder selectedView =  null;

    public interface OptionClickListener {
        void onOptionClick(Option model, int position);
    }


    public DrawerAdapter(List<Object> models, OptionClickListener modelClickListener) {
        this.models = models;
        this.modelClickListener = modelClickListener;
    }
    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW_TYPE) return HeaderViewHolder.createHeaderViewHolder(parent);
        else if (viewType == OPTION_VIEW_TYPE) return OptionViewHolder.createOptionViewHolder(parent);
        else return null;
    }

    @Override
    public void onBindViewHolder(final BasicViewHolder holder, final int position) {
        final Object model = models.get(position);
        holder.onBindViewHolder(model);

        if (model instanceof Option) {
            final OptionViewHolder optionHolder = (OptionViewHolder) holder;
            optionHolder.root.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (selectedView != null) selectedView.root.setSelected(false);
                    selectedView = optionHolder;
                    if (modelClickListener != null)
                        modelClickListener.onOptionClick((Option) model, position);
                    selectedView.root.setSelected(true);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object model = models.get(position);
        if (model instanceof String) return HEADER_VIEW_TYPE;
        else if (model instanceof Option) return OPTION_VIEW_TYPE;
        else return -1;
    }
}
