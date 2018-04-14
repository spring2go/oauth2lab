package io.spring2go.jwtresourceserver.api;

public class UserInfo {

    private String name;

    private String email;

    public UserInfo(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
