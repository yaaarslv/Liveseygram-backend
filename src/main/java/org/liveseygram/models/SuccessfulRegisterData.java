package org.liveseygram.models;
import org.liveseygram.entities.User;

public record SuccessfulRegisterData(User user, boolean success) implements RegisterResult{
}
