package com.example.taqtile.onboard_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView usersList;
    private UsersAdapter mAdapter;
    private List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.onCreate", "Debug message from onCreate method at MainActivity");

        usersList = (RecyclerView) findViewById(R.id.usersList);

        Log.d("MainActivity.onCreate", "Got RecyclerView");

        mAdapter = new UsersAdapter(users);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        usersList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        usersList.setAdapter(mAdapter);
        usersList.setLayoutManager(mLayoutManager);
        usersList.setItemAnimator(new DefaultItemAnimator());

        prepareUserData();
        Log.d("MainActivity.onCreate", "Prepared Data");
    }

    private void prepareUserData() {
        users.add(new User(1, "george", "bluth", "avatar"));
        users.add(new User(2, "lucille", "bluth", "avatar"));
        users.add(new User(3, "oscar", "bluth", "avatar"));
    }
}
