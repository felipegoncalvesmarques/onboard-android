package com.example.taqtile.onboard_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by taqtile on 1/4/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<User> users;


    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name;

        public UserViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.userId);
            name = (TextView) view.findViewById(R.id.userName);
        }
    }

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.user_cel, parent, false);
        return new UserViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.id.setText("ID: " + user.getId());
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
