package org.liveseygram.models;
import org.liveseygram.entities.User;

public record SuccessfulLoginData(User user, boolean success) implements LoginResult {
}
