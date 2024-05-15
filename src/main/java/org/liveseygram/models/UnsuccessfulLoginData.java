package org.liveseygram.models;

public record UnsuccessfulLoginData(String message, boolean success) implements LoginResult {
}
