package com.example.taqtile.onboard_android;

/**
 * Created by taqtile on 1/4/17.
 */

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String avatar;

    public User(int id, String firstName, String lastName, String avatar) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAvatar(avatar);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
