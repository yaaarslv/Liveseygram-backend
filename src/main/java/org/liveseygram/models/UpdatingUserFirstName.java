package org.liveseygram.models;

public class UpdatingUserFirstName {
    private Long id;
    private String firstName;
    public UpdatingUserFirstName(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
