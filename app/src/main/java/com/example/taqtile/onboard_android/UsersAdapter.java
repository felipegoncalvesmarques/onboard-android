package com.example.taqtile.onboard_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by taqtile on 1/4/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<User> users;
    UserClickListener userClickListener;

    public interface UserClickListener {
        void onUserClick(User user);
    }

    public void setListener(UserClickListener userClickListener) {
        this.userClickListener = userClickListener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name;
        LinearLayout root;

        public UserViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.userId);
            name = (TextView) view.findViewById(R.id.userName);
            root = (LinearLayout) view.findViewById(R.id.user_cel_root);

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
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        User user = users.get(position);
        holder.id.setText("ID: " + user.getId());
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userClickListener != null) userClickListener.onUserClick(users.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
