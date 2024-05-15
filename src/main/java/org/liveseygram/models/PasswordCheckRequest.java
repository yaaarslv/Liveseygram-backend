package org.liveseygram.models;

public class PasswordCheckRequest {

    private String email;
    private String password;
    private String additionalParameter;

    public PasswordCheckRequest(String email, String password, String additionalParameter) {
        this.email = email;
        this.password = password;
        this.additionalParameter = additionalParameter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdditionalParameter() {
        return additionalParameter;
    }

    public void setAdditionalParameter(String additionalParameter) {
        this.additionalParameter = additionalParameter;
    }
}

