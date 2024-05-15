package org.liveseygram.models;

public record UnsuccessfulRegisterData (String message, boolean success) implements RegisterResult{
}
