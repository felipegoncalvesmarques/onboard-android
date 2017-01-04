package com.example.taqtile.onboard_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements UsersAdapter.UserClickListener {

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
        mAdapter.setListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        usersList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        usersList.setAdapter(mAdapter);
        usersList.setLayoutManager(mLayoutManager);
        usersList.setItemAnimator(new DefaultItemAnimator());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersService usersService = retrofit.create(UsersService.class);
        Call<UsersWrapper> call = usersService.listUsers(0);
        Log.d("MainActivity.onCreate", usersService.toString());
        call.enqueue(new Callback<UsersWrapper>() {
            @Override
            public void onResponse(Call<UsersWrapper> call, Response<UsersWrapper> response) {
                Log.d("MainActivity.onCreate", Integer.toString(response.body().data.size()));
                users.addAll(response.body().data);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UsersWrapper> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Wasn`t possible to get users", Toast.LENGTH_LONG).show();
            }
        });
        Log.d("MainActivity.onCreate", "Prepared Data");
    }

    @Override
    public void onUserClick(User user) {
        Intent intent = new Intent(this, DetailActivity.class);
        Log.d("MainActivity.intent", user.getAvatar());
        intent.putExtra("userId", user.getId());
        intent.putExtra("userFirstName", user.getFirstName());
        intent.putExtra("userLastName", user.getLastName());
        intent.putExtra("userAvatar", user.getAvatar());
        startActivity(intent);
    }
}
