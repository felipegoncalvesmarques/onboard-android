package com.example.taqtile.onboard_android;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity implements UsersAdapter.UserClickListener, Callback<UsersWrapper> {

    private RecyclerView usersList;
    private UsersAdapter mAdapter;
    private List<User> users = new ArrayList<User>();
    private UsersDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.onCreate", "Debug message from onCreate method at MainActivity");

        usersList = (RecyclerView) findViewById(R.id.usersList);

        Log.d("MainActivity.onCreate", "Got RecyclerView");

        mDbHelper = new UsersDbHelper(this);

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
        call.enqueue(this);
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

    @Override
    public void onResponse(Call<UsersWrapper> call, Response<UsersWrapper> response) {
        Log.d("MainActivity.onCreate", Integer.toString(response.body().data.size()));
        users.addAll(response.body().data);
        mAdapter.notifyDataSetChanged();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        for (int i = 0; i < users.size(); i++)  {
            User user = users.get(i);
            ContentValues values = new ContentValues();
            values.put(UserContract.Users._ID, user.getId());
            values.put(UserContract.Users.COLUMN_NAME_FIRST_NAME, user.getFirstName());
            values.put(UserContract.Users.COLUMN_NAME_LAST_NAME, user.getLastName());
            values.put(UserContract.Users.COLUMN_NAME_AVATAR, user.getAvatar());
            try {
                long newRowId = db.insertOrThrow(UserContract.Users.TABLE_NAME, null, values);
            } catch (SQLiteConstraintException e) {
                Log.d("MainActivity.oncreate",
                        "Wasn`t able to insert item because it is already in the database.");
            }
        }
        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                UserContract.Users._ID,
                UserContract.Users.COLUMN_NAME_FIRST_NAME,
                UserContract.Users.COLUMN_NAME_LAST_NAME,
                UserContract.Users.COLUMN_NAME_AVATAR
        };
        Cursor cursor = db.query(
                UserContract.Users.TABLE_NAME,
                projection,
                null, null, null, null, null
        );
        while (cursor.moveToNext()) {
            Log.d("MainActivity.onCreate", "ID: " + cursor.getInt(0) +
                    " First Name: " + cursor.getString(1) +
                    " Last Name: " + cursor.getString(2) +
                    " Avatar: " + cursor.getString(3));
        }
    }

    @Override
    public void onFailure(Call<UsersWrapper> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Wasn`t possible to get users", Toast.LENGTH_LONG).show();
    }
}
