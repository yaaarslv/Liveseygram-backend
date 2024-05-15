package org.liveseygram.models;

public class UpdatingUserPassword {
    private Long id;
    private String password;
    public UpdatingUserPassword(Long id, String password) {
        this.id = id;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
