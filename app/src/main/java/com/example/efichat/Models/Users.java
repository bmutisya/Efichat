package com.example.efichat.Models;

public class Users {
    String profilePic;
    String userName;
    String mail;
    String Password;
    String userId;
    String lastMessage;
    String status;

    public Users(){

    }

    public Users(String profilePic, String userName, String mail, String password, String userId, String lastMessage, String status) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        Password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.status = status;
    }

    public Users(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.Password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
