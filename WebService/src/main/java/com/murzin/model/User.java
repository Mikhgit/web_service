package com.murzin.model;

public class User {
    private long id;
    private String login;
    private String password;
    private ROLE role;

    public User(long id, String login, String password, ROLE role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public synchronized void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ROLE getRole() {
        return role;
    }

    public synchronized void setRole(String strRole) {
        switch (strRole){
            case "Admin": this.role = ROLE.ADMIN;
                break;
            case "User": this.role = ROLE.USER;
                break;
            case "Unknown": this.role = ROLE.UNKNOWN;
                break;
        }
    }

    public enum ROLE{
        ADMIN, USER, UNKNOWN;
    }
}
