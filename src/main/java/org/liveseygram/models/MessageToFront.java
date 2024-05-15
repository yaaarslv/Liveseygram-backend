package org.liveseygram.models;

import org.liveseygram.entities.User;

public class MessageToFront {
    private Long id;
    private Long senderId;
    private String senderUsername;
    private String senderFullname;
    private Long recipientId;
    private String recipientUsername;
    private String recipientFullname;
    private String content;
    private String messageStatus;
    private String avatarPath;

    public MessageToFront(Long id, User sender, User recipient, String content, String messageStatus, String avatarPath) {
        this.id = id;
        this.senderId = sender.getId();
        this.senderUsername = sender.getUsername();
        this.senderFullname = sender.getFullName();
        this.recipientId = recipient.getId();
        this.recipientUsername = recipient.getUsername();
        this.recipientFullname = recipient.getFullName();
        this.content = content;
        this.messageStatus = messageStatus;
        this.avatarPath = avatarPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderFullname() {
        return senderFullname;
    }

    public void setSenderFullname(String senderFullname) {
        this.senderFullname = senderFullname;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public String getRecipientFullname() {
        return recipientFullname;
    }

    public void setRecipientFullname(String recipientFullname) {
        this.recipientFullname = recipientFullname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
