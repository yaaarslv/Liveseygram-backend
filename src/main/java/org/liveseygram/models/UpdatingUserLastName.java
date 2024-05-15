package org.liveseygram.models;

public class UpdatingUserLastName {
    private Long id;
    private String lastName;
    public UpdatingUserLastName(Long id, String lastName) {
        this.id = id;
        this.lastName = lastName;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
