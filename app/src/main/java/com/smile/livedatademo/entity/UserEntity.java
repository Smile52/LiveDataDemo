package com.smile.livedatademo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * user_id : 1000
     * username : 盖伦
     * password : 1314520
     * sex : 0
     * nickname : 德玛西亚之力
     * phone : 15575818133
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwMCwiZXhwIjoxNTU1NTU1NjU1fQ.SpKwXt_9be2UcSP3dXGj5zIBquwIsi5carEHtqAH9QQ
     * role_id : 1
     * leave : 1
     */
    private int user_id;

    private String username;
    private String password;
    private int sex;
    private String nickname;
    private String phone;
    private String token;
    private int role_id;
    private int leave;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", role_id=" + role_id +
                ", leave=" + leave +
                '}';
    }
}
