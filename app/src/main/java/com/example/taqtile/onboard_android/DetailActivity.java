package com.example.taqtile.onboard_android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

public class DetailActivity extends AppCompatActivity {

    private int id;
    private String firstName, lastName, avatar;
    private DetailElement idElement, firstNameElement, lastNameElement, avatarElement;
    private final LinearLayout.LayoutParams layoutParams = new LinearLayout.
            LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
    private LinearLayout linearLayout;
    /*android:layout_width="match_parent"*/
//    android:layout_height="wrap_content"
//    android:layout_marginTop="16dp"
//    android:layout_marginLeft="16dp"
//    android:layout_marginRight="16dp"
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        setContentView(R.layout.activity_detail_empty);
        linearLayout = (LinearLayout) findViewById(R.id.activity_detail);

        layoutParams.setMargins(getPixelValue(16),getPixelValue(16),getPixelValue(16),getPixelValue(0));

//        idElement = (DetailElement) findViewById(R.id.userIdElement);
//        firstNameElement = (DetailElement) findViewById(R.id.firstNameElement);
//        lastNameElement = (DetailElement) findViewById(R.id.lastNameElement);
//        avatarElement = (DetailElement) findViewById(R.id.avatarElement);
        fetchIntentData();
        childrenSet();
    }

    public void fetchIntentData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 1);
        firstName = intent.getStringExtra("userFirstName");
        lastName = intent.getStringExtra("userLastName");
        avatar = intent.getStringExtra("userAvatar");

    }

    public void childrenSet() {
        idElement = new DetailElement(this, null);
        idElement.setLabel(getString(R.string.userIdLabel));
        firstNameElement = new DetailElement(this, null);
        firstNameElement.setLabel(getString(R.string.userFirstNameLabel));
        lastNameElement = new DetailElement(this, null);
        lastNameElement.setLabel(getString(R.string.userLastNameLabel));
        avatarElement = new DetailElement(this, null);
        avatarElement.setLabel(getString(R.string.userAvatarLabel));

        linearLayout.addView(idElement, layoutParams);
        linearLayout.addView(firstNameElement, layoutParams);
        linearLayout.addView(lastNameElement, layoutParams);
        linearLayout.addView(avatarElement, layoutParams);

        idElement.setValue(Integer.toString(id));
        firstNameElement.setValue(firstName);
        lastNameElement.setValue(lastName);
        avatarElement.setValue(avatar);
    }
    public int getPixelValue(int dimenId) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dimenId,
                resources.getDisplayMetrics()
        );
    }
}
