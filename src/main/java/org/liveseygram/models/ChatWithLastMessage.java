package org.liveseygram.models;

import org.liveseygram.entities.Message;
import org.liveseygram.entities.User;

public class ChatWithLastMessage {
    private Long participantId;
    private String participantUsername;
    private String participantFullname;
    private String content;
    private Long id;
    private Long senderId;
    private String senderUsername;
    private String senderFullname;
    private Long recipientId;
    private String recipientUsername;
    private String recipientFullname;
    private String messageStatus;
    private String avatarPath;
    private Integer unreadMessagesCount;

    public ChatWithLastMessage(User participant, Message lastMessage, Integer unreadMessagesCount) {
        User sender = lastMessage.getSender();
        User recipient = lastMessage.getRecipient();
        this.id = lastMessage.getId();
        this.participantId = participant.getId();
        this.participantUsername = participant.getUsername();
        this.participantFullname = participant.getFullName();
        this.senderId = sender.getId();
        this.senderUsername = sender.getUsername();
        this.senderFullname = sender.getFullName();
        this.recipientId = recipient.getId();
        this.recipientUsername = recipient.getUsername();
        this.recipientFullname = recipient.getFullName();
        this.content = lastMessage.getContent();
        this.messageStatus = lastMessage.getMessageStatus().toString();
        this.avatarPath = participant.getAvatarPath();
        this.unreadMessagesCount = unreadMessagesCount;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantUsername() {
        return participantUsername;
    }

    public void setParticipantUsername(String participantUsername) {
        this.participantUsername = participantUsername;
    }

    public String getParticipantFullname() {
        return participantFullname;
    }

    public void setParticipantFullname(String participantFullname) {
        this.participantFullname = participantFullname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getUnreadMessagesCount() {
        return unreadMessagesCount;
    }

    public void setUnreadMessagesCount(Integer unreadMessagesCount) {
        this.unreadMessagesCount = unreadMessagesCount;
    }
}


