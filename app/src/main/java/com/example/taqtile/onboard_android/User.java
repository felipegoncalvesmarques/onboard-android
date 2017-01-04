package com.example.taqtile.onboard_android;

/**
 * Created by taqtile on 1/4/17.
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
