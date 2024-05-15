package org.liveseygram.models;

public class UpdatingUserUsername {
    private Long id;
    private String username;
    public UpdatingUserUsername(Long id, String username) {
        this.id = id;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
