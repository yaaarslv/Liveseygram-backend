package org.liveseygram.models;

import org.liveseygram.entities.User;

public record UnsuccessfulUpdatingUsername(String message, boolean success) implements UpdatingUsernameResult{
}
