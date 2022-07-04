package models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String lastMessage;
    private String lastMsgTime;
    private String phoneNo;
    private String conutry;
    private int imagid;

    public User(String name, String lastMessage, String lastMsgTime, String phoneNo, String conutry, int imagid) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.phoneNo = phoneNo;
        this.conutry = conutry;
        this.imagid = imagid;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(String lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCountry() {
        return conutry;
    }

    public void setCountry(String country) {
        this.conutry = country;
    }

    public int getImagid() {
        return imagid;
    }

    public void setImagid(int imagid) {
        this.imagid = imagid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                imagid == user.imagid &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastMessage, user.lastMessage) &&
                Objects.equals(lastMsgTime, user.lastMsgTime) &&
                Objects.equals(phoneNo, user.phoneNo) &&
                Objects.equals(conutry, user.conutry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastMessage, lastMsgTime, phoneNo, conutry, imagid);
    }
}
