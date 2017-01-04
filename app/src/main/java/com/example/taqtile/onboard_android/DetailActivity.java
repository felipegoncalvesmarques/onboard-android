package com.example.taqtile.onboard_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    private int id;
    private String firstName, lastName, avatar;
    private DetailElement idElement, firstNameElement, lastNameElement, avatarElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 1);
        firstName = intent.getStringExtra("userFirstName");
        lastName = intent.getStringExtra("userLastName");
        avatar = intent.getStringExtra("userAvatar");

        idElement = (DetailElement) findViewById(R.id.userIdElement);
        firstNameElement = (DetailElement) findViewById(R.id.firstNameElement);
        lastNameElement = (DetailElement) findViewById(R.id.lastNameElement);
        avatarElement = (DetailElement) findViewById(R.id.avatarElement);

        idElement.setValue(Integer.toString(id));
        firstNameElement.setValue(firstName);
        lastNameElement.setValue(lastName);
        avatarElement.setValue(avatar);
    }
}
