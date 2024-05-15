package org.liveseygram.models;

import org.liveseygram.entities.User;

public record SuccessfulUpdatingUsername(User user, boolean success) implements UpdatingUsernameResult {
}
